(ns archo.queries.explore
  (:require [datomic.client.api :as d]
            [archo.client :as client]))

(defn describe
  "Return the attributes of an entity in Datomic"
  [db id]
  (map first (d/q '{:find  [(pull ?attr [*])]
                    :in    [$ ?id]
                    :where [[?id ?attr]]}
                  db id)))

(defn reference-attribute
  "Return true if an attribute map represents a reference attribute"
  [attribute-map]
  (= (-> attribute-map :db/valueType :db/ident) :db.type/ref))

(defn group-values-by-key
  "Given a collection of 2 types [key-a {:some-attr v1],
  return a map of [key-a (v1 v2) key-b (v1 v2 v3)"
  [coll]
  (into {} (map (fn [[k v]] [k (map str (map last v))]) (group-by first coll))))

(defn reference-values
  "Return the attributes of an entity in Datomic"
  [db id]
  (d/q '{:find  [?attr-ident ?value]
         :in    [$ ?id]
         :where [[?id ?attr ?value]
                 [?attr :db/ident ?attr-ident]
                 [?attr :db/valueType ?value-type]
                 [?value-type :db/ident :db.type/ref]]}
       db id))

(defn non-reference-values
  "Returns a collection of [non-ref-attribute value] tuples of an entity in Datomic"
  [db id]
  (d/q '{:find  [?attr-ident ?value]
         :in    [$ ?id]
         :where [[?id ?attr ?value]
                 [?attr :db/ident ?attr-ident]
                 [?attr :db/valueType ?value-type]
                 (not [?value-type :db/ident :db.type/ref])]}
       db id))



