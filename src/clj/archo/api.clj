(ns archo.api
  "A simple web server to host a single page web application
  that uses client-side routing without document fragments"
  (:require
    [reitit.coercion.spec]
    [reitit.coercion.spec]
    [archo.client :as client]
    [archo.handlers.node :as node-handlers]
    [archo.queries.entities :as queries]
    [clojure.spec.alpha :as s]
    [compojure.core :refer [GET defroutes]]
    [compojure.route :refer [resources]]
    [datomic.client.api :as d]
    [reitit.ring :as ring]
    [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
    [ring.util.http-response :as resp]
    [ring.util.response :refer [resource-response content-type]]))


(s/def ::page number?)
(s/def ::pages (s/coll-of ::page :kind vector?))
(s/def ::page-groups (s/coll-of ::pages))

(def handler
  (fn [request]
    (or (resp/resource-response (:uri request) {:root "public"})
        (-> (resp/resource-response "index.html" {:root "public"})
          (resp/content-type "text/html")))))

(def routes
  [
   ["/assets" {:coercion reitit.coercion.spec/coercion}
    ["/origin/{space/uuid}/{s3/bucket}/{s3/key}" {:get {:parameters {:path {:s3/key     string?
                                                                            :s3/bucket  string?
                                                                            :space/uuid uuid?}}
                                                        :handler    node-handlers/nodes-created-from-pages-handler}}]
    ["/split" {:post {:parameters {:body {:s3/key      string?
                                          :page-groups ::page-groups
                                          :space/uuid  uuid?}}
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
                       :body   {:total (+ x y)}})}]]
   ["/api"
    ["/admin/orgs" {:get {:handler (fn [r] (resp/ok (queries/orgs (client/db))))}}]
    ["/admin/spaces" {:get {:handler (fn [r] (resp/ok (queries/all-spaces2 (client/db))))}}]]

   ;["/*" (ring/create-resource-handler)]
   ["/*" handler]
   ])
