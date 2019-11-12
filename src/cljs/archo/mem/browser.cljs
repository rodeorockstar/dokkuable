(ns archo.mem.browser
  (:require [archo.fx :as fx]
            [goog.math.Long :as lo]
            [archo.routes :as routes]
            [cuerdas.core :as c]
            [re-frame.core :refer [reg-sub reg-event-db reg-event-fx reg-sub trim-v]]))

(reg-event-fx ::fetch-node trim-v
              (fn [{db :db} [id]]
                {:db      (assoc db :in-view (lo/fromString id))
                 ::fx/api {:uri        (str "/assets/node/" id)
                           :on-success [::store-node id]}}))

(reg-event-fx ::fetch-nodes trim-v
              (fn [{db :db} [[root :as ids]]]
                {
                 :db      (-> db
                              (assoc :in-view root)
                              (assoc :view ids))
                 ::fx/api {:uri          (str "/assets/nodes")
                           :method       :get
                           :query-params {:ids ids}
                           :on-success   [::store-nodes]}}))

(reg-event-db ::store-nodes trim-v
              (fn [db [results]]
                ;(js/console.log "RESULTS" (reduce (fn [total next]) results))
                (let [r (reduce (fn [total [k v]]
                                  (assoc total (lo/fromString (str k)) v)
                                  ) {} results)]
                  (update db :nodes merge r))))

(reg-event-fx ::fetch-schema trim-v
              (fn [{db :db} []]
                {
                 ::fx/api {:uri        (str "/assets/schema")
                           :method     :get
                           :on-success [::store-schema]}}))

(reg-event-db ::store-schema trim-v
              (fn [db [results]]
                (assoc-in db [:datomic :schema] results)))

(reg-event-fx ::load-node trim-v
              (fn [_ [id]]
                {::fx/api {:uri        (str "/assets/node/" id)
                           :on-success [::store-node id]}}))

(reg-event-db ::store-node trim-v
              (fn [db [id results]]
                (update db :nodes merge results)))

(reg-sub ::in-view
         (fn [db]
           (get-in db [:nodes (get db :in-view)])))

(reg-sub ::schema
         (fn [db]
           (get-in db [:datomic :schema])))

(reg-sub ::nodes
         (fn [db]
           (get db :nodes)))

(reg-sub ::node
         (fn [db [_ id]]
           (get-in db [:nodes id])))

(reg-sub ::schema-attribute
         :<- [::schema]
         (fn [schema [_ attribute-id]]
           (get schema attribute-id)))

(reg-sub ::root
         (fn [db]
           (-> db :view first)))

(reg-sub ::cursor
         (fn [db]
           (-> db :view)))

(reg-sub ::all-items
         :<- [::nodes]
         :<- [::cursor]
         :<- [::schema]
         (fn [[nodes cursor schema]]


           (reduce (fn [total db-id]


                     (conj total
                           {:id     db-id
                            :trail  (vec (conj (:trail (last total)) db-id))
                            :entity (clojure.set/rename-keys (group-by second (get nodes db-id)) schema)}))
                   []
                   cursor)))

(reg-sub ::as-entity
         (fn [db [_ datoms]]
           ;(js/console.log "datoms" datoms)
           (group-by second datoms)))


(reg-sub ::unique-idents
         :<- [::schema]
         (fn [schema]
           (sort-by :db/ident (filter (fn [attribute]
                                        (-> attribute :db/unique)
                                        ) (vals schema)))))



(reg-event-fx ::search trim-v
              (fn [{db :db} [attribute value]]
                (let [kw :node/uuid]
                  {
                   ::fx/api {:uri        (str "/assets/search")
                             :method     :post
                             :params     {
                                          ;:attribute kw
                                          :attribute attribute
                                          ;:value     #uuid"1b84708c-79d2-4159-8829-666ea050eaf9"
                                          :value     value
                                          }
                             :on-success [::store-search attribute value]}})))

(reg-event-fx ::store-search trim-v
              (fn [db [attribute value results]]
                ;(archo.routes/redirect!)
                (routes/redirect! {:name :route/browser-id
                                         :path-params {:id-tree (str (ffirst results))} })
                {:dispatch [::store-nodes results]}))


(reg-event-fx ::edit trim-v
              (fn [{db :db} [e a v]]
                ;(js/console.log "EE" e a v)

                {::fx/api {:uri "/assets/edit"
                           :method :post
                           :params {:entity e
                                    :attribute a
                                    :value v}
                           :on-success [::store-edit]}}
                ))

(reg-event-db ::store-edit trim-v
              (fn [db [results]]
                ;(js/console.log "results" results)
                db))

(reg-event-db ::toggle-modal trim-v
              (fn [db [val]]
                (assoc db :show-modal? val)
                ))

(reg-sub ::modal-status
         (fn [db]
           (get db :show-modal?)))




