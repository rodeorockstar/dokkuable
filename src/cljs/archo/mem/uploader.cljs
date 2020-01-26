(ns archo.mem.uploader
  (:require [re-frame.core :refer [reg-sub reg-event-db trim-v reg-event-fx]]
            [clojure.set :as set]
            [archo.fx :as fx]
            [oops.core :refer [oget oset!]]
            [cemerick.url :refer (url url-encode)]
            [cljs.core.async :as async]
            [archo.mem.assets :as mem-assets]
            [cljs-bean.core :refer [bean ->clj]]))


(defn add-file [{db :db} [files]]
  {:db (assoc-in db [:uploader :files] (array-seq files))})

(reg-event-fx ::add-file trim-v add-file)

(reg-sub ::files (fn [db] (map ->clj (get-in db [:uploader :files]))))
(reg-sub ::file-details (fn [db] (map bean (get-in db [:uploader :files]))))
(reg-sub ::progress-chan (fn [db] (get-in db [:uploader :progress-chan])))

(defn upload-file [{db :db} [files prefix]]

  ;(js/console.log "ON" files prefix)

  (let [progress-chan (async/chan)]
    {
     :db      (update db :uploader assoc :progress-chan progress-chan :status :uploading)
     ::fx/api {
               ; match the API endpoint via its stored name in the router
               :uri              (str "/assets/upload")
               :method           :post
               :multipart-params (into [["Prefix" prefix]]
                                       (map (fn [file] ["file" file]) files))
               :progress         progress-chan
               ;:params     {:node/uuid node-uuid
               ;             :lang/en   lang-en}
               :on-success       [::upload-file-success prefix]
               }}))



(reg-event-fx ::upload-file trim-v upload-file)

(reg-sub ::uploader-status (fn [db] (get-in db [:uploader :status])))

;;;;

(defn upload-file-success [{db :db} [prefix response]]
  ;(js/console.log "RESPONSE" prefix)
  {:db       (-> db
                 (update :fs dissoc :modal-kw)
                 (dissoc :uploader))
   :dispatch [::mem-assets/ls prefix]})



(reg-event-fx ::upload-file-success trim-v upload-file-success)


;;; video

;; video

(defn make-video [{db :db} [params]]


  {
   ;:db      (update db :stage assoc :storing? true)
   ::fx/api {
             ; match the API endpoint via its stored name in the router
             :uri        (str "/assets/video")
             :method     :post
             :params     params
             :on-success [::video-success]
             }})



(reg-event-fx ::make-video trim-v make-video)

(reg-event-db ::video-success trim-v (fn [db [response]]
                                       (js/console.log "VIDEO RES" response)
                                       db))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


