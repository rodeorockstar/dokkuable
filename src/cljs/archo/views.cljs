(ns archo.views
  (:require
    [re-frame.core :as re-frame :refer [subscribe]]
    [archo.views.home :as home]
    [archo.views.org :as org]
    [archo.views.space :as space]
    [archo.mem.events :as mem-events]
    [archo.views.wizard :as wizard]
    [archo.ui.navbar :as navbar]
    [archo.views.fs :as fs]
    [reagent.core :as r]))

(defn activate []
  (fn []
    [:div.container.pt-3.w-25
     [:div.alert.alert-info "Activating"]]))

(defn- panels [panel-name]
  [:div.container
   (case panel-name
     :route/home [wizard/main]
     :route/org [wizard/main]
     :route/space [space/main]
     :route/files [fs/main]
     ;[:div.test-container "404"]
     [wizard/main]
     )])

(defn show-panel []
  (fn [active-route]
    [:div
     [navbar/main]
     [panels active-route]]))

(defn app []
  (let [active-panel (subscribe [::mem-events/active-route])]
    [show-panel (-> @active-panel :data :name)]
    ))
