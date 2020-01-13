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
    [cljs-bean.core :refer [bean ->clj ->js]]))

(defn modal []
  (let [node-name    (r/atom nil)
        theview      (subscribe [::mem-view/active])
        is-adaptive? (r/atom true)]
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
                                                        maybe-title (clojure.string/join " " (map :str (take-while (fn [s]
                                                                                                                     (= font-name (:fontName s))
                                                                                                                     ) lines)))]
                                                    (reset! node-name maybe-title)
                                                    )
                                                  )))
               :on-render-success     (fn [e]

                                        )
               }]
             ]]
           [:div.col

            [:div @node-name]


            [:div.form
             [:div.form-group
              [:label {:for "formGroupExampleInput"} "Example label"]
              [:input.form-control {:type      "text"
                                    :value     @node-name
                                    :on-change (fn [e]
                                                 (reset! node-name (not-empty (oget e :target :value))))}]]

             [:div.form-group
              [:div.form-check
               [:input#gridCheck.form-check-input {:type "checkbox"
                                                   :on-click (fn [] (swap! is-adaptive? not))
                                                   :checked @is-adaptive?}]
               [:label.form-check-label {:for "gridCheck"} "Check me out"]]]

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

            [:button.btn.btn-primary
             {:on-click (fn []
                          (dispatch [::mem-upload/store-selection {:s3/key         key
                                                                   :title          @node-name
                                                                   :node/adaptive? @is-adaptive?
                                                                   :space/uuid     (:space/uuid @theview)}])
                          )}
             "Make Node"]

            ]]]]]])))


(defn page []
  (let [page-number (r/atom nil)]
    (fn [{:keys [page selected s3-key]}]
      (let [nodes             @(subscribe [::mem-upload/page-nodes "cms-sandbox.obrizum" s3-key page])
            is-last-selected? (= @page-number (apply max selected))
            selected?         (contains? selected @page-number)]
        [:div.position-relative.d-flex.flex-column.m-2.text-truncated
         {:class (cond
                   (contains? selected @page-number) ["is-selected"] #_["pdf-selected" "shadow" "border" "border-primary" "rounded"]
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
           [:button.btn.btn-secondary
            {:on-click (fn []
                         ;(dispatch [::mem-upload/store-selection {:s3/key s3-key}])
                         (dispatch [::mem-upload/show-modal true])
                         )}
            "Make Node"])
         (into [:<>]
               (map (fn [n]
                      (if (:node/adaptive? n)

                        [:span.badge-primary [:i.fas.fa-chart-] (str (-> n :text/title :lang/en)) " (Adaptive)"]
                        [:span.badge-secondary (str (-> n :text/title :lang/en) " (Non Adaptive")]

                        )
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