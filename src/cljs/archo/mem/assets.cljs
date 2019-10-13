(ns archo.mem.assets
  (:require [archo.fx :as fx]
            [re-frame.core :refer [reg-sub reg-event-db reg-event-fx reg-sub trim-v]]))

(def assets {:orgs {:endpoint "/assets/org"
                    :store    [:assets :orgs]}})

(defn fetch-asset [world [asset-kind]]
  {::fx/api {:uri        (get-in assets [asset-kind :endpoint])
             :on-success [::fetch-asset-success (get-in assets [asset-kind :store])]}})

(defn fetch-asset-success [db [where results]]
  (assoc-in db where results))

(reg-event-fx ::fetch-asset trim-v fetch-asset)
(reg-event-db ::fetch-asset-success trim-v fetch-asset-success)

(reg-sub ::assets (fn [db [_ key]]
                    (sort-by (comp :org/name last) (get-in db [:assets key]))))
