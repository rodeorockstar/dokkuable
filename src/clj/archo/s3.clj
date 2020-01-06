(ns archo.s3
  (:require [cognitect.aws.client.api :as aws]
            [archo.config :as config]
            [cuerdas.core :as str]
            [clojure.walk :refer [postwalk]]))

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
  (let [safe-prefix (cond-> prefix (does-not-end-with? prefix "/") (str "/"))]
    (->> {:op      :ListObjectsV2
          :request {:Bucket    bucket
                    :Prefix    safe-prefix
                    :Delimiter "/"}}
         (aws/invoke s3)
         (postwalk (unquote-kv :ETag)))))

