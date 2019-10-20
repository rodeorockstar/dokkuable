(ns archo.queries.entities
  (:require [datomic.client.api :as d]
            [archo.client :as client]))

(defn key-by [key coll]
  (zipmap (map key coll) coll))

(defn orgs
  "Return a list of organisation maps"
  [db]
  (key-by :org/short-name (d/q '{:find  [?name ?short-name ?uuid]
                                 :keys  [org/name org/short-name obr/uuid]
                                 :where [[?o :org/short-name ?short-name]
                                         [?o :org/name ?name]
                                         [?o :obr/uuid ?uuid]]}
                               db)))

(defn spaces
  "Return a list of organisation maps"
  [db org-short-name]
  (->> (d/q '{:find  [(pull ?space [:node/uuid :space/title :text/tran])]
              :in    [$ ?org-short-name]
              :where [[?org :org/short-name ?org-short-name]
                      [?org :obr/permissions ?perms]
                      [?perms :perm/target ?space]
                      [?space :node/kind :space]]}
            db org-short-name)
       (map first)
       (key-by :node/uuid)))

(defn node-counts-by-kind
  "Return a map of node counts by kind within a space"
  [db space-uuid]
  (->> (d/q '{:find  [?kind (count ?point)]
              :in    [$ ?space-uuid]
              :where [[?space :node/uuid ?space-uuid]
                      [?space :space/point ?point]
                      [?point :node/kind ?kind]
                      ]}
            db space-uuid)
       (into {})))

(defn space-details
  "Return the details of a space"
  [db space-uuid]
  {:details (-> (d/q '{:find  [(pull ?space [:node/kind
                                             :node/uuid
                                             :space/intro-summary
                                             :space/intro-video
                                             :space/title
                                             :text/summary
                                             :text/tran
                                             :view/visibility])]
                       :in    [$ ?space-uuid]
                       :where [[?space :node/uuid ?space-uuid]]}
                     db space-uuid)
              ffirst)
   :node-count (node-counts-by-kind db space-uuid)})

