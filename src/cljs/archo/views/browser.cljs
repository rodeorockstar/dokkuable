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

(defn entry []
  (fn [{schema :schema
        [k value] :data}]
    [:div.d-flex.align-items-baseline
     [:div.flex-grow-0.m-1 (if (is-many? schema k)
                             [:i.fas.fa-chevron-right.fa-fw.mr-2]
                             [:i.fas.fa-fw.mr-2]) (str k)]

     (cond
       (and (is-ref? schema k)
            (is-many? schema k)) [:div.badge.badge-dark [:i.fas.fa-layer-group.mr-1] (count value)]
       (is-ref? schema k) [:div.badge.badge-dark [:i.fas.fa-square]]
       :else [:div.text-light (str value)])
     ]))

(defn anode []
  (fn [{:keys [details schema]}]
    (js/console.log "D" schema)
    (into [:span.entity.d-flex.flex-column]
          (map (fn [e]
                 [entry {:schema schema
                         :data e}]) details))))

(defn main []
  (let [schema (subscribe [::mem-browser/schema])
        thenode (subscribe [::mem-browser/thenode])]
    (fn []
     [:div.text-monospace
      [:button.btn.btn-dark {:on-click (fn []
                            ;(dispatch [::mem-graph/fetch-node 3078632563232929])
                            ;(dispatch [::mem-graph/fetch-node "52578646044903633"])
                            (dispatch [::mem-graph/fetch-node "52578646044903633"])
                            ;(dispatch [::mem-graph/fetch-node (lo/fromString "999999999999999999")])

                            )} "load node"]
      ;[entity @schema]
      [anode {:details @thenode :schema @schema}]])))