(ns archo.views.kinds.pdf
  (:require
    [oops.core :refer [ocall]]
    [re-frame.core :refer [dispatch subscribe]]
    ["react-pdf" :as react-pdf :refer [Document Page]]
    [reagent.core :as r]
    [oops.core :refer [oget]]
    [archo.mem.upload :as mem-upload]
    [cljs-bean.core :refer [bean ->clj ->js]]
    [promesa.core :as p]))


(defn page []
  (let [page-number (r/atom nil)]
    (fn [{:keys [page selected]}]
      (let [nodes @(subscribe [::mem-upload/page-nodes "cms-sandbox.obrizum" "cms/playground/sample.pdf" page])]
        [:div.position-relative.d-flex.flex-column {:class (when (contains? selected @page-number) "pdf-selected shadow border rounded" #_"border-success shadow")}
         [:> Page
          {:pageNumber            page
           :renderTextLayer       false
           :renderAnnotationLayer false
           :width                 200
           :className             "page"
           :on-click              (fn []
                                    (dispatch [::mem-upload/select-page @page-number]))
           :on-load-success       (fn [e]
                                    (reset! page-number (aget e "pageNumber"))
                                    (p/then (ocall e :getTextContent)
                                            (fn [x]
                                              ;(js/console.log "X" x)
                                              )))
           :on-render-success     (fn [e]

                                    (dispatch [::mem-upload/render-page-success (aget e "pageNumber")])
                                    )
           }]
         (into [:<>]
               (map (fn [n]
                      [:div.node "(str n)"]) nodes) )
         #_[:div [:i.fal.fa-search-plus]
          (into [:ul]
                (map (fn [n]
                       (when n (js/console.log "N" n))
                       [:li [:pre (str n)]]) nodes) )]
         ]))))


(defn main []
  (let [page-count     (r/atom 0)
        selected-pages (subscribe [::mem-upload/selected-pages])
        all-groups     (subscribe [::mem-upload/all-groups])

        ]
    (fn [{:keys [file]}]

      [:div
       [:div
        [:button.btn.btn-primary
         {:on-click (fn [] (dispatch [::mem-upload/store-selection]))}
         "Make Node"]

        [:button.button.is-primary
         {:on-click (fn [] (dispatch [::mem-upload/save-nodes @all-groups]))}
         "SERVER"]

        [:button.button.is-primary
         {:on-click (fn [] (dispatch [::mem-upload/fetch-nodes-from-object "cms-sandbox.obrizum" "cms/playground/sample.pdf"]))}
         "ORIGIN"]

        ]
       [:pre (str @selected-pages)]
       [:pre (str @all-groups)]

       (into [:> Document
              {:file            file
               :className       "document"
               :rotate          0
               :on-load-success (fn [e]
                                  (js/console.log "E" (aget e "numPages"))
                                  (reset! page-count (aget e "numPages")))}]
             (map (fn [p] [page {:page     p
                                 :selected @selected-pages}]) (take 50 (range 1 (inc @page-count)))))])))