(ns archo.queries.node
  (:require [datomic.client.api :as d]
            [archo.client :as client]))


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
  [db s3-bucket s3-key]
  (group-by-reduce first last (d/q '{:find  [?pages (pull ?node [:node/uuid :text/title])]
                                     :in    [$ ?bucket ?key]
                                     :where [[?object :s3/bucket ?bucket]
                                             [?object :s3/key ?key]

                                             [?origin-edge :origin/s3.object ?object]
                                             [?origin-edge :origin/pages ?pages]

                                             [?node :content/origins ?origin-edge]
                                             [?node :node/uuid ?node-uuid]]}
                                   db s3-bucket s3-key)))


(comment
  (nodes-created-from-pages2 (client/db) "cms-sandbox.obrizum" "cms/playground/sample.pdf"))


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