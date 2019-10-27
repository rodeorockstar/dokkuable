(ns archo.views
  (:require
    [re-frame.core :as re-frame :refer [subscribe]]
    [archo.views.home :as home]
    [archo.views.org :as org]
    [archo.views.space :as space]
    [archo.mem.events :as mem-events]
    [reagent.core :as r]
    [archo.views.browser :as browser]
    [archo.routes :as routes]
    ))

(defn activate []
  (fn []
    [:div.container.pt-3.w-25
     [:a.btn.btn-primary {:href (routes/url-for :route/explore {:idtree "abc/xyz/123"})} "Click"]
     [:div.alert.alert-info "Activating"]]))

(defn- panels [panel-name]
  [:div.container
   (case panel-name
     ;:route/home [home/main]
     ;:route/home [browser/main]
     :route/browser-id [browser/main]
     ;:route/home [activate]
     :route/org [org/main]
     :route/space [space/main]
     ;:route/browser-id [activate]
     :route/explore [activate]
     [:div.test-container "404"])])

(defn show-panel []
  (fn [active-route]
    [panels active-route]))

(defn app []
  (let [active-panel (subscribe [::mem-events/active-route])]
    [show-panel (-> @active-panel :data :name)]
    ))
