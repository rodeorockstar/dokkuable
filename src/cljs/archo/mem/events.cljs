(ns archo.mem.events
  (:require [re-frame.core :refer [reg-event-db reg-sub]]))

(reg-event-db
  ::set-active-route
  (fn [db [_ active-panel {:keys [park] :as _opts}]]
    (let [old-route (get db :active-route)]
      (assoc db
        :old-route old-route
        :active-route active-panel))))

(reg-sub
  ::active-route
  (fn [db _]
    (:active-route db)))
