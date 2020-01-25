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
    [reitit.ring.middleware.multipart :as multipart]
    [ring.util.http-response :as r]
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
    ["/upload" {:post {:summary "upload a file"
                       :parameters {:multipart {:file some? ;multipart/temp-file-part
                                                :org string?}}
                       ;:responses {200 {:body {:name string?, :size int?}}}
                       :handler node-handlers/file-handler
                       ;:handler (fn [req]
                       ;           (r/ok {:success true}))

                       }}]
    ["/node/retract/{node/uuid}" {:delete  {:parameters {:path {:node/uuid uuid?}}}
                                  :handler node-handlers/retract-node}]
    ["/node/rename" {:post    {:parameters {:body {:node/uuid uuid?
                                                   :lang/en   string?}}}
                     :handler node-handlers/rename-node}]
    ["/available-files/{org/short-name}" {:get {:parameters {:path {:org/short-name string?}}
                                                :handler    node-handlers/available-files-handler}}]
    ["/origin/{space/uuid}/{s3/bucket}/{s3/key}" {:get {:parameters {:path {:s3/key     string?
                                                                            :s3/bucket  string?
                                                                            :space/uuid uuid?}}
                                                        :handler    node-handlers/nodes-created-from-pages-handler}}]



    ["/split" {:post {:parameters {:body {:s3/key         string?
                                          :page-groups    ::page-groups
                                          :space/uuid     uuid?
                                          :node/adaptive? boolean?
                                          :node/kind      keyword?
                                          }}
                      :handler    node-handlers/make-files-handler}}]


    ["/video" {:post {:parameters {:body {:s3/key         string?
                                          :space/uuid     uuid?}}
                      :handler    node-handlers/create-video-handler}}]


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
   ["/fs"
    ["/ls" {:get {:parameters {:query {:key string?}}
                  :handler node-handlers/fs-ls-handler}}]
    ["/mkdir" {:post {:parameters {:body {:key string?}}}
               :handler node-handlers/fs-mkdir-handler}]
    ]
   ["/api"
    ["/admin/orgs" {:get {:handler (fn [r] (resp/ok (queries/orgs (client/db))))}}]
    ["/admin/spaces" {:get {:handler (fn [r] (resp/ok (queries/all-spaces2 (client/db))))}}]]

   ;["/*" (ring/create-resource-handler)]
   ["/*" handler]
   ])
