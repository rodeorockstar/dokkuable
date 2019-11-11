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

            [goog.math.Long :as lo]
            ))

(defn is-many? [schema attribute]
  (= :db.cardinality/many (get-in schema [attribute :db/cardinality :db/ident])))

(defn is-ref? [schema attribute]
  (= :db.type/ref (get-in schema [attribute :db/valueType :db/ident])))



(defn editable []
  (let [el        (r/atom nil)
        state     (r/atom nil)
        new-state (r/atom nil)
        editable? (r/atom true)]
    (r/create-class
      {:reagent-render (fn [entity attribute value]
                         [:div.can-edit {:style {:position "relative"}}
                          [:div.editable {
                                          :contentEditable         @editable?
                                          ;:class (when @editable? "is-editing has-background-info")
                                          :ref                     (fn [e] (reset! el e))
                                          :dangerouslySetInnerHTML {:__html value}
                                          :on-focus                (fn [e] (reset! state (oget e :target :innerText)))
                                          :on-blur                 (fn [e]
                                                                     (when (not= @state (oget e :target :innerText)) (dispatch [::mem-browser/edit entity attribute (oget e :target :innerText)]))
                                                                     ;(reset! editable? false)
                                                                     )
                                          :on-key-up               (fn [e] (if (= 13 (oget e :keyCode)) (-> e (oget :target) (ocall :blur))))
                                          :on-key-down             (fn [e]
                                                                     (when (= 13 (oget e :keyCode))
                                                                       (do
                                                                         (ocall e :preventDefault)
                                                                         (ocall e :stopPropagation)
                                                                         (-> e (oget :target) (ocall :blur)))))
                                          }]
                          #_[:div.controls.has-background-info {:style {:position "relative"
                                                                      ;:bottom   "-30px"
                                                                      :width    "100%"
                                                                      :padding "10px"
                                                                      }}
                           [:i.fad.fa-pencil
                            {:on-click (fn []
                                         (swap! editable? not)
                                         (js/setTimeout
                                           (fn [] (ocall @el :focus))
                                           1))}]]])})))

(def icon-map
  {
   :db.type/ref     [:i.fad.fa-share]
   :db.type/uuid    [:i.fad.fa-globe-africa]
   :db.type/string  [:i.fad.fa-text]
   :db.type/boolean [:i.fad.fa-toggle-on]
   :db.type/keyword [:div "kw"]
   })

(defn attr->icon [attr]
  (get icon-map (-> attr :db/valueType :db/ident) [:i.fad.fa-question]))

(defn entity []
  (fn [{:keys [id trail entity cursor]}]
    ;(js/console.log "cursor" (str id) (first (drop (count trail) cursor)))
    [:div.box.is-family-monospace.entity-card.is-small.content
     [:div.is-pulled-left.has-text-link ;.tag.is-medium
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
                                 [:span
                                  (case (-> attribute :db/valueType :db/ident)
                                    :db.type/ref [:a.tag {
                                                          :href  (routes/url-for :route/browser-id {:id-tree (clojure.string/join "" (interpose "-" (conj (mapv str trail) v)))})
                                                          :class (when (= (lo/fromString (str v)) (first (drop (count trail) cursor))) "is-link")
                                                          }
                                                  (str v)]
                                    ;[:div {:contentEditable true} (str v)]
                                    [editable e a v]
                                    )])
                               (sort-by
                                 (fn [[e a v t]]
                                   (str v)
                                   )
                                 datoms)))])
                 (into (sorted-map-by :db/ident) entity)))]]
    ))


(defn selector []
  (let [id     (r/atom nil)
        unique (subscribe [::mem-browser/unique-idents])]
    (fn []
      ; 34159627256401032
      [:section.hero.is-transparent.is-family-monospace
       [:div.hero-body
        [:div.container.is-fullwidth
         [:div.columns.is-centered
          [:div.column.is-half
           [:form
            {:on-submit (fn [e]
                          ; prevent the form from submitting the page
                          (ocall e :preventDefault)
                          ; and route the user to the explorer
                          #_(routes/redirect! {:name        :route/browser-id
                                               :path-params {:id-tree @id}})
                          (dispatch [::mem-browser/search])

                          )}
            [:div.field
             ;[:label.label ":db/id"]
             [:div.control
              [:input.input.is-large.has-background-dark.is-family-monospace {:type        "text"
                                                                              :value       @id
                                                                              :placeholder "34159627256401032"
                                                                              :on-change   (fn [d]
                                                                                             (reset! id (oget d :target :value)))}]]]
            [:div.select.is-family-monospace
             (into [:select.has-background-dark.is-family-monospace]
                   (map (fn [o] [:option (-> o :db/ident str)]) @unique))]
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
      [:div
       #_[:div.notification.is-link
        [:h4 "hi"]]
       #_[:button.button
          {:on-click (fn [] (dispatch [::mem-browser/search]))}
          "Search"]
       (let [c @cursor]
         [:> FlipMove {:class            "tree-browser table-container"
                       :enter-animation  "fade"
                       :appear-animation "fade"
                       :leave-animation  "fade"
                       }
          (for [item @all-items]
            ^{:key (-> item :id str)}
            [entity (assoc item :cursor c)])])]

      )))

; http://localhost:5000/explore/34159627256401032-34159627256423753-34841324465644688-34841324465644715-37291036372329644-37291036372329650-34062870230265719


; http://localhost:5000/explore/34159627256401032-34159627256401033

; http://localhost:5000/explore/17376681767001057

