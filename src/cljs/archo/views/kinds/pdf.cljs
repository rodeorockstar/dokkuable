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
      [:div.border.rounded.m-2 {:class (when (contains? selected @page-number) "border-success shadow")}
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
       [:div [:i.fal.fa-search-plus]]])))


(defn main []
  (let [page-count (r/atom 0)
        selected-pages (subscribe [::mem-upload/selected-pages])]
    (fn [{:keys [file]}]

      [:div
       [:pre (str @selected-pages)]

       (into [:> Document
              {:file            file
               :className       "document"
               :rotate          0
               :on-load-success (fn [e]
                                  (reset! page-count (aget e "numPages")))}]
             (map (fn [p] [page {:page p
                                 :selected @selected-pages}]) (range 1 (inc @page-count))))])))