(ns archo.mem.upload
  (:require [re-frame.core :refer [reg-sub reg-event-db trim-v]]))

(defn store-stage-file [db [f]]
  (assoc-in db [:stage :file] f))

(reg-event-db ::store-stage-file trim-v store-stage-file)

(defn stage-file [db]
  (get-in db [:stage :file]))

(reg-sub ::stage-file stage-file)
