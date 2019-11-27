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
    [archo.handlers.node :as node-handlers]
    [archo.queries.entities :as queries]
    [clojure.spec.alpha :as s]))


(s/def ::page number?)
(s/def ::pages (s/coll-of ::page :kind vector?))
(s/def ::page-groups (s/coll-of ::pages))

(def routes
  [
   ["/assets" {:coercion reitit.coercion.spec/coercion}
    ["/origin/{s3/bucket}/{s3/key}" {:get {:parameters {:path {:s3/key    string?
                                                               :s3/bucket string?}}
                                           :handler    node-handlers/nodes-created-from-pages-handler}}]
    ["/split" {:post {:parameters {:body {:s3/key      string?
                                          :page-groups ::page-groups}}
                      :handler    node-handlers/make-files-handler}}]
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
