(ns archo.views
  (:require
    [re-frame.core :as re-frame :refer [subscribe]]
    [archo.views.home :as home]
    [archo.views.org :as org]
    [archo.mem.events :as mem-events]
    [reagent.core :as r]))

(defn activate []
  (fn []
    [:div.container.pt-3.w-25
     [:div.alert.alert-info "Activating"]]))

(defn- panels [panel-name]
  [:div.container
   (case panel-name
     :route/home [home/main]
     :route/org [org/main]
     [:div.test-container "404"])])

(defn show-panel []
  (fn [active-route]
    [panels active-route]))

(defn app []
  (let [active-panel (subscribe [::mem-events/active-route])]
    [show-panel (-> @active-panel :data :name)]
    ))
