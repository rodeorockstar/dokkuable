(ns archo.core
  (:require
    [reagent.core :as r]
    [re-frame.core :as re-frame :refer [dispatch subscribe reg-event-db]]
    [oops.core :refer [oget ocall]]
    [archo.mem.boot :as mem-boot]
    [archo.views.canvas :as canvas]
    ))


(defn main-view []
  (fn []
    [canvas/main]))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (r/render [main-view] (.getElementById js/document "app")))

;; and this is what figwheel calls after each save
(defn ^:after-load re-render []
  (mount-root))

(defn ^:export init []
  (enable-console-print!)
  (re-frame/dispatch-sync [::mem-boot/initialize-db])
  (mount-root))
