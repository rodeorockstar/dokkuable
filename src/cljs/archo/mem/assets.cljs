(ns archo.mem.assets
  (:require [archo.fx :as fx]
            [reitit.core :as reitit]
            [archo.mem.events :as mem-events]
            [reitit.coercion.spec]
            [reitit.coercion :as coercion]
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


(defn fetch-spaces [_world [route-name path-parameters store-in]]
  (js/console.log "PP" path-parameters)
  (let [m (reitit/match-by-name router route-name path-parameters)]
    (js/console.log "M" m)
    {::fx/api {
               ; match the API endpoint via its stored name in the router
               :uri        (:path m)
               :on-success [::fetch-spaces-success path-parameters]}}))

(defn fetch-spaces-success [db [{:keys [space/uuid org/short-name] :as _path-params} results]]
  (assoc-in db [:assets short-name :space uuid] results))

(reg-event-fx ::fetch-spaces trim-v fetch-spaces)
(reg-event-db ::fetch-spaces-success trim-v fetch-spaces-success)


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
           (get db :orgs)))