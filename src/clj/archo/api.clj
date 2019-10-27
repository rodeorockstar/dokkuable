(ns archo.api
  "A simple web server to host a single page web application
  that uses client-side routing without document fragments"
  (:require
    [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
    [compojure.core :refer [GET defroutes]]
    [compojure.route :refer [resources]]
    [ring.util.response :refer [resource-response content-type]]
    [ring.util.http-response :as resp]
    [reitit.coercion.spec]
    [archo.client :as client]
    [datomic.client.api :as d]
    [reitit.coercion.spec]
    [reitit.ring :as ring]
    [archo.queries.entities :as queries]
    [archo.queries.explore :as explore]))

(def routes
  [
   ["" (ring/create-resource-handler)]
   ["/assets" {:coercion reitit.coercion.spec/coercion}
    ["/node/:id"
     ["" {:get {:parameters {:path {:id some?}}
                :handler    (fn [req]
                              (clojure.pprint/pprint (-> req :parameters))
                              (let [id (-> req :parameters :path :id read-string)]
                                (resp/ok (explore/entity-without-components2 (client/db) id))))}}]
     ["/attributes" {:get {:parameters {:path {:id number?}}
                           :handler    (fn [req]
                                         (let [id (-> req :parameters :path :id read-string)]
                                           (resp/ok (into {} (explore/non-reference-values (client/db) id)))))}}]
     ["/references" {:get {:parameters {:path {:id some?}}
                           :handler    (fn [req]
                                         (let [id (-> req :parameters :path :id read-string)]
                                           (resp/ok (explore/group-values-by-key (explore/reference-values (client/db) id)))))}}]]
    ["/orgs"
     ["" {:get {:handler (fn [r]
                           (resp/ok (queries/orgs (client/db))))}}]
     ["/{org/short-name}/spaces"
      ["" {:get {:handler (fn [{{org-short-name :org/short-name} :path-params}]
                            (resp/ok (queries/spaces (client/db) org-short-name)))}}]
      ["/{space/uuid}" {:get {
                              :parameters {:path {:space/uuid uuid?}}
                              :handler    (fn [req]
                                            (resp/ok (queries/space-details (client/db) (-> req :parameters :path :space/uuid)))

                                            )}}]
      ]]]
   ["/plain"
    ["/plus" {:get  {:parameters {:query {:sample string?}}
                     :handler    (fn [r]
                                   (resp/ok "thanks2"))}
              :post (fn [{{:keys [x y]} :body-params}]
                      {:status 200
                       :body   {:total (+ x y)}})}]]])
