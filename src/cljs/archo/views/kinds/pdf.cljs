(ns archo.views.kinds.pdf
  (:require
    [oops.core :refer [ocall]]
    [re-frame.core :refer [subscribe]]
    ["react-pdf" :as react-pdf :refer [Document Page]]
    [reagent.core :as r]
    [oops.core :refer [oget]]
    [cljs-bean.core :refer [bean ->clj ->js]]))


(defn page []
  (let [page-number (r/atom nil)]
    (fn [p]
      [:div.page.border
       [:h4 "A Page"]
       [:> Page
        {:pageNumber            p
         :renderTextLayer       true
         :renderAnnotationLayer true
         :width                200
         :on-click              (fn [] (println @page-number))
         :on-load-success       (fn [e]
                                  (reset! page-number (aget e "pageNumber")))}]])))


(defn main []
  (let [page-count (r/atom 0)]
    (fn [{:keys [file]}]

      (into [:> Document
             {:file            file
              :className       "document"
              :rotate          0
              :on-load-success (fn [e]
                                 (reset! page-count (aget e "numPages")))}]
            (map (fn [p] [page p]) (range 1 (inc @page-count)))))))