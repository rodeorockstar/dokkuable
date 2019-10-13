(ns archo.queries.entities
  (:require [datomic.client.api :as d]
            [archo.client :as client]))

(defn orgs
  "Return a list of organisation maps"
  [db]
  (d/q '{:find  [?name ?short-name ?uuid]
         :keys  [org/name org/short-name obr/uuid]
         :where [[?o :org/short-name ?short-name]
                 [?o :org/name ?name]
                 [?o :obr/uuid ?uuid]]}
       db))

(defn spaces
  "Return a list of organisation maps"
  [db org-short-name]
  (d/q '{:find  [(pull ?space [*])]
         :in    [$ ?org-short-name]
         :where [
                 [?org :org/short-name ?org-short-name]
                 [?org :obr/permissions ?perms]
                 [?perms :perm/target ?space]
                 [?space :node/kind :space]

                 ]}
       db org-short-name))