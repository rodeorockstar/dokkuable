(ns archo.views.canvas
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe]]
            [archo.views.wizard :as wizard]
            [archo.mem.canvas :as mem-canvas]
            [archo.views.kinds.pdf :as pdf]))

(defn story []
  (fn [{:keys [label]}]
    [:div.col-sm-12.col-md-3
     [:div.card.mb-4
      [:div.card-body
       [:h4 label]
       [:div "Testing"]]]]))


(defn main []
  (let [deliveries (subscribe [::mem-canvas/deliveries])]
    (fn []
      [:div.canvas-container.vh-100
       [:div.container
        (into [:div.row]
              (map
                (fn [b] [story b])
                @deliveries))]]

      [wizard/main]
      ;[pdf/main]
      )))