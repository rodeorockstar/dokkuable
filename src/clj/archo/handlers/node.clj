(ns archo.handlers.node
  (:require [ring.util.http-response :as r]
            [archo.client :as client]
            [archo.queries.node :as node-queries]
            [clojure.string :as str]
            [pdfboxing.split :as pdf]
            [clojure.java.io :as io]
            [cognitect.aws.client.api :as aws]
            [clojure.data.json :as json]
            [pdfboxing.merge :as pdf-merge]
            [pdfboxing.info :as info]
            [pdfboxing.common :as common]
            [datomic.client.api :as d]
            [ring.util.http-response :as resp]
            [pdfboxing.text :as text]
            [archo.config :as config]
            [archo.s3 :as s3-fns]
            )
  (:import org.apache.commons.io.FileUtils
           org.apache.pdfbox.io.IOUtils
           org.apache.pdfbox.pdmodel.PDDocument
           org.apache.pdfbox.cos.COSDocument
           org.apache.pdfbox.pdmodel.common.PDStream
           org.apache.pdfbox.text.PDFTextStripper
           (java.net URLEncoder)))


(def s3 (aws/client {:api :s3}))

(defn get-s3-object
  "Get an object from an S3 bucket"
  [bucket key]
  (aws/invoke s3 {:op :GetObject :request {:Bucket bucket :Key key}}))

(defn put-s3-object
  "Put an object into an S3 bucket"
  [bucket key body]
  (aws/invoke s3 {:op :PutObject :request {:Bucket bucket :Key key :Body body :ContentType "application/pdf"}}))

(defn get-page [doc n]
  (.getPage doc n))

(defn add-page [doc page]
  (.addPage doc (.setRotation page 90)))

(defn keep-pages
  "Given a PDDocument and a collection of page numbers,
  return a new PDDocument containing only those page numbers "
  [pd-document page-numbers]
  (let [new-pddocument (PDDocument.)]
    (doseq [pd-page (map (partial get-page pd-document) page-numbers)] (add-page new-pddocument pd-page))
    new-pddocument))


(defn available-files-handler [req]
  ;(println "PARAMSARE" (-> req :parameters :path :org/short-name))
  (r/ok (s3-fns/ls config/upload-bucket (-> req :parameters :path :org/short-name)))
  )
(defn create-node [db key title page-group tran space-uuid adaptive?]
  #_{:node/uuid       (java.util.UUID/randomUUID)
     :node/kind       :document
     :media/extension ["pdf"]
     :text/tran       {:lang/en "delete me text"}
     :text/title      {:lang/en "the title"}
     :document/format :application/pdf
     :node/source     {:source/pages  #{1 2 3}
                       :source/object {:s3/key    "cms/playground/aaaaaaaa.pdf"
                                       :s3/bucket archo.config/upload-bucket}}
     :node/ext-id     "abc"}


  (println "CREATENODESPACEID" space-uuid)

  (let [existing-origin-edge nil #_(ffirst (d/q '{:find  [(pull ?n [])]
                                                  :in    [$]
                                                  :where [
                                                          [?n]
                                                          ]}
                                                (client/db)))

        node-to-create       (cond-> {:db/id           "new-node"
                                      :node/uuid       (java.util.UUID/randomUUID)
                                      :node/kind       :document
                                      :media/extension ["pdf"]
                                      :text/title      {:lang/en title}
                                      :text/tran       {:lang/en tran}
                                      :db/doc          "archo-rscilin"
                                      :document/format :application/pdf}
                                     adaptive? (assoc :node/adaptive? true))]

    (println "CREATINGWITH" {:s3/bucket+key [archo.config/upload-bucket key]})

    (println "TXIS" (d/transact (client/get-conn) {:tx-data [

                                                             {:obr/kind      :source
                                                              :origin/source {:s3/key        key
                                                                              :s3/bucket     archo.config/upload-bucket
                                                                              :s3/bucket+key [archo.config/upload-bucket key]}
                                                              :origin/pages  page-group
                                                              :origin/target "new-node"
                                                              }


                                                             {
                                                              ; westcoast space
                                                              ;:node/uuid   #uuid"fc30fd25-0026-4a98-b3c2-cda3c3e7499d"
                                                              ; HSBC space
                                                              ;:node/uuid   #uuid"3c9b9086-22b8-4978-961c-001140fcba94"
                                                              ; Monty space
                                                              ;:node/uuid   #uuid"4635dd9e-d1d5-4d2a-8844-ae207f422334"
                                                              ; RCSI adaptive space
                                                              ;:node/uuid   #uuid"a0701313-a516-4edc-a6e6-2ecbde4ba09f"
                                                              ; RCIS linear space
                                                              ;:node/uuid   #uuid"7d9217eb-a3c7-487a-978c-e91ad350b5f3"
                                                              ; josh test space
                                                              ;:node/uuid   #uuid"9482933f-5a3a-4f5d-98f1-f0a7e0696d21"
                                                              :node/uuid   space-uuid
                                                              :space/point [node-to-create]}

                                                             ]}))
    node-to-create

    )

  )

(comment
  "Delete all deleteme nodes"
  (d/transact (client/get-conn) {:tx-data (map (fn [x] [:db/retractEntity x])
                                               (map first (d/q '{:find  [?n]
                                                                 :in    [$]
                                                                 :where [
                                                                         [?n :node/ext-id "deleteme"]
                                                                         ]}
                                                               (client/db))))})
  )

(defn pdf->input-stream
  "Return a "
  [pd-document]
  (let [output-stream (java.io.ByteArrayOutputStream.)]
    (.save pd-document output-stream)
    (.close pd-document)
    (java.io.ByteArrayInputStream. (.toByteArray output-stream))))

(defn make-files-handler
  "Expects the following body parameters
  - :s3/key the S3 key of the PDF
  - :page-groups a collection of page numbers [[1 2 3] [4 5 6]] each of which become a PDF"
  [{{{s3-key      :s3/key
      page-groups :page-groups
      title       :title
      space-uuid  :space/uuid
      adaptive?   :node/adaptive?
      } :body} :parameters}]
  ; load the PDF from S3
  (println "NODEISADAPTIVE" adaptive?)
  (let [doc (->> s3-key (get-s3-object archo.config/upload-bucket) :Body PDDocument/load)
        ;doc2 (->> s3-key (get-s3-object archo.config/upload-bucket) :Body PDDocument/load)
        ]

    #_(let [new-pdf      (keep-pages doc (map dec (first page-groups)))
            burnable-pdf (keep-pages doc2 (map dec (first page-groups)))]

        (let [{new-node-uuid :node/uuid} (create-node (client/db) s3-key title (first page-groups) (text/extract burnable-pdf))]

          (doall (put-s3-object
                   "obr-vod-destination-vpx8y5wsew25"
                   ;archo.config/upload-bucket
                   (str new-node-uuid "/" new-node-uuid ".pdf") (pdf->input-stream new-pdf)))

          (resp/ok {:success true})

          )

        )

    (let [doc      (->> s3-key (get-s3-object archo.config/upload-bucket) :Body PDDocument/load)
          stripper (PDFTextStripper.)]
      (let [pages-to-keep (map dec (first page-groups))
            page-count    (range (.getNumberOfPages doc))
            pages-to-drop (sort > (clojure.set/difference (set page-count) (set pages-to-keep)))]

        (doseq [p pages-to-drop]
          (.removePage doc p)))

      (let [extracted-text (.getText stripper doc)
            {new-node-uuid :node/uuid} (create-node (client/db) s3-key title (first page-groups) extracted-text space-uuid adaptive?)]
        ;(.save o "tada.pdf")
        (println "SAVINGTOCDN" (str new-node-uuid "/" new-node-uuid ".pdf"))
        (put-s3-object
          "obr-vod-destination-vpx8y5wsew25"
          ;archo.config/upload-bucket
          (str new-node-uuid "/" new-node-uuid ".pdf") (pdf->input-stream doc))

        ))

    )

  )





(comment

  (let [document (PDDocument/load (:Body (get-object archo.config/upload-bucket "cms/playground/sample.pdf")))]
    (def adoc (first (split-pdfs document [[1 2 3] [4 5 6] [7 8 9 10 11 12]]))))

  (put-s3-object archo.config/upload-bucket "somefile.pdf" (let [out (java.io.ByteArrayOutputStream.)]
                                                             (.save adoc out)
                                                             (.close adoc)
                                                             (java.io.ByteArrayInputStream. (.toByteArray out))))
  )



(comment

  (let [key        "sample.pdf"
        page-group [1 2 3 4 5]]
    (d/transact (client/get-conn)
                {:tx-data [

                           {:db/id           "anode"
                            :node/uuid       #uuid"95e87321-e23a-48a6-a489-76f1fa15a0d6"
                            :node/kind       :document
                            :media/extension ["pdf"]
                            :text/tran       {:lang/en "delete me text"}
                            :text/title      {:lang/en "the title"}

                            :document/format :application/pdf
                            ;:node/source     {:source/pages  #{1 2 3}
                            ;                  :source/object {:s3/key    "cms/playground/aaaaaaaa.pdf"
                            ;                                  :s3/bucket archo.config/upload-bucket}}
                            :node/ext-id     "deleteme"

                            ; store information about the origin of the content
                            :content/origins [
                                              {:origin/pages     page-group
                                               :origin/s3.object {:s3/bucket      archo.config/upload-bucket
                                                                  :s3/key         key
                                                                  :s3/bucket+key  [archo.config/upload-bucket key]
                                                                  :media/produced "anode"}
                                               }
                                              ]
                            }

                           ]}))
  )

(defn retract-node [{parameters :parameters}]
  (if (:db-after (d/transact (client/get-conn) {:tx-data [[:db/retractEntity [:node/uuid (-> parameters :path :node/uuid)]]]}))
    (r/ok {:node/uuid (-> parameters :path :node/uuid)})
    (r/bad-request {:status false})))

(defn rename-node [{parameters :parameters}]
  (println "RENAMING" parameters)
  (if (:db-after (d/transact (client/get-conn) {:tx-data [
                                                          {:node/uuid  (-> parameters :body :node/uuid)
                                                           :text/title {:lang/en (-> parameters :body :lang/en)}}
                                                          ]}))
    (r/ok {:node/uuid (-> parameters :body :node/uuid)
           :parameters parameters})
    (r/bad-request {:status false})))

(defn nodes-created-from-pages-handler [{{{s3-bucket  :s3/bucket
                                           s3-key     :s3/key
                                           space-uuid :space/uuid} :path} :parameters}]

  (println "SIDIS" space-uuid)

  (r/ok (node-queries/nodes-created-from-pages3 (client/db) s3-bucket s3-key space-uuid)))


(comment
  (d/transact (client/get-conn) {:tx-data [

                                           {:db/id           "anode"
                                            :node/uuid       #uuid"0b3ac126-7fe5-458f-baf1-5897d5fc3a02"
                                            :node/kind       :document
                                            :media/extension ["pdf"]
                                            :text/tran       {:lang/en "delete me text"}
                                            :text/title      {:lang/en "the title"}

                                            :document/format :application/pdf
                                            :node/ext-id     "deleteme"

                                            ;; store information about the origin of the content
                                            :content/origins [
                                                              {:origin/pages     page-group
                                                               :origin/s3.object {:s3/bucket      archo.config/upload-bucket
                                                                                  :s3/key         key
                                                                                  :s3/bucket+key  [archo.config/upload-bucket key]
                                                                                  :media/produced "anode"}
                                                               }
                                                              ]
                                            }

                                           ;{:source/object}

                                           ]}))



(comment
  "duplicates"
  (filter (fn [[t n]]
            (> (count n) 1)) (group-by second (d/q '{:find  [?p ?title-en]
                                                     :in    [$]
                                                     :where [
                                                             [?p :content/origins ?o]
                                                             [?p :text/title ?title]
                                                             [?title :lang/en ?title-en]
                                                             [?o :origin/pages ?pages]
                                                             [?o :origin/s3.object ?n]
                                                             ;[?n :s3/key "introduction_to_cyber_security__stay_safe_online.pdf"]
                                                             [?n :s3/key "L13 Cancer Screening Programmes.pdf"]
                                                             ]}
                                                   (client/db)))))


(defn fix-pdf22 [bucket-name pages]
  (let [doc (->> "AZURE BACKUP.pdf" (get-s3-object archo.config/upload-bucket) :Body PDDocument/load)]

    (let [new-pdf (keep-pages doc (map dec (sort pages)))]

      (let [new-node-uuid #uuid"0f17a9e9-ce17-41dc-bdcb-bfbaea4f2d7b"]

        (doall (put-s3-object
                 "obr-vod-destination-vpx8y5wsew25"
                 ;archo.config/upload-bucket
                 (str new-node-uuid "/" new-node-uuid ".pdf") (pdf->input-stream new-pdf)))

        (resp/ok {:success true})

        )

      )

    ))

(defn fix-pdf [original-file-name node-uuid pages]
  (let [doc (->> original-file-name (get-s3-object archo.config/upload-bucket) :Body PDDocument/load)]

    (let [new-pdf (keep-pages doc (map dec (sort pages)))]

      (let [node-uuid node-uuid]

        (doall (put-s3-object
                 "obr-vod-destination-vpx8y5wsew25"
                 ;archo.config/upload-bucket
                 (str node-uuid "/" node-uuid ".pdf") (pdf->input-stream new-pdf)))

        (resp/ok {:success true})

        )

      )

    ))

(comment fix-all
         (map-indexed (fn [idx [node-id pages]]
                        (println "Doing " (inc idx) " / " (count doc-pages))
                        (fix-pdf "introduction_to_cyber_security__stay_safe_online.pdf" node-id pages)

                        ) doc-pages))