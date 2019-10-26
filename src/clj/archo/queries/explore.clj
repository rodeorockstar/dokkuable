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


; video 63666121299420156
; space 52578646044903633
(comment (d/q '{:find [(pull ?id [*])]
                :in   [$ ?id]}
              (client/db) 52578646044903633))

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

(defn wind [k coll]
  (reduce (fn [total next]
            (assoc total (get next k) next)
            ) {} coll))

(defn schema [db]
  (d/q '{:find  [(pull ?attribute [*])]
         :in    [$]
         :where [[?attribute :db/ident]]}
       db))


(defn reference-counts-query
  "Return the attributes of an entity in Datomic"
  [db id]
  (d/q '{:find  [?attr-ident (count ?value)]
         :in    [$ ?id]
         :where [[?id ?attr ?value]
                 [?attr :db/ident ?attr-ident]
                 [?attr :db/valueType ?value-type]
                 [?value-type :db/ident :db.type/ref]]}
       db id))


(defn reference-counts [db id]
  (into {} (map (fn [[k v]] [k {:count v}]) (reference-counts-query db id))))

(defn entity-without-components [db id]
  (reduce (fn [total [attr card v]]
            (update total attr (fn [s]
                                 (if (= card :db.cardinality/many)
                                   (conj s v)
                                   v))))
          {}
          (conj (d/q '{:find  [?attr-ident ?cardinality-ident ?v]
                       :in    [$ ?id]
                       :where [[?id ?attr ?v]
                               [?attr :db/ident ?attr-ident]
                               [?attr :db/cardinality ?cardinality]
                               [?cardinality :db/ident ?cardinality-ident]]}
                     db id)
                [:db/id :db.cardinality/one id])))



(defn entity-without-components2 [db id]
  (group-by first (d/q '{:find  [?id ?attr ?v ?t]
          :in    [$ ?id]
          :where [[?id ?attr ?v ?t]
                  [?attr :db/ident ?attr-ident]
                  [?attr :db/cardinality ?cardinality]
                  [?cardinality :db/ident ?cardinality-ident]]}
        db id)))