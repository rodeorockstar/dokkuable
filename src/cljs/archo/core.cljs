(ns archo.core
  (:require
    [reagent.core :as r]
    [re-frame.core :as re-frame :refer [dispatch subscribe reg-event-db]]
    [oops.core :refer [oget ocall]]
    [archo.mem.boot :as mem-boot]
    [archo.views.canvas :as canvas]
    [archo.views.home :as home]
    [archo.routes :as routes]
    [archo.views :as views]
    ))


(defn main-view []
  (fn []
    [canvas/main]
    [home/main]))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (r/render [views/app] (.getElementById js/document "app")))

;; and this is what figwheel calls after each save
(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  (enable-console-print!)

  (re-frame/dispatch-sync [::mem-boot/initialize-db])
  (routes/init!)
  (mount-root))
