(ns archo.views.wizard
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.upload :as mem-upload]
            [archo.views.kinds.pdf :as pdf]))

(defn wiz []
  (fn []
    [:div
     [:i.fas.fa-circle]]))

(defn main []
  (let [file (subscribe [::mem-upload/stage-file])]
    (fn []
      [:div.container


       ;[:h1 "Open PDF"]
       [wiz]
       [:input {:type      "file"
                :on-change (fn [e]

                             (dispatch [::mem-upload/store-stage-file
                                        (first (array-seq (oget e :target :files)))]))}]
       (when @file
         [pdf/main {:file @file}])])))