(ns archo.mem.graph
  (:require [archo.fx :as fx]
            [reitit.core :as reitit]
            [archo.mem.events :as mem-events]
            [reitit.coercion.spec]
            [reitit.coercion :as coercion]
            [re-frame.core :refer [reg-sub reg-event-db reg-event-fx reg-sub trim-v]]))


(reg-event-fx ::fetch-node trim-v
              (fn [_world [id]]
                {::fx/api {
                           ; match the API endpoint via its stored name in the router
                           :uri        (str "/assets/node/" id)
                           :on-success [::store-node id]}}))

(reg-event-db ::store-node trim-v
              (fn [db [id results]]
                (assoc-in db [:nodes id] results)))

(reg-sub ::nodes (fn [db] (vals (get-in db [:nodes]))))

(reg-sub ::links (fn [db]
                   (let [nodes (get-in db [:nodes])]
                     #_(remove nil? (mapcat (fn [[k v]]
                                         (when-let [references (-> v :reference-attributes)]
                                           (mapcat (fn [[attribute ids]]
                                                     (println ids)) references)
                                           )
                                         ) nodes))

                     (mapcat (fn [[k v]]
                            ;(js/console.log "V" n)

                            (when-let [references (-> v :reference-attributes)]
                              (for [parent [k]
                                    child  (flatten (vals references))]
                                {:source parent
                                 :target child})
                              )
                            ;(-> v :reference-attributes vals)
                            ) nodes)

                     )))

(reg-event-fx ::fetch-node-references trim-v
              (fn [_world [id]]
                {::fx/api {
                           ; match the API endpoint via its stored name in the router
                           :uri        (str "/assets/node/" id "/references")
                           :on-success [::store-references id]}}))

(reg-event-db ::store-references trim-v
              (fn [db [id results]]
                (js/console.log "results" results (flatten (vals results)))
                (-> db
                    (update-in [:nodes id :reference-attributes] merge results)
                    (update-in [:nodes] merge (reduce (fn [total next]
                                                        (assoc total next {:id next
                                                                           :local-attributes {:id next}})
                                                        ) {} (flatten (vals results)))))))

;(reg-sub ::nodes (fn [db] (get-in db [:nodes])))


(reg-event-db ::store-positions trim-v
              (fn [db [{:keys [nodes links]}]]
                (-> db
                    (assoc-in [:pos :nodes] (let [nodes (js->clj nodes :keywordize-keys true)]
                                           (zipmap (map :id nodes) nodes)))
                    (assoc-in [:pos :links] (js->clj links :keywordize-keys true)))))

(reg-sub ::node-positions
         (fn [db]
           (get-in db [:pos :nodes])))

(reg-sub ::link-positions
         (fn [db]
           (get-in db [:pos :links])))