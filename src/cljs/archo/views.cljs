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
    [archo.views.uploader :as uploader]
    [reagent.core :as r]
    ["react-particles-js" :refer [Particles]]))

; http://localhost:5000/org/cbs/space/1ac3385f-39d8-4673-8697-95ddb3f80810
; http://localhost:5000/org/cbs/space/1ac3385f-39d8-4673-8697-95ddb3f80810/files

; http://localhost:5000/org/rcsi/space/a0701313-a516-4edc-a6e6-2ecbde4ba09f/split


(defn field []
  [:div.bg-dark.w-100
   #_[:> Particles {:params {:particles {:number {:value 50}
                                         :size   {:value 3}}}}]
   [:> Particles {:params {:particles
                                          {:number
                                                  {:value 160,
                                                   :density {:enable true, :value_area 800}},
                                           :color {:value "#ffffff"},
                                           :shape
                                                  {:type "circle",
                                                   :stroke {:width 0, :color "#000000"},
                                                   :polygon {:nb_sides 5},
                                                   :image
                                                   {:src "img/github.svg",
                                                    :width 100,
                                                    :height 100}},
                                           :opacity
                                                  {:value 1,
                                                   :random true,
                                                   :anim
                                                   {:enable true,
                                                    :speed 1,
                                                    :opacity_min 0,
                                                    :sync false}},
                                           :size
                                                  {:value 3,
                                                   :random true,
                                                   :anim
                                                   {:enable false,
                                                    :speed 4,
                                                    :size_min 0.3,
                                                    :sync false}},
                                           :line_linked
                                                  {:enable false,
                                                   :distance 150,
                                                   :color "#ffffff",
                                                   :opacity 0.4,
                                                   :width 1},
                                           :move
                                                  {:enable true,
                                                   :speed 1,
                                                   :direction "none",
                                                   :random true,
                                                   :straight false,
                                                   :out_mode "out",
                                                   :bounce false,
                                                   :attract
                                                   {:enable false,
                                                    :rotateX 600,
                                                    :rotateY 600}}},
                           #_#_:interactivity
                                          {:detect_on "canvas",
                                           :events
                                                      {:onhover {:enable true, :mode "bubble"},
                                                       :onclick {:enable true, :mode "repulse"},
                                                       :resize true},
                                           :modes
                                                      {:grab
                                                                {:distance 400, :line_linked {:opacity 1}},
                                                       :bubble
                                                                {:distance 250,
                                                                 :size 0,
                                                                 :duration 2,
                                                                 :opacity 0,
                                                                 :speed 3},
                                                       :repulse {:distance 400, :duration 0.4},
                                                       :push {:particles_nb 4},
                                                       :remove {:particles_nb 2}}},
                           :retina_detect true}}]])

(defn activate []
  (fn []
    [:div.container.pt-3.w-25
     [:div.alert.alert-info "Activating"]]))

(defn- panels [panel-name]
  [:div.container
   ;(js/console.log "panel-name" panel-name)
   (case panel-name
     :route/home [wizard/main]
     :route/split [wizard/main]
     ;:route/home [home/main]
     ;:route/org [wizard/main]
     :route/org [org/main]
     ;:route/space [org/some-space]
     :route/space [wizard/main]
     :route/org-home [org/main]
     :route/upload [uploader/main]
     ;:route/space [space/main]
     :route/files [fs/main]
     ;[:div.test-container "404"]
     [wizard/main]
     )])

(defn show-panel []
  (fn [active-route]
    [:div
     [navbar/main]
     ;[field]
     [panels active-route]]))

(defn app []
  (let [active-panel (subscribe [::mem-events/active-route])]
    [show-panel (-> @active-panel :data :name)]
    ))
