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





(defn entities-without-components [db ids]
  (group-by first (d/q '{:find  [?id ?attr ?v ?t]
                         :in    [$ [?id ...]]
                         :where [[?id ?attr ?v ?t]
                                 [?attr :db/ident ?attr-ident]
                                 [?attr :db/cardinality ?cardinality]
                                 [?cardinality :db/ident ?cardinality-ident]]}
                       db ids)))

(defn find-id [db a v]
  (println "finding" a (type a) v (type v))
  (entities-without-components db (first (d/q '{:find  [?e]
                                                :in    [$ ?a ?v]
                                                :where [[?e ?a ?v]]}
                                              db a v))))

(def db-types #{:db.type/keyword :db.type/string :db.type/boolean
                :db.type/long :db.type/bigint :db.type/float
                :db.type/double :db.type/bigdec :db.type/ref
                :db.type/instant :db.type/uuid :db.type/uri
                :db.type/bytes})

(defn str->boolean [s] (Boolean/valueOf s))
(defn str->long [s] (Long/parseLong s))
(defn str->uuid [s] (java.util.UUID/fromString s))

(def to-type
  {:db.type/keyword keyword
   :db.type/string  str
   :db.type/boolean str->boolean
   :db.type/long    str->long
   :db.type/uuid    str->uuid})


;{:entity 34159627256401033, :attribute 373, :value "What is used to guide the grodwth of cerebral organoids?"}

(def somes2 [34159627256401033 373 "What is used to guide the grodwth of cerebral organoids?"])
(def somes [17376681767001057 118 "04474585-a75d-49b4-aec4-ae467ade2dd1"])

(defn edit [db e a v]
  (println "finding" a (type a) v (type v))
  (let [[attr-ident value-type] (first (d/q '{:find  [?attr-ident ?value-type-ident]
                                              :in    [$ ?a]
                                              :where [
                                                      [?a :db/ident ?attr-ident]
                                                      [?a :db/valueType ?value-type]
                                                      [?value-type :db/ident ?value-type-ident]
                                                      ]}
                                            (client/db) a))
        coercion-fn (get to-type value-type read-string)]
    (let [coerced-value (coercion-fn v)]
      (println "coerced" e a v coercion-fn)
      ;[attr-ident coerced-value]
      (let [tx-result (d/transact (client/get-conn)
                                  {:tx-data [
                                             [:db/add e a coerced-value]
                                             ]}
                                  )]
        (println "R" tx-result)
        {:success false})
      )))