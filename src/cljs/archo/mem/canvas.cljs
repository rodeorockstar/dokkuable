(ns archo.mem.canvas
  (:require [re-frame.core :refer [reg-sub]]))

(defn deliveries
  "Return all buckets in app-db"
  [db _]
  (-> db :deliveries vals))

(reg-sub ::deliveries deliveries)