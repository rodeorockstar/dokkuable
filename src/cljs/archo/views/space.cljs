(ns archo.views.space
  (:require [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.assets :as mem-assets]
            [archo.views.kinds.pdf :as pdf]
            [archo.views.wizard :as wizard]))


(defn uploader []
  (fn []
    [:div.border-bottom.pb-2
     [:button.btn.btn-primary "upload"]]))

(defn space-header []
  (fn [{:keys [node/kind
               node/uuid
               space/intro-summary
               space/intro-video
               space/title
               text/summary
               text/tran
               view/visibility]}]
    [:h1.display-4.border-bottom title]))

(defn node-counter []
  (fn [count-map]
    (let [total-count (apply + (vals count-map))]
      (into [:div.border-bottom]
            (map (fn [[kind n]]
                   [:div
                    [:div (name kind)]
                    [:div.progress
                     [:div.progress-bar.bg-info {:style {:width (str (* 100 (/ n total-count)) "%")}}]]])
                 (sort-by last > count-map))))))

(defn main []
  (let [active-space-assets (subscribe [::mem-assets/active-space-assets])]
    (fn []
      [:div
       [space-header (-> @active-space-assets :details)]
       ;[uploader]
       [wizard/main]
       ;[node-counter (-> @active-space-assets :node-count)]
       ])))