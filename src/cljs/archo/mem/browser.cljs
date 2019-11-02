(ns archo.mem.browser
  (:require [archo.fx :as fx]
            [goog.math.Long :as lo]
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
                            (assoc  :in-view root)
                            (assoc  :view ids))
                 ::fx/api {:uri          (str "/assets/nodes")
                           :method       :get
                           :query-params {:ids ids}
                           :on-success   [::store-nodes]}}))

(reg-event-db ::store-nodes trim-v
              (fn [db [results]]
                (update db :nodes merge results)))

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
         (fn [[nodes cursor]]
           (map (partial group-by second) (map (partial get nodes) cursor))
           ))

(reg-sub ::as-entity
         (fn [db [_ datoms]]
           (js/console.log "datoms" datoms)
           (group-by second datoms)))