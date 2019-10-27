(ns server
  "A simple web server to host a single page web application
  that uses client-side routing without document fragments"
  (:require [org.httpkit.server :as kit]
    ;[ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response content-type]]
            [jumblerg.middleware.cors :refer [wrap-cors]]


    ;[ring.adapter.jetty :as jetty]
            [ring.middleware.params :as params]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [muuntaja.core :as m]
            [reitit.ring.coercion :as coercion]
            [reitit.ring :as ring]
    ;[example.plain]
    ;[example.dspec]
    ;[example.schema]
    ;[example.spec]

            [archo.api :as api-routes]

            ))

; Serve resources from the public folder, and for anything not found
; (aka client-side routes that do not use document fragments) serve index.html
(defroutes routes
           (resources "/" {:root "public"})
           (GET "*" [] (-> (resource-response "index.html" {:root "public"})
                         (content-type "text/html"))))

; Define a handler wrapped with Ring defaults
(def handler (wrap-defaults #'routes site-defaults))



(def app
  (ring/ring-handler
    (ring/router
      [api-routes/routes]
      {
       :conflicts (constantly nil)
       :data {:muuntaja   m/instance
              :coercion   reitit.coercion.spec/coercion
              :middleware [params/wrap-params
                           muuntaja/format-middleware
                           coercion/coerce-exceptions-middleware
                           coercion/coerce-request-middleware
                           coercion/coerce-response-middleware
                           [wrap-cors identity]]}})
    (ring/routes
      ;(ring/create-resource-handler {:path "/"})
      (ring/create-default-handler))))

(defn -main
  "Run the web server"
  []
  (let [port 5000]
    (println "Web server starting on port" port)
    (kit/run-server app {:port port})))