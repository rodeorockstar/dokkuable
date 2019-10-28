(ns archo.views.browser
  (:require [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.assets :as mem-assets]
            [archo.views.kinds.pdf :as pdf]
            [archo.mem.graph :as mem-graph]
            [archo.views.wizard :as wizard]
            [archo.mem.browser :as mem-browser]
            [goog.math.Long :as lo]))

(defn is-many? [schema attribute]
  (= :db.cardinality/many (get-in schema [attribute :db/cardinality :db/ident])))

(defn is-ref? [schema attribute]
  (= :db.type/ref (get-in schema [attribute :db/valueType :db/ident])))

(declare anode)

(defn datom-view []
  (fn [[e a v t]]
    (let [r         @(subscribe [::mem-browser/node v])
          attribute @(subscribe [::mem-browser/schema-attribute a])]
      ;(js/console.log "A" attribute)
      (if r
        [anode {:details r}]
        [:div [:span
               {:class (when (= :db.type/ref (-> attribute :db/valueType :db/ident))
                         "badge badge-dark")
                :on-click (fn []
                                                   (dispatch [::mem-graph/load-node (str v)]))}
               (str v)]]))))

(defn grouping []
  (fn [[att-id datoms]]
    (let [attr-details @(subscribe [::mem-browser/schema-attribute att-id])]
      [:div.row
       [:div.col.col-auto (-> attr-details :db/ident str)]
       (into [:div.col ]
             (map (fn [d] [datom-view d]) datoms))])))

(defn anode []
  (fn [{:keys [details schema]}]
    (js/console.log "D" details)
    (into [:span.aentity.d-flex.flex-column.m-2]
          (map (fn [e]
                 [grouping e])
               (group-by second details)))))

(defn main []
  (let [schema  (subscribe [::mem-browser/schema])
        in-view (subscribe [::mem-browser/in-view])]
    (fn []
      [:div.text-monospace.small

       #_[:div.agrid
        [:div "test"]
        [:div "test2"]]

       [:button.btn.btn-dark {:on-click (fn []
                                          ;(dispatch [::mem-graph/fetch-node 3078632563232929])
                                          ;(dispatch [::mem-graph/fetch-node "52578646044903633"])
                                          ;(dispatch [::mem-graph/fetch-node "52578646044903633"])
                                          (dispatch [::mem-graph/fetch-node "34159627256401032"])
                                          ;(dispatch [::mem-graph/fetch-node (lo/fromString "999999999999999999")])

                                          )} "load node"]
       [anode {:details @in-view :schema @schema}]])))

