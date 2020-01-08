(ns archo.queries.node
  (:require [datomic.client.api :as d]
            [archo.client :as client]))



(defn parse-int [s]
  (Long/parseLong s))

(defn vector-compare [[value1 & rest1] [value2 & rest2]]
  (let [result (compare value1 value2)]
    (cond
      (not (zero? result)) result
      (nil? value1) 0
      :else (recur rest1 rest2))))

(defn prepare-string [s]
  (let [s (or s "")
        parts (vec (clojure.string/split s #"\d+"))
        numbers (->> (re-seq #"\d+" s)
                     (map parse-int)
                     (vec))]
    (vec (interleave (conj parts "") (conj numbers "")))))

(defn natural-compare [a b]
  (vector-compare (prepare-string a)
                  (prepare-string b)))

(defn xsort [coll] (clojure.core/sort natural-compare coll))

(defn xsort-by [keyfn coll]
  (clojure.core/sort-by keyfn natural-compare coll))


; org
(comment
  "org" #uuid"06f588f6-b67e-447c-a9e1-b72bc37f1dd3"
  "space" #uuid"c4b92483-c6e3-46d0-bc5c-cd33056d638f")

{:s3/key    "cms/playground/aaaaaaaa.pdf"
 :s3/bucket "cms-sandbox.obrizum"}

{:source/pages  #{1 2 3}
 :source/object []}

(defn add-node [s]
  {:node/uuid       (java.util.UUID/randomUUID)
   :node/kind       :document
   :media/extension ["pdf"]
   :text/tran       {:lang/en "delete me text"}
   :text/title      {:lang/en "the title"}
   :document/format :application/pdf
   :node/source     {:source/pages  #{1 2 3}
                     :source/object {:s3/key    "cms/playground/aaaaaaaa.pdf"
                                     :s3/bucket "cms-sandbox.obrizum"}}
   :node/ext-id     "abc"})


(defn nodes-created-from-origin
  "Nodes created from origin"
  [db s3-bucket s3-key]
  (d/q '{:find  [(pull ?node [:node/ext-id
                              :document/format
                              :node/kind
                              :text/title
                              :media/extension
                              :db/id
                              :node/uuid
                              :content/origins
                              :text/tran])]
         :in    [$ ?bucket ?key]
         :where [[?object :s3/bucket ?bucket]
                 [?object :s3/key ?key]
                 [?origin-edge :origin/s3.object ?object]
                 [?node :content/origins ?origin-edge]]}
       db s3-bucket s3-key))

(defn group-by-reduce [f vals-f coll]
  (reduce (fn [total [k v]] (assoc total k (map vals-f v))) {} (group-by f coll)))

(defn nodes-created-from-pages
  "Nodes created from origin"
  [db s3-bucket s3-key]
  (group-by-reduce first last (d/q '{:find  [?pages ?node-uuid]
                                     :in    [$ ?bucket ?key]
                                     :where [[?object :s3/bucket ?bucket]
                                             [?object :s3/key ?key]

                                             [?origin-edge :origin/s3.object ?object]
                                             [?origin-edge :origin/pages ?pages]

                                             [?node :content/origins ?origin-edge]
                                             [?node :node/uuid ?node-uuid]]}
                                   db s3-bucket s3-key)))

(defn nodes-created-from-pages2
  "Nodes created from origin"
  [db s3-bucket s3-key space-uuid]
  (group-by-reduce first last (d/q '{:find  [?pages (pull ?node [:node/uuid :text/title])]
                                     :in    [$ ?bucket ?key ?space-uuid]
                                     :where [[?object :s3/bucket ?bucket]
                                             [?object :s3/key ?key]

                                             [?origin-edge :origin/s3.object ?object]
                                             [?origin-edge :origin/pages ?pages]

                                             [?node :content/origins ?origin-edge]

                                             [?space :space/point ?node]
                                             [?space :node/uuid ?space-uuid]]}
                                   db s3-bucket s3-key space-uuid)))

(defn nodes-created-from-pages3
  "Nodes created from origin"
  [db s3-bucket s3-key space-uuid]
  (group-by-reduce first last (d/q '{:find  [?pages (pull ?target [:node/uuid :text/title])]
                                     :in    [$ ?bucket ?key ?space-uuid]
                                     :where [

                                             ; Bind the original source document on S3
                                             [?source :s3/bucket ?bucket]
                                             [?source :s3/key ?key]

                                             ; Find the source connection between the document and a node
                                             [?origin :origin/source ?source]
                                             [?origin :origin/target ?target]
                                             [?origin :origin/pages ?pages]

                                             ; Only select target nodes within the space
                                             [?space :node/uuid ?space-uuid]
                                             [?space :space/point ?target]

                                             ]}
                                   db s3-bucket s3-key space-uuid)) )


(comment
  (nodes-created-from-pages2 (client/db) "cms-sandbox.obrizum" "cms/playground/sample.pdf"))


(comment
  (d/transact (client/get-conn)
              {:tx-data (doc->section (client/db)
                                      "cms-sandbox.obrizum"
                                      "AZURE BACKUP.pdf"
                                      #uuid"ca565c65-68e9-40bf-b04a-b76b2e06a479")}))

(defn doc->section [db bucket key tag-uuid]
  (let [results (d/q '{:find  [?p (distinct ?pages)]
                       :in    [$ ?bucket ?key]
                       :where [
                               [?p :content/origins ?o]
                               [?o :origin/pages ?pages]
                               [?o :origin/s3.object ?n]
                               [?n :s3/key ?key]
                               ]}
                     db bucket key)]
    (map (fn [[db-id pages]]
           {:db/id     db-id
            :node/edge [{:edge/node {:node/uuid tag-uuid}
                         :edge/kind :tag
                         :edge/mass (double (apply min pages))}]})
         (sort-by (comp (partial apply min) second) results))))



(defn doc->section2 [db bucket key tag-uuid space-uuid]
  (let [results (d/q '{:find  [?p (distinct ?pages)]
                       :in    [$ ?bucket ?key ?space-uuid]
                       :where [
                               [?s :node/uuid ?space-uuid]
                               [?s :space/point ?p]
                               [?p :content/origins ?o]
                               [?o :origin/pages ?pages]
                               [?o :origin/s3.object ?n]
                               [?n :s3/key ?key]
                               ]}
                     db bucket key space-uuid)]
    results
    #_(map (fn [[db-id pages]]
             {:db/id     db-id
              :node/edge [{:edge/node {:node/uuid tag-uuid}
                           :edge/kind :tag
                           :edge/mass (double (apply min pages))}]})
           (sort-by (comp (partial apply min) second) results))))




(comment "SORTED"

         "Lecture 1 - Cancer: Prevention and Control"
         "Lecture 2 - An Introduction to Cancer"
         "Lecture 3 - Mutagens, carcinogens and chemically induced carcinogenesis"
         "Lecture 4 - The Hallmarks of Cancer: Molecular Characteristics of Cancer Cells"
         "Lecture 5 - The Cell Cycle, Oncogenes & Tumour Suppressor Genes: Driving the Cancer Phenotype"
         "Lecture 6 - Anatomy of the Lymphatic System"
         "Lecture 7 - Psychological Impact of a Diagnosis of Cancer"
         "Lecture 8 - Clinical Assessment, Diagnosis, Markers, & Staging"
         "Lecture 9 - Tumour Immunology: How Cancer Re-programmes Our Immune System"
         "Lecture 10 - Mechanistic Organic Chemistry: mechanisms of DNA damage"
         "Lecture 11 - Angiogenesis & Metastasis: Mechanisms of Cancer Progression"
         "Lecture 12 - Tumour Metabolism: Powering the Growth & Dissemination of Cancer"
         "Lecture 13 - Cancer screening programmes (Part 1 & 2)"
         "Lecture 14 - Cancer vaccines"
         "Lecture 15 - Overview of Cancer Treatment Options: (Surgery,  Radiotherapy, Chemotherapy)"
         )