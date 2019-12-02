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
            )
  (:import org.apache.commons.io.FileUtils
           org.apache.pdfbox.io.IOUtils
           org.apache.pdfbox.pdmodel.PDDocument
           org.apache.pdfbox.pdmodel.common.PDStream
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
  (.addPage doc page))

(defn keep-pages
  "Given a PDDocument and a collection of page numbers,
  return a new PDDocument containing only those page numbers "
  [pd-document page-numbers]
  (let [new-pddocument (PDDocument.)]
    (doseq [pd-page (map (partial get-page pd-document) page-numbers)] (add-page new-pddocument pd-page))
    new-pddocument))

(defn create-node [db key title page-group tran]
  #_{:node/uuid       (java.util.UUID/randomUUID)
     :node/kind       :document
     :media/extension ["pdf"]
     :text/tran       {:lang/en "delete me text"}
     :text/title      {:lang/en "the title"}
     :document/format :application/pdf
     :node/source     {:source/pages  #{1 2 3}
                       :source/object {:s3/key    "cms/playground/aaaaaaaa.pdf"
                                       :s3/bucket "cms-sandbox.obrizum"}}
     :node/ext-id     "abc"}

  (let [existing-origin-edge nil #_(ffirst (d/q '{:find  [(pull ?n [])]
                                                  :in    [$]
                                                  :where [
                                                          [?n]
                                                          ]}
                                                (client/db)))

        node-to-create       {:db/id           "anode"
                              :node/uuid       (java.util.UUID/randomUUID)
                              :node/kind       :document
                              :media/extension ["pdf"]
                              :text/title      {:lang/en title}
                              :text/tran       {:lang/en tran}
                              :db/doc          "archo-westcoast"

                              :document/format :application/pdf
                              ;:node/source     {:source/pages  #{1 2 3}
                              ;                  :source/object {:s3/key    "cms/playground/aaaaaaaa.pdf"
                              ;                                  :s3/bucket "cms-sandbox.obrizum"}}
                              ;:node/ext-id     "deleteme"

                              ; store information about the origin of the content
                              :content/origins [
                                                {:origin/pages     page-group
                                                 :origin/s3.object {
                                                                    :s3/bucket     "cms-sandbox.obrizum"
                                                                    :s3/key        key
                                                                    :s3/bucket+key ["cms-sandbox.obrizum" key]
                                                                    ;:s3/bucket+key  [[:person/uuid "abc"] key]
                                                                    ;:media/produced "anode"
                                                                    }
                                                 }
                                                ]
                              }

        ]

    (println "TXIS" (d/transact (client/get-conn) {:tx-data [

                                                             {:node/uuid   #uuid"fc30fd25-0026-4a98-b3c2-cda3c3e7499d"
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
      title       :title} :body} :parameters}]
  ; load the PDF from S3
  (let [doc (->> s3-key (get-s3-object "cms-sandbox.obrizum") :Body PDDocument/load)]

    (let [new-pdf      (keep-pages doc (map dec (first page-groups)))
          burnable-pdf (keep-pages doc (map dec (first page-groups)))]

      (let [{new-node-uuid :node/uuid} (create-node (client/db) s3-key title (first page-groups) (text/extract burnable-pdf))]

        (doall (put-s3-object
                 ;"obr-vod-destination-vpx8y5wsew25"
                 "cms-sandbox.obrizum"
                 (str new-node-uuid "/" new-node-uuid ".pdf") (pdf->input-stream new-pdf)))

        (resp/ok {:success true})

        )

      )

    )

  )





(comment

  (let [document (PDDocument/load (:Body (get-object "cms-sandbox.obrizum" "cms/playground/sample.pdf")))]
    (def adoc (first (split-pdfs document [[1 2 3] [4 5 6] [7 8 9 10 11 12]]))))

  (put-s3-object "cms-sandbox.obrizum" "somefile.pdf" (let [out (java.io.ByteArrayOutputStream.)]
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
                            ;                                  :s3/bucket "cms-sandbox.obrizum"}}
                            :node/ext-id     "deleteme"

                            ; store information about the origin of the content
                            :content/origins [
                                              {:origin/pages     page-group
                                               :origin/s3.object {:s3/bucket      "cms-sandbox.obrizum"
                                                                  :s3/key         key
                                                                  :s3/bucket+key  ["cms-sandbox.obrizum" key]
                                                                  :media/produced "anode"}
                                               }
                                              ]
                            }

                           ]}))
  )

(defn nodes-created-from-pages-handler [{{{s3-bucket :s3/bucket s3-key :s3/key} :path} :parameters}]

  (r/ok (node-queries/nodes-created-from-pages2 (client/db) s3-bucket s3-key)))


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
                                                               :origin/s3.object {:s3/bucket      "cms-sandbox.obrizum"
                                                                                  :s3/key         key
                                                                                  :s3/bucket+key  ["cms-sandbox.obrizum" key]
                                                                                  :media/produced "anode"}
                                                               }
                                                              ]
                                            }

                                           ;{:source/object}

                                           ]}))

