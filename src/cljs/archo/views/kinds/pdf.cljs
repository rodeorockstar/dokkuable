(ns archo.views.kinds.pdf
  (:require
    [oops.core :refer [ocall]]
    [re-frame.core :refer [dispatch subscribe]]
    ["react-pdf" :as react-pdf :refer [Document Page Outline]]
    [reagent.core :as r]
    [oops.core :refer [oget]]
    [archo.mem.upload :as mem-upload]
    [cljs-bean.core :refer [bean ->clj ->js]]
    [promesa.core :as p]
    [archo.mem.assets :as mem-assets]
    [archo.mem.view :as mem-view]
    [cuerdas.core :as cuerdas]
    ["react-flip-move" :as FlipMove]
    [cljs-bean.core :refer [bean ->clj ->js]]))



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
       :reagent-render          (fn [{v :v s3-key :s3/key node-uuid :node/uuid space-uuid :space/uuid}]
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
                                                                                               ;#_(dispatch [::mem-browser/edit entity a (oget el :target :innerText)])
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

                                     (when @confirm?
                                       [:div.popup.text-dark
                                        [:div.popup-background
                                         {:on-click (fn []
                                                      ;(dispatch [::mem-browser/toggle-modal false])
                                                      )}
                                         [:div.popup-dialog
                                          [:div.popup-body
                                           [:div.row

                                            [:div.col-auto
                                             [:div.media-left
                                              [:figure
                                               [:i.fad.fa-exclamation-triangle.fa-4x.text-warning]]]]
                                            [:div.col
                                             [:div.content
                                              [:p.is-size-5 "You are about to change a value in the database."]
                                              [:p "Do you want to continue?"]
                                              [:div.modal-footer
                                               [:button.btn.btn-primary
                                                {:on-click (fn []
                                                             (dispatch [::mem-upload/rename-node node-uuid @temp-value s3-key space-uuid])
                                                             (reset! confirm? false)
                                                             )}
                                                "Transact"]
                                               [:button.btn.btn-secondary
                                                {:on-click (fn []
                                                             (reset! confirm? false)
                                                             (reset! okey (gensym))
                                                             (reset! temp-value @original-value)
                                                             )}
                                                "Cancel"]]]]

                                            ]]]]
                                        ])
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


(defn modal []
  (let [node-name        (r/atom nil)
        theview          (subscribe [::mem-view/active])
        is-adaptive?     (r/atom true)
        node-kind-select (r/atom :document)]
    (fn [{:keys [s3/key selected-pages file]}]
      [:div.popup
       [:div.popup-background
        [:div.popup-dialog
         [:div.popup-body
          ;[:h1 (str "pu" key)]

          [:div.row
           [:div.col-auto
            [:> Document
             {:file            (str "https://s3-eu-west-1.amazonaws.com/cms-sandbox.obrizum/" (js/encodeURIComponent file))
              :className       "document"
              :rotate          0
              :on-load-success (fn [e]
                                 ;(js/console.log "E" (aget e "numPages"))
                                 ;(js/console.log "D" e)
                                 )}
             [:> Page
              {:pageNumber            (first selected-pages)
               :renderTextLayer       true
               :renderAnnotationLayer false
               :width                 200
               :className             "page"

               :on-load-success       (fn [e]
                                        ;(reset! page-number (aget e "pageNumber"))
                                        ;(js/console.log "PAGENU" e)
                                        (p/then (ocall e :getTextContent)
                                                (fn [x]
                                                  (let [[{font-name :fontName} :as lines] (drop-while
                                                                                            (fn [s]
                                                                                              (clojure.string/blank? (-> s :str clojure.string/trim))
                                                                                              )
                                                                                            (:items (js->clj x :keywordize-keys true)))
                                                        maybe-title (cuerdas/clean (clojure.string/join " " (map :str (take-while (fn [s]
                                                                                                                                    (= font-name (:fontName s))
                                                                                                                                    ) lines))))]
                                                    (reset! node-name maybe-title)
                                                    )
                                                  )))
               :on-render-success     (fn [e]

                                        )
               }]
             ]]
           [:div.col




            [:div.form
             [:div.form-group
              [:label {:for "formGroupExampleInput"} "Title of node"]
              [:input.form-control {:type      "text"
                                    :value     @node-name
                                    :on-change (fn [e]
                                                 (reset! node-name (not-empty (oget e :target :value))))}]]

             [:div.form-group
              [:div.form-check
               [:input#gridCheck.form-check-input {:type     "checkbox"
                                                   :on-click (fn [] (swap! is-adaptive? not))
                                                   :checked  @is-adaptive?}]
               [:label.form-check-label {:for "gridCheck"} "Adaptive?"]]]


             [:div.form-group
              [:label "Choose which kind of node to create"]
              (into [:select.form-control {:value     @node-kind-select
                                           :on-change (fn [e] (reset! node-kind-select (keyword (oget e :target :value))))}]
                    (map (fn [k] [:option k]) ["document" "paper" "product"]))]

             ]


            #_[:div.form
               [:div.form-group
                [:label "Example label"]
                [:input {:type      "text"
                         :value     @node-name
                         :on-change (fn [e]
                                      (reset! node-name (not-empty (oget e :target :value))))}]]]


            [:button.btn.btn-outline-secondary
             {:on-click (fn []
                          (dispatch [::mem-upload/show-modal false])
                          (dispatch [::mem-upload/clear-selection])
                          )}
             "Cancel"]

            [:button.btn.btn-primary.ml-2
             {:on-click (fn []
                          (dispatch [::mem-upload/store-selection {:s3/key         key
                                                                   :title          @node-name
                                                                   :node/adaptive? @is-adaptive?
                                                                   :node/kind      (or @node-kind-select :document)
                                                                   :space/uuid     (:space/uuid @theview)}])
                          )}
             "Create Node"]

            ]]]]]])))


(defn page []
  (let [page-number (r/atom nil)
        theview     (subscribe [::mem-view/active])]
    (fn [{:keys [page selected s3-key]}]
      (let [nodes             @(subscribe [::mem-upload/page-nodes "cms-sandbox.obrizum" s3-key page])
            is-last-selected? (and (some? @page-number) (some? selected) (= @page-number (apply max selected)))
            selected?         (contains? selected @page-number)]
        (js/console.log "XXXXXX" @page-number (apply max selected))
        [:div.position-relative.d-flex.flex-column.m-2.text-truncated
         {:class (cond
                   (contains? selected @page-number) ["is-selected border-success"] #_["pdf-selected" "shadow" "border" "border-primary" "rounded"]
                   nodes ["has-nodes" "border-success" "shadow"]

                   :else [nil]

                   )}
         [:div (str "Page " @page-number)]
         [:> Page
          {:pageNumber            page
           :renderTextLayer       (contains? selected @page-number)
           :renderAnnotationLayer false
           :width                 200
           :className             "page d-flex flex-grow-1"
           :on-click              (fn []
                                    (when-not selected?
                                      (dispatch [::mem-upload/select-page @page-number])))
           :on-load-success       (fn [e]
                                    (reset! page-number (aget e "pageNumber"))
                                    ;(js/console.log "PAGENU" e)
                                    (p/then (ocall e :getTextContent)
                                            (fn [x]
                                              ;(js/console.log "X" x)
                                              )))
           :on-render-success     (fn [e]

                                    (dispatch [::mem-upload/render-page-success (aget e "pageNumber")])
                                    )
           }]
         (when is-last-selected?
           [:button.btn.btn-success
            {:on-click (fn []
                         ;(dispatch [::mem-upload/store-selection {:s3/key s3-key}])
                         (dispatch [::mem-upload/show-modal true])
                         )}
            "Make Node"])

         (into [:<>]
               (map (fn [n]
                      ^{:key (str "a" (:node/uuid n) (-> n :text/title :lang/en))}
                      [:div.node-toolbar.border-bottom
                       {:class (if (:node/adaptive? n)
                                 "bg-primary text-light" "bg-secondary text-light")}



                       [:div.toolbar-title.text-truncate
                        #_[editable {:v          "This is a value"
                                     :node/uuid  (:node/uuid n)
                                     :s3/key     s3-key
                                     :space/uuid (:space/uuid @theview)}]
                        [:div [editable {:v          (-> n :text/title :lang/en)
                                         :node/uuid  (:node/uuid n)
                                         :s3/key     s3-key
                                         :space/uuid (:space/uuid @theview)}]]
                        [:div.font-weight-light (if (:node/adaptive? n) "(Adaptive)" "(Linear)")]]
                       [:button.btn.btn-danger.btn-sm.ml-2
                        {:on-click (fn []
                                     (dispatch [::mem-upload/delete-node (:node/uuid n) s3-key (:space/uuid @theview)]))}
                        [:i.fas.fa-trash]]

                       #_(if (:node/adaptive? n)

                           [:span.badge-primary [:i.fas.fa-chart-] (str (-> n :text/title :lang/en)) " (Adaptive)"]
                           [:span.badge-secondary (str (-> n :text/title :lang/en) " (Non Adaptive")]

                           )

                       ]
                      #_[:span.badge
                         {:class (if (:node/adaptive? n) "badge-success" "badge-secondary")}

                         (-> n :text/title :lang/en)]
                      ) nodes))
         #_[:div [:i.fal.fa-search-plus]
            (into [:ul]
                  (map (fn [n]
                         (when n (js/console.log "N" n))
                         [:li [:pre (str n)]]) nodes))]
         ]))))


(defn main []
  (let [page-count     (r/atom 0)
        selected-pages (subscribe [::mem-upload/selected-pages])
        all-groups     (subscribe [::mem-upload/all-groups])
        stage-file     (subscribe [::mem-upload/stage-file])
        show-modal?    (subscribe [::mem-upload/show-modal?])
        show-count     (subscribe [::mem-upload/show-count])

        ]
    (fn [{:keys [file]}]


      [:div

       #_[:button.btn.btn-outline-secondary
          {:on-click (fn [] (dispatch [::mem-assets/fetch-orgs]))}
          "ORGS"]

       #_[:div
          [:button.btn.btn-primary
           {:on-click (fn [] (dispatch [::mem-upload/store-selection {:s3/key (some-> @stage-file (oget :name))}]))}
           (str "Make Node" (some-> @stage-file (oget :name)))]

          [:button.button.is-primary
           {:on-click (fn [] (dispatch [::mem-upload/save-nodes @all-groups]))}
           "SERVER"]

          [:button.button.is-primary
           {:on-click (fn [] (dispatch [::mem-upload/fetch-nodes-from-object "cms-sandbox.obrizum" "cms/playground/sample.pdf"]))}
           "ORIGIN"]

          ]
       ;[:pre (str @selected-pages)]
       ;[:pre (str @all-groups)]

       (into [:> Document
              {
               ;:file            file
               :file            (str "https://s3-eu-west-1.amazonaws.com/cms-sandbox.obrizum/" (js/encodeURIComponent file))
               ;:file            "https://s3-eu-west-1.amazonaws.com/cms-sandbox.obrizum/2019%20Davies_Ability_of_an_Arterial_Waveform_Analysis.pdf"
               :className       "document"
               :rotate          0
               :on-load-success (fn [e]
                                  ;(js/console.log "E" (aget e "numPages"))
                                  ;(js/console.log "D" e)
                                  (reset! page-count (aget e "numPages")))}
              #_[:> Outline
                 {:on-load-success (fn [e]
                                     (js/console.log "OUTLINE" e))}]
              ]
             (map (fn [p] [page {:page     p
                                 ;:s3-key   (some-> @stage-file (oget :name))
                                 :s3-key   file
                                 :selected @selected-pages}]) (take @show-count (range 1 (inc @page-count)))))


       (when (<= (or @show-count 0) (or @page-count 0))
         [:button.btn.btn-primary.btn-lg
          {:on-click (fn []
                       (dispatch [::mem-upload/change-show-count + 50])
                       )}
          "Display 50 more pages"])

       (when @show-modal?
         [modal
          {:selected-pages @selected-pages
           ;:s3/key         (some-> @stage-file (oget :name))
           :s3/key         file
           :file           file
           }])

       ])))