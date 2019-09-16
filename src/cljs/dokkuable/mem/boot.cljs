(ns dokkuable.mem.boot
  (:require [re-frame.core :refer [reg-event-db]]))

(reg-event-db
  ::initialize-db
  (fn [_ _]
    {}))