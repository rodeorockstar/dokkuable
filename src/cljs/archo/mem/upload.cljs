(ns archo.mem.upload
  (:require [re-frame.core :refer [reg-sub reg-event-db trim-v reg-event-fx]]
            [clojure.set :as set]
            [archo.fx :as fx]
            [oops.core :refer [oget oset!]]
            [cemerick.url :refer (url url-encode)]))

(defn store-stage-file [db [f]]
  (assoc-in db [:stage :file] f))

(reg-event-db ::store-stage-file trim-v store-stage-file)

(defn render-page-success [db [num]]
  (assoc-in db [:stage :pages :rendered num] true))

(reg-event-db ::render-page-success trim-v render-page-success)

(defn stage-file [db]
  (str "https://s3-eu-west-1.amazonaws.com/cms-sandbox.obrizum/" (js/encodeURIComponent (get-in db [:stage :file])))
  (get-in db [:stage :file])
  )

(reg-sub ::stage-file stage-file)


(defn select-page [db [n]]
  (if (contains? (get-in db [:stage :selected]) n)
    (update-in db [:stage :selected] (fnil disj #{}) n)
    (update-in db [:stage :selected] (fnil conj #{}) n)))

(reg-event-db ::select-page trim-v select-page)

(reg-sub ::selected-pages (fn [db]
                            (apply sorted-set (get-in db [:stage :selected]))))

(defn store-selection [{db :db} [{s3-key :s3/key title :title space-uuid :space/uuid adaptive? :node/adaptive?}]]
  (js/console.log "thespace with" space-uuid)
  (js/console.log "doing with" s3-key space-uuid)
  {:db       (-> db
                 (update-in [:stage :selections] conj (get-in db [:stage :selected])))
   :dispatch [::save-nodes [(get-in db [:stage :selected])] s3-key title space-uuid adaptive?]})

(reg-event-fx ::store-selection trim-v store-selection)

(defn all-selected-pages [db]
  (apply set/union (get-in db [:stage :selections])))

(defn all-groups [db]
  (get-in db [:stage :selections]))

(reg-sub ::all-selected-pages all-selected-pages)
(reg-sub ::all-groups all-groups)

;;;;;

(defn save-nodes [{db :db} [pages s3-key title space-id adaptive?]]
  ;(js/console.log "pages" pages)
  ;(js/console.log "filename" (-> db :stage :file (oget :name)))
  ;(js/console.log "TITLEIS" title)
  (js/console.log "SPACEID" space-id)
  {:db      (update db :stage assoc :storing? true)
   ::fx/api {
             ; match the API endpoint via its stored name in the router
             :uri        "/assets/split"
             :method     :post
             :params     {:s3/key         s3-key
                          :page-groups    (map vec (sort pages))
                          :title          title
                          :node/adaptive? adaptive?
                          :space/uuid     space-id
                          }
             :on-success [::save-nodes-success s3-key space-id]}})

(defn save-nodes-success [{db :db} [s3-key space-id response]]
  (js/console.log "SAVENODESUCCESS" space-id)
  (js/console.log "RES" response)
  {:db         (-> db
                   (update :stage dissoc :storing?)
                   (update :stage dissoc :selected))
   :dispatch-n [
                [::show-modal false]
                [::fetch-nodes-from-object "cms-sandbox.obrizum" s3-key space-id]]})

(reg-event-fx ::save-nodes trim-v save-nodes)
(reg-event-fx ::save-nodes-success trim-v save-nodes-success)


(defn fetch-nodes-from-object [_ [s3-bucket s3-key space-uuid]]
  {::fx/api {
             ; match the API endpoint via its stored name in the router
             :uri        (str "/assets/origin/" space-uuid "/" (url-encode s3-bucket) "/" (url-encode s3-key))
             ;:uri        (str "/assets/origin")
             ;:params     {:space/uuid space-uuid
             ;             :s3/bucket  (url-encode s3-bucket)
             ;             :s3/key     s3-key}
             :method     :get
             :on-success [::save-nodes-from-object s3-bucket s3-key]}})

(defn save-nodes-from-object [db [s3-bucket s3-key response]]
  (update-in db [:objects s3-bucket s3-key] assoc :pages response))

(reg-event-fx ::fetch-nodes-from-object trim-v fetch-nodes-from-object)
(reg-event-db ::save-nodes-from-object trim-v save-nodes-from-object)

(reg-sub ::page-nodes (fn [db [_ s3-bucket s3-key page-number]]
                        ;(println "doing" s3-bucket s3-key page-number)
                        (-> db :objects (get s3-bucket) (get s3-key) :pages (get page-number))
                        ))

(reg-event-db ::show-modal trim-v
              (fn [db [tf]]
                (assoc db :modal tf)))

(reg-sub ::show-modal?
         (fn [db]
           (get db :modal)))

(reg-event-db ::clear-selection trim-v (fn [db]
                                         (update db :stage dissoc :selected)))

(reg-sub ::show-count
         (fn [db]
           (get-in db [:stage :count] 50)))

(reg-event-db ::change-show-count trim-v
              (fn [db [f amount]]
                (update-in db [:stage :count] f amount)))

;;;;

(defn delete-node [{db :db} [node-uuid s3-key space-id]]

  {
   ;:db      (update db :stage assoc :storing? true)
   ::fx/api {
             ; match the API endpoint via its stored name in the router
             :uri        (str "/assets/node/retract/" node-uuid)
             :method     :delete
             ;:params     {:s3/key         s3-key
             ;             :page-groups    (map vec (sort pages))
             ;             :title          title
             ;             :node/adaptive? adaptive?
             ;             :space/uuid     space-id
             ;             }
             :on-success [::delete-node-success s3-key space-id]}})

(defn delete-node-success [{db :db} [s3-key space-id]]

  {:db       db
   :dispatch [::fetch-nodes-from-object "cms-sandbox.obrizum" s3-key space-id]}

  )

(reg-event-fx ::delete-node trim-v delete-node)
(reg-event-fx ::delete-node-success trim-v delete-node-success)



(reg-event-db ::toggle-modal2 trim-v
              (fn [db [val]]
                (assoc db :show-modal? val)
                ))

(reg-sub ::modal-status2
         (fn [db]
           (get db :show-modal2?)))




;;;;

(defn rename-node [{db :db} [node-uuid lang-en s3-key space-id]]

  {
   ;:db      (update db :stage assoc :storing? true)
   ::fx/api {
             ; match the API endpoint via its stored name in the router
             :uri        (str "/assets/node/rename")
             :method     :post
             :params     {:node/uuid node-uuid
                          :lang/en lang-en}
             :on-success [::delete-node-success s3-key space-id]
             }})



(reg-event-fx ::rename-node trim-v rename-node)

