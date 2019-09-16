(ns server
  "A simple web server to host a single page web application
  that uses client-side routing without document fragments"
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [resources]]
            [ring.util.response :refer [resource-response content-type]]))

; Serve resources from the public folder, and for anything not found
; (aka client-side routes that do not use document fragments) serve index.html
(defroutes routes
           (resources "/" {:root "public"})
           (GET "*" [] (-> (resource-response "index.html" {:root "public"})
                         (content-type "text/html"))))

; Define a handler wrapped with Ring defaults
(def handler (wrap-defaults #'routes site-defaults))

(defn -main
  "Run the web server"
  []
  (let [port 5000]
    (println "Web server starting on port" port)
    (run-jetty handler {:port port})))