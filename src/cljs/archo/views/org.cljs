(ns archo.views.org
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.upload :as mem-upload]
            [archo.mem.assets :as mem-assets]
            [archo.mem.events :as mem-events]
            [archo.mem.view :as mem-view]
            [archo.views.kinds.pdf :as pdf]
            [archo.routes :as routes]
            [oops.core :refer [oget]]
            ["react-particles-js" :refer [Particles]]
            ))



(defn space-dropdown []
  (fn [{:keys [spaces on-change]}]
    (into [:select
           {:on-change on-change}]
          (map (fn [o]
                 [:option (-> o :space/title)])
               spaces))))

(defn org-dropdown []
  (fn [{:keys [orgs on-change]}]
    (into [:select
           {:on-change on-change}]
          (map (fn [o]
                 [:option (-> o :org/short-name)])
               orgs))))

(defn space-card []
  (fn [{:keys [node/uuid space/title text/tran]} org-short-name]
    [:a.card.rounded-0.shadow.p-4
     {:href (routes/url-for :route/space {:org/short-name org-short-name
                                          :space/uuid uuid})}
     title]))

(defn main []
  (let [active-spaces (subscribe [::mem-assets/active-assets :spaces])
        active-route  (subscribe [::mem-events/active-route])
        orgs          (subscribe [::mem-assets/orgs])
        spaces        (subscribe [::mem-assets/spaces])
        selected-org  (r/atom nil)
        theview       (subscribe [::mem-view/active])
        xxx       (subscribe [::mem-view/active-org-spaces])
        active-space (subscribe [::mem-view/active-space])
        ]
    (fn []
      (into [:div]
            (map (fn [s] [space-card s (:org/short-name @theview)]) (vals @xxx)))
      )))

(defn some-space []
  (fn []
    [:div "SOME SPACE2"
     (js/console.log "SSS" Particles)
     ]))

(defn main2 []
  (let [active-spaces (subscribe [::mem-assets/active-assets :spaces])
        active-route  (subscribe [::mem-events/active-route])
        orgs          (subscribe [::mem-assets/orgs])
        spaces        (subscribe [::mem-assets/spaces])
        selected-org  (r/atom nil)
        theview       (subscribe [::mem-view/active])
        xxx       (subscribe [::mem-view/active-org-spaces])
        active-space (subscribe [::mem-view/active-space])
        ]
    (fn []
      (js/console.log "tt" @theview)
      ;(js/console.log "AS" @active-spaces)
      ;(js/console.log "ARARAR" @orgs)
      ;(js/console.log "SSSS" (select-keys @spaces (get-in @orgs [@selected-org :org/spaces])))
      [:div
       [:h1 "Spaces"]
       (into [:div.list-group]
             (map (fn [[k {:keys [space/title node/uuid] :as details}]]
                    [:a.list-group-item.list-group-item-action
                     {:href (routes/url-for :route/space {:space/uuid     uuid
                                                          :org/short-name (-> @active-route :parameters :path :org/short-name)
                                                          :banana         true})}
                     title]) @active-spaces))]
      #_(into [:ul]
              (map (fn [[short-name {:keys [org/name org/uuid]}]]
                     (js/console.log "O" short-name)
                     [:li name])) @orgs)

      [:div
       #_[org-dropdown {:orgs      (vals @orgs)
                      :on-change (fn [e]
                                   (reset! selected-org (oget e :target :value))
                                   (js/console.log "X" (oget e :target :value)))}]
       #_[space-dropdown {:spaces (vals (select-keys @spaces (get-in @orgs [@selected-org :org/spaces])))}]


       #_(into [:ul]
             (map (fn [d] [:li ]) (-> @orgs (get))))

       #_[:div (str (get-in @orgs [(:org/short-name @theview) :org/spaces]))]
       #_[:div (str @xxx)]

       #_(into [:div]
             (map (fn [s] [space-card s]) (vals @xxx)))


       [:div.row
        [:div.col.bg-dark.text-light.col-3 "Test"]
        [:div.col.bg-dark.text-light.col-9 "2"]]


       [:div.acard.acard-5 "X"]

       [:code (str @active-space)]



       ]
      )))