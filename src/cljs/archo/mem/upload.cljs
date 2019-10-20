(ns archo.mem.upload
  (:require [re-frame.core :refer [reg-sub reg-event-db trim-v]]))

(defn store-stage-file [db [f]]
  (assoc-in db [:stage :file] f))

(reg-event-db ::store-stage-file trim-v store-stage-file)

(defn render-page-success [db [num]]
  (assoc-in db [:stage :pages :rendered num] true))

(reg-event-db ::render-page-success trim-v render-page-success)

(defn stage-file [db]
  (get-in db [:stage :file]))

(reg-sub ::stage-file stage-file)


(defn select-page [db [n]]
  (if (contains? (get-in db [:stage :selected]) n)
    (update-in db [:stage :selected] (fnil disj #{}) n)
    (update-in db [:stage :selected] (fnil conj #{}) n)))

(reg-event-db ::select-page trim-v select-page)

(reg-sub ::selected-pages (fn [db]
                            (apply sorted-set (get-in db [:stage :selected]))))