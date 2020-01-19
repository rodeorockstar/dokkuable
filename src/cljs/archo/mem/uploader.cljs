(ns archo.mem.uploader
  (:require [re-frame.core :refer [reg-sub reg-event-db trim-v reg-event-fx]]
            [clojure.set :as set]
            [archo.fx :as fx]
            [oops.core :refer [oget oset!]]
            [cemerick.url :refer (url url-encode)]
            [cljs.core.async :as async]
            [cljs-bean.core :refer [bean ->clj]]))


(defn add-file [{db :db} [files]]
  {:db (assoc-in db [:uploader :files] (array-seq files))})

(reg-event-fx ::add-file trim-v add-file)

(reg-sub ::files (fn [db] (map ->clj (get-in db [:uploader :files]))))
(reg-sub ::file-details (fn [db] (map bean (get-in db [:uploader :files]))))
(reg-sub ::progress-chan (fn [db] (get-in db [:uploader :progress-chan])))

(defn upload-file [{db :db} [files org-name]]

  (js/console.log "ON" files org-name)

  (let [progress-chan (async/chan)]
    {
     :db      (assoc-in db [:uploader :progress-chan] progress-chan)
     ::fx/api {
               ; match the API endpoint via its stored name in the router
               :uri              (str "/assets/upload")
               :method           :post
               :multipart-params (into [["org" org-name]]
                                       (map (fn [file] ["file" file]) files))
               :progress         progress-chan
               ;:params     {:node/uuid node-uuid
               ;             :lang/en   lang-en}
               :on-success       [::upload-file-success]
               }}))



(reg-event-fx ::upload-file trim-v upload-file)

;;;;

(defn upload-file-success [{db :db} [response]]
  (js/console.log "RESPONSE" response)
  {:db db})



(reg-event-fx ::upload-file-success trim-v upload-file-success)