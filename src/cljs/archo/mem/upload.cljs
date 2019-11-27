(ns archo.mem.upload
  (:require [re-frame.core :refer [reg-sub reg-event-db trim-v reg-event-fx]]
            [clojure.set :as set]
            [archo.fx :as fx]
            [cemerick.url :refer (url url-encode)]))

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

(defn store-selection [db]
  (-> db
      (update-in [:stage :selections] conj (get-in db [:stage :selected]))
      (update :stage dissoc :selected)))

(reg-event-db ::store-selection trim-v store-selection)

(defn all-selected-pages [db]
  (apply set/union (get-in db [:stage :selections])))

(defn all-groups [db]
  (get-in db [:stage :selections]))

(reg-sub ::all-selected-pages all-selected-pages)
(reg-sub ::all-groups all-groups)

;;;;;

(defn save-nodes [_ [pages]]
  (js/console.log "pages" pages)
  {::fx/api {
             ; match the API endpoint via its stored name in the router
             :uri        "/assets/split"
             :method     :post
             :params     {:s3/key      "cms/playground/sample.pdf"
                          :page-groups (map vec pages)}
             :on-success [::save-nodes-success]}})

(defn save-nodes-success [db [response]]
  (js/console.log "RES" response)
  db)

(reg-event-fx ::save-nodes trim-v save-nodes)
(reg-event-db ::save-nodes-success trim-v save-nodes-success)


(defn fetch-nodes-from-object [_ [s3-bucket s3-key]]
  {::fx/api {
             ; match the API endpoint via its stored name in the router
             :uri        (str "/assets/origin/" (url-encode s3-bucket) "/" (url-encode s3-key))
             :method     :get
             :on-success [::save-nodes-from-object s3-bucket s3-key]}})

(defn save-nodes-from-object [db [s3-bucket s3-key response]]
  (update-in db [:objects s3-bucket s3-key] assoc :pages response))

(reg-event-fx ::fetch-nodes-from-object trim-v fetch-nodes-from-object)
(reg-event-db ::save-nodes-from-object trim-v save-nodes-from-object)

(reg-sub ::page-nodes (fn [db [_ s3-bucket s3-key page-number]]
                        (println "doing" s3-bucket s3-key page-number)
                        (-> db :objects (get s3-bucket) (get s3-key) :pages (get page-number))
                        ))