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
            [cuerdas.core :as str]
            ["react-flip-move" :as FlipMove]

            [goog.math.Long :as lo]
            ))

(defn is-many? [schema attribute]
  (= :db.cardinality/many (get-in schema [attribute :db/cardinality :db/ident])))

(defn is-ref? [schema attribute]
  (= :db.type/ref (get-in schema [attribute :db/valueType :db/ident])))

(defn modal []
  (let [show? (subscribe [::mem-browser/modal-status])]
    (fn []
      [:div.modal {:class (when @show? "is-active")}
       [:div.modal-background
        {:on-click (fn [] (dispatch [::mem-browser/toggle-modal false]))}]
       [:div.modal-content
        [:div.box
         [:article.media
          [:div.media-left
           [:figure
            [:i.fad.fa-globe-africa]]]
          [:div.media-content
           [:div.content
            [:p "You are about to change a value in the database. Do you want to continue?"]]]]]]
       ])))

(defn editable []
  (let [el             (r/atom nil)
        state          (r/atom nil)
        new-state      (r/atom nil)
        editable?      (r/atom true)
        temp-value     (r/atom nil)
        confirm?       (r/atom false)
        original-value (r/atom nil)
        okey           (r/atom (gensym))]
    (r/create-class
      {:component-did-mount     (fn [this]
                                  (reset! original-value (:v (r/props this)))
                                  (reset! temp-value (:v (r/props this)))
                                  )
       :should-component-update (fn [this]
                                  ;(r/force-update this)
                                  true
                                  )
       :reagent-render          (fn [{:keys [e a v]}]
                                  [:div
                                   ;[:div.has-background-info (str @okey)]
                                   ;[:div.has-background-info (str @original-value)]
                                   ;[:div.has-background-warning (str @temp-value)]
                                   [:div.can-edit {:style {:position "relative"}}

                                    ^{:key @okey} [:div.editable {
                                                                  :contentEditable         @editable?
                                                                  ;:class (when @editable? "is-editing has-background-info")
                                                                  :ref                     (fn [dom-node] (reset! el dom-node))
                                                                  :dangerouslySetInnerHTML {:__html @original-value}
                                                                  :on-focus                (fn [evt]
                                                                                             (reset! state (oget evt :target :innerText))
                                                                                             ;(dispatch [::mem-browser/toggle-modal true])
                                                                                             )
                                                                  :on-blur                 (fn [evt]
                                                                                             (when (not= @state (oget evt :target :innerText))
                                                                                               #_(dispatch [::mem-browser/edit entity a (oget el :target :innerText)])
                                                                                               (reset! confirm? true)
                                                                                               )
                                                                                             ;(reset! editable? false)
                                                                                             )
                                                                  :on-key-up               (fn [evt] (if (= 13 (oget evt :keyCode))
                                                                                                       (-> evt
                                                                                                           (oget :target)
                                                                                                           (ocall :blur))
                                                                                                       (reset! temp-value (oget evt :target :innerText))))
                                                                  :on-key-down             (fn [evt]
                                                                                             (if (= 13 (oget evt :keyCode))
                                                                                               (do
                                                                                                 (ocall evt :preventDefault)
                                                                                                 (ocall evt :stopPropagation)
                                                                                                 (-> evt (oget :target) (ocall :blur)))
                                                                                               ))
                                                                  }]

                                    [:> FlipMove {:class            ""
                                                  :enter-animation  "fade"
                                                  :appear-animation "fade"
                                                  :leave-animation  "fade"
                                                  }
                                     ^{:key (str "modal" a v)}

                                     [:div.modal {:class (when @confirm? "is-active")}
                                      [:div.modal-background
                                       {:on-click (fn [] (dispatch [::mem-browser/toggle-modal false]))}]
                                      [:div.modal-content
                                       [:div.box

                                        [:article.media
                                         [:div.media-left
                                          [:figure
                                           [:i.fad.fa-exclamation-triangle.fa-4x.has-text-warning]]]
                                         [:div.media-content
                                          [:div.content
                                           [:p.is-size-5 "You are about to change a value in the database."]
                                           [:p "Do you want to continue?"]
                                           [:div.field.is-grouped
                                            [:div.control
                                             [:button.button.is-warning
                                              {:on-click (fn []
                                                           (dispatch [::mem-browser/edit e a @temp-value])
                                                           (reset! confirm? false)
                                                           )}
                                              "Transact"]]
                                            [:div.control
                                             [:button.button.is-link.is-light
                                              {:on-click (fn []
                                                           (reset! confirm? false)
                                                           (reset! okey (gensym))
                                                           (reset! temp-value @original-value)
                                                           )}
                                              "Cancel"]]]]]]

                                        ]]
                                      [:button.modal-close.is-large {:aria-label "close"}]]
                                     ]



                                    #_[:div.controls.has-background-info {:style {:position "relative"
                                                                                  ;:bottom   "-30px"
                                                                                  :width    "100%"
                                                                                  :padding  "10px"
                                                                                  }}
                                       [:i.fad.fa-pencil
                                        {:on-click (fn []
                                                     (swap! editable? not)
                                                     (js/setTimeout
                                                       (fn [] (ocall @el :focus))
                                                       1))}]]]])})))

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
    [:div.box.is-family-monospace.entity-card.content.is-small
     [:div.is-pulled-left.has-text-link ;.tag.is-medium
      (str id)]
     [:a.delete.is-medium.is-pulled-right.is-link
      {:href (if-let [t (not-empty (butlast trail))]
               (routes/url-for :route/browser-id {:id-tree (clojure.string/join "" (interpose "-" t))})
               (routes/url-for :route/search))}

      ]
     [:table.table.is-narrow.is-small-content
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
                                    ^{:key (str a e)} [editable {:e e :a a :v v}]
                                    )])
                               (sort-by
                                 (fn [[e a v t]]
                                   v
                                   )
                                 datoms)))])
                 (into (sorted-map-by :db/ident) entity)))]]
    ))


(defn selector []
  (let [id                 (r/atom nil)
        unique             (subscribe [::mem-browser/unique-idents])
        selected-attribute (r/atom nil)]
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
                          (if (and @selected-attribute (not= @selected-attribute :db/id))
                            (dispatch [::mem-browser/search (or @selected-attribute :db/id) @id])

                            (routes/redirect! {:name        :route/browser-id
                                               :path-params {:id-tree @id}}))

                          )}

            [:div.field
             [:div.label "Attribute"]
             [:div.control
              [:div.select.is-family-monospace.is-fullwidth
               (into [:select.is-family-monospace ;.has-background-dark
                      {:on-change (fn [e]
                                    (reset! selected-attribute (keyword (str/ltrim (oget e :target :value) ":"))))}
                      ]
                     (conj (map (fn [o] [:option (-> o :db/ident str)]) @unique) [:option (str :db/id)]))]]
             ]
            [:div.field
             [:div.label "Value"]
             [:div.control.is-expanded
              [:input.input.is-family-monospace.is-fullwidth ;.has-background-dark
               {:type        "text"
                :value       @id
                :placeholder "34159627256401032"
                :on-change   (fn [d]
                               (reset! id (oget d :target :value)))}]]]

            [:div.field.is-pulled-right
             [:div.control
              [:button.button.is-link.is-medium {:type "submit"} "Search"]]]]]]]]])))



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
            [entity (assoc item :cursor c)])])
       [modal]]

      )))

; http://localhost:5000/explore/34159627256401032-34159627256423753-34841324465644688-34841324465644715-37291036372329644-37291036372329650-34062870230265719


; http://localhost:5000/explore/34159627256401032-34159627256401033

; http://localhost:5000/explore/17376681767001057

