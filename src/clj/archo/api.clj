(ns archo.api
  "A simple web server to host a single page web application
  that uses client-side routing without document fragments"
  (:require
    [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
    [compojure.core :refer [GET defroutes]]
    [compojure.route :refer [resources]]
    [ring.util.response :refer [resource-response content-type]]
    [ring.util.http-response :as resp]
    ;[reitit.coercion.spec]
    [archo.client :as client]
    [datomic.client.api :as d]
    ;[reitit.coercion.spec]
    [reitit.ring :as ring]
    [clojure.spec.alpha :as spec]
    [archo.queries.entities :as queries]
    [archo.queries.explore :as explore]
    [spec-tools.data-spec :as ds]
    [reitit.coercion.spec :as spec-coercion]

    ))

(def handler
  (fn [request]
    (or (resp/resource-response (:uri request) {:root "public"})
        (-> (resp/resource-response "index.html" {:root "public"})
          (resp/content-type "text/html")))))

(spec/def ::is-long (fn [n] (Long/parseLong n)))

;(spec/def ::ids (spec/or
;                  nat-int?
;                  (spec/coll-of nat-int?)))

(spec/def ::ids (spec/or :ids (spec/coll-of nat-int?)
                         :id nat-int?))

(spec/def ::id nat-int?)


(def routes
  [
   ;["" (ring/create-resource-handler)]
   ["/assets" {:coercion reitit.coercion.spec/coercion}
    ["/schema"
     ["" {:get {:handler (fn [req]
                           (resp/ok (explore/wind :db/id (map first (explore/schema (client/db))))))}}]]
    ["/search"
     ["" {:post {:parameters {:body {:attribute keyword?
                                     :value     some?}}
                 :handler    (fn [req]
                               (println "PARAMSARE" (-> req :parameters))
                               (println "values" (vals (-> req :parameters :body)))
                               (println "types" (map type (vals (-> req :parameters :body))))
                               (resp/ok (explore/find-id (client/db) (-> req :parameters :body :attribute) (-> req :parameters :body :value)))
                               ;(resp/ok (explore/wind :db/id (map first (explore/schema (client/db)))))
                               )}}]]
    ["/nodes"
     ["" {:get {:parameters {:query {:ids ::ids}}
                :coercion   spec-coercion/coercion
                :handler    (fn [req]
                              ;(clojure.pprint/pprint req)

                              (let [ids-input (-> req :parameters :query :ids)]
                                (resp/ok (explore/entities-without-components (client/db)
                                                                              (cond->
                                                                                ids-input
                                                                                (not (coll? ids-input)) vector)))))}}]

     ]
    ]

   ;["/*" (ring/create-resource-handler)]
   ["/*" handler]
   ])

