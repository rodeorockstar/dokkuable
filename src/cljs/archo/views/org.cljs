(ns archo.views.org
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.upload :as mem-upload]
            [archo.mem.assets :as mem-assets]
            [archo.mem.events :as mem-events]
            [archo.views.kinds.pdf :as pdf]
            [archo.routes :as routes]
            ))


(defn main []
  (let [active-spaces (subscribe [::mem-assets/active-assets :spaces])
        active-route (subscribe [::mem-events/active-route])]
    (fn []
      (js/console.log "AS" @active-spaces)
      [:div
       [:h1 "Spaces"]
       (into [:div.list-group]
             (map (fn [[k {:keys [space/title node/uuid] :as details}]]
                    [:a.list-group-item.list-group-item-action
                     {:href (routes/url-for :route/space {:space/uuid uuid
                                                          :org/short-name (-> @active-route :parameters :path :org/short-name)
                                                          :banana true})}
                     title]) @active-spaces))])))