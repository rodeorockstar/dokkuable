(ns archo.views.browser
  (:require [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            ;[archo.mem.assets :as mem-assets]
            ;[archo.views.kinds.pdf :as pdf]
            ;[archo.mem.graph :as mem-graph]
            ;[archo.views.wizard :as wizard]
            [archo.mem.browser :as mem-browser]
    [archo.routes :as routes]
            ;[goog.math.Long :as lo]
            ))

(defn is-many? [schema attribute]
  (= :db.cardinality/many (get-in schema [attribute :db/cardinality :db/ident])))

(defn is-ref? [schema attribute]
  (= :db.type/ref (get-in schema [attribute :db/valueType :db/ident])))





(def icon-map
  {
   :db.type/ref [:i.fad.fa-share]
   :db.type/uuid [:i.fad.fa-globe-africa]
   :db.type/string [:i.fad.fa-text]
   :db.type/keyword [:div "kw"]
   })

(defn attr->icon [attr]
  (get icon-map (-> attr :db/valueType :db/ident) [:i.fad.fa-question]))

(defn entity []
  (fn [{:keys [id trail entity]}]
    [:div.mr-3
     [:div.d-flex.justify-content-start [:div.top-label.p-1 (str id)]]
     ;[:div.d-flex.justify-content-end [:div (str trail)]]
     [:table.table (into [:tbody]
                         (map (fn [[attribute datoms]]
                                ;(js/console.log "attribute" attribute)
                                [:tr
                                 [:td (str (:db/ident attribute))]
                                 [:td (attr->icon attribute)]
                                 (into [:td]
                                       (map (fn [[e a v t]]
                                              [:div

                                               (case (-> attribute :db/valueType :db/ident)
                                                 :db.type/ref [:a {:href (routes/url-for :route/browser-id {:id-tree (clojure.string/join "" (interpose "/" (conj (mapv str trail) v)))})} (str v)]
                                                 [:div (str v)])])
                                            datoms))])
                              (into (sorted-map-by :db/ident) entity)))]]
    ))


(defn main []
  (let [schema  (subscribe [::mem-browser/schema])
        in-view (subscribe [::mem-browser/in-view])
        root (subscribe [::mem-browser/root])
        cursor (subscribe [::mem-browser/cursor])
        all-items (subscribe [::mem-browser/all-items])
        ]
    (fn []
      ;(js/console.log "root" @root)
      ;(js/console.log "cursor" @cursor)
      ;(js/console.log "invew" @in-view)
      ;(js/console.log "invew" @in-view)
      (js/console.log "all-items" @all-items)
      [:div.text-monospace.small
       #_[:button.btn.btn-dark {:on-click (fn []
                                          ;(dispatch [::mem-graph/fetch-node 3078632563232929])
                                          ;(dispatch [::mem-graph/fetch-node "52578646044903633"])
                                          ;(dispatch [::mem-graph/fetch-node "52578646044903633"])
                                          (dispatch [::mem-browser/fetch-node "34159627256401032"])
                                          ;(dispatch [::mem-graph/fetch-node (lo/fromString "999999999999999999")])

                                          )} "load node2"]
       ;[anode {:details @in-view :schema @schema}]
       (into [:div.d-flex.align-items-start]
             (map (fn [item]
                    [entity item])) @all-items)
       ])))

