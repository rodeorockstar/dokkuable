(ns archo.views.home
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.upload :as mem-upload]
            [archo.mem.assets :as mem-assets]
            [archo.views.kinds.pdf :as pdf]
            [archo.routes :as routes]
            [reitit.core :as reitit]))



(defn org-entry []
  (fn [[id {:keys [org/name org/short-name]}]]
    [:div.col-sm-6.col-md-4.col-lg-3
     [:div.card.h-100.p-2 [:div.card-body name]]]
    [:a.list-group-item.list-group-item-action
     {:href (routes/url-for :route/org {:org/short-name short-name})} name]))

(defn org-list []
  (fn [orgs]
    (into [:div.list-group] (map (fn [o] [org-entry o]) orgs))))

(defn main []
  (let [orgs (subscribe [::mem-assets/assets :orgs])]
    (fn []
     [:div
      [:h1 "Organisations"]
      [org-list @orgs]
      [:button {:on-click (fn []
                            (dispatch [::mem-assets/fetch-asset :orgs])
                            )} "click"]])))