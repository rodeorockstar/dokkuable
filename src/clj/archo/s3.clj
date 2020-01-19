(ns archo.s3
  (:require [cognitect.aws.client.api :as aws]
            [archo.config :as config]
            [cuerdas.core :as str]
            [clojure.walk :refer [postwalk]]
            [cemerick.url :refer (url url-encode)]
            [clojure.java.io :as io]))

(defn str->uuid [s]
  (java.util.UUID/fromString s))

(def does-not-end-with? (complement clojure.string/ends-with?))

(def s3 (aws/client {:api :s3}))

(defn unquote-kv
  "If a map contains the keyword "
  [k]
  (fn [m]
    (if (and (map? m) (contains? m k))
      (update m k str/unquote)
      m)))

(defn ls
  "List the top level contents of a folder within an S3 bucket"
  [bucket prefix]
  ; ensure that the root key ends with a / which represents a folder
  (let [safe-prefix (if (empty? prefix)
                      ""
                      (if (clojure.string/ends-with? prefix "/")
                        prefix
                        (str prefix "/"))


                      #_(str/rtrim prefix "/"))]
    (cond->
      (->> {:op      :ListObjectsV2
            :request {:Bucket    bucket
                      :Prefix    safe-prefix
                      ;:Prefix    prefix
                      :Delimiter "/"}}
           (aws/invoke s3)
           (postwalk (unquote-kv :ETag)))
      (not-empty safe-prefix) (update :Contents rest))))


(defn upload [prefix file]
  (println "uploading" prefix file)
  (let [puts (for [f file]
               (future (->> {:op      :PutObject
                             :request {:Bucket      "cms-sandbox.obrizum"
                                       :Key         (str prefix "/" (:filename f))
                                       :Body        (io/input-stream (:tempfile f))
                                       :ContentType (:content-type f)}}
                            (aws/invoke s3))))]
    (map deref puts)))