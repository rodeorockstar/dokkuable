(ns archo.views.browser
  (:require [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [ocall oget]]
    ;[archo.mem.assets :as mem-assets]
    ;[archo.views.kinds.pdf :as pdf]
    ;[archo.mem.graph :as mem-graph]
    ;[archo.views.wizard :as wizard]
            [archo.mem.browser :as mem-browser]
            [archo.routes :as routes]
            [reagent.core :as r]
            ["react-flip-move" :as FlipMove]
    ;[goog.math.Long :as lo]
            ))

(defn is-many? [schema attribute]
  (= :db.cardinality/many (get-in schema [attribute :db/cardinality :db/ident])))

(defn is-ref? [schema attribute]
  (= :db.type/ref (get-in schema [attribute :db/valueType :db/ident])))





(def icon-map
  {
   :db.type/ref     [:i.fad.fa-share]
   :db.type/uuid    [:i.fad.fa-globe-africa]
   :db.type/string  [:i.fad.fa-text]
   :db.type/keyword [:div "kw"]
   })

(defn attr->icon [attr]
  (get icon-map (-> attr :db/valueType :db/ident) [:i.fad.fa-question]))

(defn entity []
  (fn [{:keys [id trail entity cursor]}]
    ;(js/console.log "cursor" (str id) (first (drop (count trail) cursor)))
    [:div.box.is-family-monospace.entity-card.is-small.content
     [:div.tag;.tag.is-medium
      (str id)]
     [:a.delete.is-medium.is-pulled-right.is-link
      {:href (if-let [t (not-empty (butlast trail))]
               (routes/url-for :route/browser-id {:id-tree (clojure.string/join "" (interpose "-" t))})
               (routes/url-for :route/search))}

      ]
     [:table.table.is-narrow
      (into [:tbody]
            (map (fn [[attribute datoms]]
                   ;(js/console.log "attribute" attribute)
                   [:tr
                    [:td (str (:db/ident attribute))]
                    [:td (attr->icon attribute)]
                    (into [:td]
                          (map (fn [[e a v t]]
                                 [:div
                                  (case (-> attribute :db/valueType :db/ident)
                                    :db.type/ref [:a {:href (routes/url-for :route/browser-id {:id-tree (clojure.string/join "" (interpose "-" (conj (mapv str trail) v)))})
                                                      :class (when (= v (first (drop (count trail) cursor))) "has-background-link has-text-dark")} (str v)]
                                    [:div (str v)])])
                               (sort-by
                                 (fn [[e a v t]]
                                   (str v)
                                   )
                                 datoms)))])
                 (into (sorted-map-by :db/ident) entity)))]]
    ))


(defn selector []
  (let [id (r/atom nil)]
    (fn []
     ; 34159627256401032
      [:section.hero.is-transparent
       [:div.hero-body
        [:div.container.is-fullwidth
         [:div.columns.is-centered
          [:div.column.is-half
           [:form
            {:on-submit (fn [e]
                          ; prevent the form from submitting the page
                          (ocall e :preventDefault)
                          ; and route the user to the explorer
                          (routes/redirect! {:name        :route/browser-id
                                             :path-params {:id-tree @id}})
                          )}
            [:div.field
             ;[:label.label ":db/id"]
             [:div.control
              [:input.input.is-large.has-background-dark.is-family-monospace {:type        "text"
                                                                              :value       @id
                                                                              :placeholder "34159627256401032"
                                                                              :on-change   (fn [d]
                                                                                             (reset! id (oget d :target :value)))}]]]
            [:div.field.is-pulled-right
             [:div.control
              [:button.button.is-link.is-medium {:type "submit"} "Explore"]]]]]]]]])))



(defn main []
  (let [schema    (subscribe [::mem-browser/schema])
        in-view   (subscribe [::mem-browser/in-view])
        root      (subscribe [::mem-browser/root])
        cursor    (subscribe [::mem-browser/cursor])
        all-items (subscribe [::mem-browser/all-items])
        ]
    (fn []

      (js/console.log "allitems" @all-items)

      (into [:div.tree-browser.is-family-monospace]
            (map (fn [item]
                   [entity item])) @all-items)

      [:> FlipMove {:class            "tree-browser2 table-container"
                    :enter-animation  "fade"
                    :appear-animation "fade"
                    :leave-animation  "fade"
                    }
       (for [item @all-items]
         ^{:key (-> item :id str)}
         [entity (assoc item :cursor @cursor)])]

      )))

; http://localhost:5000/explore/34159627256401032-34159627256423753-34841324465644688-34841324465644715-37291036372329644-37291036372329650-34062870230265719

