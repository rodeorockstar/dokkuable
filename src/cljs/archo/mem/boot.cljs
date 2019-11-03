(ns archo.mem.boot
  (:require [re-frame.core :refer [reg-event-db]]))

(reg-event-db
  ::initialize-db
  (fn [_ _]
    {
     ;:bignumb    999999999999999999
     ;:thenode 34159627256401032
     :deliveries {"A" {:label "Bucket A"
                       :id    "A"}
                  "B" {:label "Bucket B"
                       :id    "B"}
                  "C" {:label "Bucket C"
                       :id    "C"}
                  "D" {:label "Bucket D"
                       :id    "C"}
                  "E" {:label "Bucket E"
                       :id    "C"}
                  "F" {:label "Bucket F"
                       :id    "C"}
                  "G" {:label "Bucket G"
                       :id    "C"}}
     :datomic    {:schema {}}}))