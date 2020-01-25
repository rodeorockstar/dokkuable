(ns archo.mem.view
  (:require [archo.fx :as fx]
            [reitit.core :as reitit]
            [archo.mem.events :as mem-events]
            [archo.mem.assets :as mem-assets]
            [reitit.coercion.spec]
            [reitit.coercion :as coercion]
            [re-frame.core :refer [reg-sub reg-event-db reg-event-fx reg-sub trim-v]]))

(reg-sub ::active-route
         (fn [db]
           (:active-route db)))

(reg-sub ::active
         (fn [db]
           (let [parameters (-> db :active-route :parameters)]
             (merge (:path parameters)))))

(reg-sub ::active-org-spaces
         :<- [::active]
         :<- [::mem-assets/orgs]
         :<- [::mem-assets/spaces]
         (fn [[active orgs spaces]]
           (select-keys spaces (get-in orgs [(:org/short-name active) :org/spaces]))))

(reg-sub ::active-org
         :<- [::active]
         :<- [::mem-assets/orgs]
         (fn [[active orgs]]
           (get orgs (:org/short-name active))))

(reg-sub ::active-space
         :<- [::active]
         :<- [::mem-assets/spaces]
         (fn [[active spaces]]
           (get spaces (:space/uuid active))))

