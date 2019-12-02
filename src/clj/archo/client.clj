(ns archo.client
  (:require
    [datomic.ion :as ion]
    [datomic.client.api :as d]
    [taoensso.timbre :as timbre :refer [debugf]]
    [config.core :refer [env]]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; App params
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn fail
  [k]
  (throw (RuntimeException. (str "Unable to get a value for " k ", see https://docs.datomic.com/cloud/ions/ions-reference.html#parameters."))))

(defn get-params
  "Returns the SSM params under /datomic-shared/(env)/(app-name)/, where
   env       value of get-env :env
   app-name  value of get-app-info :app-name"
  []
  (let [
        ; /datomic-shared/ is a path in the AWS System Manager Parmaeter Store that all Ion Lambdas can see.
        ; TODO; later configure this to be per app and environment
        ;app (or (get (ion/get-app-info) :app-name) (fail :app-name))
        ;env (or (get (ion/get-env) :env) (fail :env))
        ]
    ; get stage specific variables, otherwise default to :prod
    (merge
      ; get "global" parameters
      (ion/get-params {:path (str "/datomic-shared/all/")})
      ; and any ones specific to the environment (will override conflicting keys from /all)
      (ion/get-params {:path (str "/datomic-shared/" (name (get (ion/get-env) :env :prod)) "/")}))))

(defn get-param
  "Get a parameter from AWS System Manager Parameter Store"
  [k]
  (or (get (get-params) k) (fail k)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Get a connection. Note the :server-type :ion, see also
;; https://docs.datomic.com/cloud/ions/ions-reference.html#server-type-ion
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def get-client
  (memoize (fn []
             (let [ions-env (name (get (ion/get-env) :env :prod))]
               (try
                 (println "IRONENV" (ion/get-env))
                 (println "CCCC")

                 (clojure.pprint/pprint env)
                 (d/client
                   (cond->
                     {:server-type :ion
                      :region      "eu-west-1"
                      :system      "cbs-datomic-shelf"
                      :query-group (str "obrio-api-" ions-env)
                      :endpoint    (str "http://entry.obrio-api-" ions-env ".eu-west-1.datomic.net:8182/")
                      }
                     (not= (-> env :myenv) "prod") (assoc :proxy-port 8182)
                     ))
                 (catch Exception e (str e)))))))

(defn get-connection*
  "Get a new connection to a database associated with a keyword.
  Tip: you probably want to use the memoized get-conn fn below.
  "
  []
  (d/connect (get-client) {:db-name
                           ;"joshdb"
                           (get-param "db-name")
                           }))

; Get a memoized connection to a database. Accepts no arguments or a kw to choose which database
(def get-conn (memoize get-connection*))

(defn db
  "Returns the most recent database from the connection.
  All queries and handlers should use this instead of calling d/db directly,
  that way we can see historical data using d/as-of in one place
  This should never be memoized!"
  []
  (d/db (get-conn)))
