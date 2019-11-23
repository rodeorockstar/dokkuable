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
    [archo.queries.entities :as queries]))

(def routes
  [
   ["/assets" {:coercion reitit.coercion.spec/coercion}
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
