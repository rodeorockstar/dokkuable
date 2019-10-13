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
    [archo.queries.entities :as queries]))

(def routes

  [
   ["/assets"
    ["/org" {:get {:handler (fn [r]
                              (let [results (queries/orgs (client/db))]
                                (resp/ok (zipmap (map :obr/uuid results) results))
                                ))}}]]
   ["/plain"
    ["/plus" {:get  {:parameters {:query {:sample string?}}
                     :handler    (fn [r]
                                   (resp/ok "thanks2"))}
              :post (fn [{{:keys [x y]} :body-params}]
                      {:status 200
                       :body   {:total (+ x y)}})}]]])
