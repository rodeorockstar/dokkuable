(ns archo.mem.assets
  (:require [archo.fx :as fx]
            [reitit.core :as reitit]
            [archo.mem.events :as mem-events]
            [reitit.coercion.spec]
            [reitit.coercion :as coercion]
            [cuerdas.core :as str]
            [re-frame.core :refer [reg-sub reg-event-db reg-event-fx reg-sub trim-v]]))


(def router
  ;; apply Spec coercion to all routes
  (reitit/router
    [["/" {:coercion reitit.coercion.spec/coercion}
      ["assets/orgs"
       ["" {:name :api/orgs}]
       ["/{org/short-name}/spaces"
        ["" {:name :api/spaces}]
        ["/{space/uuid}" {:name       :api/space
                          :parameters {:path {:space/uuid uuid?}}}]]]]]))


(defn fetch-asset [_world [route-name path-parameters store-in]]
  {::fx/api {
             ; match the API endpoint via its stored name in the router
             :uri        (:path (reitit/match-by-name router route-name path-parameters))
             :on-success [::fetch-asset-success store-in]}})

(defn fetch-asset-success [db [where results]]
  (assoc-in db where results))

(reg-event-fx ::fetch-asset trim-v fetch-asset)
(reg-event-db ::fetch-asset-success trim-v fetch-asset-success)

(reg-sub ::assets (fn [db [_ key]] (get-in db [:assets key])))
(reg-sub ::all-assets (fn [db [_]] (get db :assets)))

(reg-sub ::active-assets
         :<- [::mem-events/active-route]
         :<- [::all-assets]
         (fn [[active-route all-assets] [_ & asset-key]]
           (get-in all-assets (apply conj [(-> active-route :parameters :path :org/short-name)] asset-key))))

(reg-sub ::active-space-assets
         :<- [::mem-events/active-route]
         :<- [::all-assets]
         (fn [[active-route assets]]
           (let [{:keys [org/short-name space/uuid]} (-> active-route :parameters :path)]
             (get-in assets [short-name :space uuid]))))


;(defn fetch-spaces [_world [route-name path-parameters store-in]]
;  (js/console.log "PP" path-parameters)
;  (let [m (reitit/match-by-name router route-name path-parameters)]
;    (js/console.log "M" m)
;    {::fx/api {
;               ; match the API endpoint via its stored name in the router
;               :uri        (:path m)
;               :on-success [::fetch-spaces-success path-parameters]}}))
;
;(defn fetch-spaces-success [db [{:keys [space/uuid org/short-name] :as _path-params} results]]
;  (assoc-in db [:assets short-name :space uuid] results))
;
;(reg-event-fx ::fetch-spaces trim-v fetch-spaces)
;(reg-event-db ::fetch-spaces-success trim-v fetch-spaces-success)


(reg-event-fx ::get-some-api-data
              trim-v
              (fn [_world [some-parameter]]
                {::fx/api {
                           :uri        "/some/api/call"
                           :method     :post
                           :params     {:some-parameter some-parameter}
                           :on-success [::get-some-data-success "extra-value"]}}))

(reg-event-db ::get-some-data-success
              trim-v
              (fn [db [extra-value response]]
                (js/console.log "response: " extra-value response)
                db))


;;;;;;;;;;;;;;;;;;;;

(reg-event-fx ::fetch-orgs
              trim-v
              (fn [_]
                {::fx/api {
                           :uri        "/api/admin/orgs"
                           :method     :get
                           :on-success [::fetch-orgs-success]}}))

(reg-event-db ::fetch-orgs-success
              trim-v
              (fn [db [response]]
                (assoc db :orgs response)))

(reg-sub ::orgs
         (fn [db]
           ;(sort-by (comp clojure.string/lower-case :org/short-name) (vals (get db :orgs)))
           (get db :orgs)
           ))

;;;;;;;;;;;;;;;;;;;;

(reg-event-fx ::fetch-spaces
              trim-v
              (fn [_]
                {::fx/api {
                           :uri        "/api/admin/spaces"
                           :method     :get
                           :on-success [::fetch-spaces-success]}}))

(reg-event-db ::fetch-spaces-success
              trim-v
              (fn [db [response]]
                (assoc db :spaces response)))

(reg-sub ::spaces
         (fn [db]
           (get db :spaces)))

;;;;;;;;;;;;;;;;;;;;

(reg-event-fx ::fetch-available-files
              trim-v
              (fn [db [short-name]]
                (js/console.log "SHORT NAME" short-name)
                {::fx/api {
                           :uri        (str "/assets/available-files/" short-name)
                           :method     :get
                           :on-success [::store-available-files]}}))

(reg-event-db ::store-available-files
              trim-v
              (fn [db [response]]
                (assoc db :available-files response)))

(reg-sub ::available-files
         (fn [db]
           (-> db :available-files :Contents)))


;;;;;

;;;;;;;;;;;;;;;;;;;;

(reg-event-fx ::ls
              trim-v
              (fn [{db :db} [k]]
                {:db      (assoc-in db [:fs :view] k)
                 ::fx/api {
                           :uri        "/fs/ls"
                           :method     :get
                           :params     {:key k}
                           :on-success [::store-ls]}}))

(reg-event-fx ::mkdir
              trim-v
              (fn [{db :db} [current-key k]]
                {:db      (update db :fs dissoc :modal-kw)
                 ::fx/api {
                           :uri        "/fs/mkdir"
                           :method     :post
                           :params     {:key (str current-key k "/")}
                           :on-success [::ls current-key]}}))

(reg-event-fx ::rm
              trim-v
              (fn [{db :db} [current-key k]]
                {
                 :db      (update db :fs dissoc :modal-kw)
                 ::fx/api {
                           :uri        "/fs/rm"
                           :method     :post
                           :params     {:key k}
                           :on-success [::ls current-key]}}))

(reg-event-db ::close-modal
              trim-v
              (fn [db]
                (update db :fs dissoc :modal-kw)))

(reg-event-db ::set-selected-key
              trim-v
              (fn [db [k]]
                (assoc-in db [:fs :selected-key] k)))

(reg-sub ::selected-key
         (fn [db]
           (get-in db [:fs :selected-key])))

(reg-event-db ::show-modal
              trim-v
              (fn [db [modal-kw]]
                (assoc-in db [:fs :modal-kw] modal-kw)))

(reg-sub ::showing-modal
         (fn [db]
           (get-in db [:fs :modal-kw])))

(reg-event-db ::store-ls
              trim-v
              (fn [db [{:keys [Prefix] :as response}]]
                (assoc-in db [:fs :files Prefix] response)))

(reg-sub ::fs
         (fn [db]
           (get-in db [:fs])))

(reg-sub ::fs-view
         :<- [::fs]
         (fn [fs]
           (get fs :view)))

(reg-sub ::fs-files
         :<- [::fs]
         (fn [fs]
           (get fs :files)))

(defn display-name [m]
  (map (fn [s] (assoc s :Display (-> s :Key (str/split #"/") last))) m)
  )

(defn display-name2 [m]
  (map (fn [s] (assoc s :Display (-> s :Prefix (str/split #"/") last))) m)
  )

(reg-sub ::current-files
         :<- [::fs-files]
         :<- [::fs-view]
         (fn [[files view]]
           (js/console.log "GOTFILES" (get files view))
           (-> (get files view)
               (update :Contents display-name)
               (update :CommonPrefixes display-name2))))

(reg-sub ::nav
         :<- [::fs-view]
         (fn [view]
           {:back (str/join "" (interleave (butlast (str/split view #"/")) (repeat "/")))}))