(ns archo.fx
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [re-frame.core :refer [reg-fx subscribe dispatch reg-sub reg-event-db trim-v reg-event-fx]]
            ;[taoensso.timbre :refer-macros [debug debugf]]
            [cljs.core.async :refer [<! timeout]]
            [cljs-http.client :as http]
            [archo.config :as config]
            [cljs-http.core :as http-core]
            ))

(defn fetch [{:keys    [fetch/method
                        fetch/url
                        fetch/params
                        fetch/on-success
                        fetch/on-error
                        ] :as fetch-desc}]
  (let [
        ;token   (-> @(subscribe [:hubble.mem.ident/data]) :person/tokens :access :token/value)
        http-fn (case method
                  :get    http/get
                  :post   http/post
                  :delete http/delete
                  :put    http/put
                  http/get)]
    (let [c (http-fn url (merge {:with-credentials? false}
                                params))]
      (go (let [{:keys [status body success] :as response} (<! c)]
            (cond
              (and success
                   on-success)
              (dispatch (conj on-success response))

              (and (not success)
                   on-error)
              (dispatch (conj on-error response))
              ))))))


(defn api [{:keys [method
                   uri
                   params
                   on-success
                   on-error
                   is-retry?
                   track-id
                   delay] :as req}]
  (let [
        ;token    (-> @(subscribe [:hubble.mem.ident/data]) :person/tokens :access :token/value)
        http-fn  (case method
                   :get http/get
                   :post http/post
                   :delete http/delete
                   :put http/put
                   http/get)
        track-id (or track-id (random-uuid))]
    ;(debugf "http ⬆ ┃ %s %s %s" method uri params)
    ;(dispatch [:lens.http/track track-id uri])
    (go
      (when delay (<! (timeout (max delay 0))))
      (let [c (http-fn (str config/api-url uri) (cond-> {:with-credentials? false
                                                         :headers           {"Accept" "application/edn"}}
                                                        ;token (update :headers assoc "Authorization" token)
                                                        (and params) (assoc :transit-params params)
                                                        ;(and params (= method :get)) (assoc :query-params params)
                                                        true (assoc :with-credentials? false)))]
        (go
          (let [{:keys [status body success] :as response} (<! c)]
            ;(debugf "http ⬇ ┃ %s" response)
            (if (= success true)
              (when on-success (dispatch (conj on-success body)))

              ; If the request was unsuccessful, retry it once more.
              ; This is to circumvent a bug in the API Gateway that spontaneously
              ; throws 500 errors "Connection reset by peer"
              (if-not is-retry?
                (js/setTimeout #(api (merge req {:is-retry? true
                                                 :track-id  track-id}))
                               1000)
                (when on-error (dispatch (conj on-error body)))))))))))

(defn media [{:keys [uri
                     on-success
                     on-error
                     is-retry?
                     raw?
                     track-id] :as req}]
  (let [c (http/get (str config/media-url uri) (merge {:with-credentials? false}
                                                      (when raw? {:response-type :array-buffer}))
                    )]
    (go (let [{:keys [status body success] :as response} (<! c)]
          ;(debugf "http ⬇ ┃ %s" response)
          (if (= success true)
            (when on-success (dispatch (conj on-success body)))

            ; If the request was unsuccessful, retry it once more.
            ; This is to circumvent a bug in the API Gateway that spontaneously
            ; throws 500 errors "Connection reset by peer"
            (when on-error (dispatch (conj on-error body))))))))


;; TODO: merge to above media fx???
(defn s3-upload [{:keys [file
                         presigned-url
                         content-type
                         on-success
                         on-error]}]
  (let [req          {:request-method    "PUT"
                      :headers           {"content-type" content-type}
                      :body              file
                      :with-credentials? false}
        req-with-url (merge req (http/parse-url presigned-url))]
    (let [ch (http-core/xhr req-with-url)]
      (go (let [{:keys [status body success] :as response} (<! ch)]
            (if (= success true)
              (when on-success (dispatch (conj on-success body)))
              (when on-error (dispatch (conj on-error body)))))))))

(reg-fx ::fetch fetch)
(reg-fx ::api api)
(reg-fx ::media media)
(reg-fx ::s3-upload s3-upload)

(defn fetch-as-blob
  "Fetch a media resource and save it as a blob"
  [[file-name extension]]
  ; Fetch a resource from the CDN
  (let [c (http/get (str config/media-url "/" file-name "." (first extension)) {:with-credentials? false})]
    (go (let [{:keys [headers body]} (<! c)]
          ; Save the response as a Blob with
          (let [blob (js/Blob. [body] (clj->js {:type (get headers "content-type")}))]
            (dispatch [::store-blob file-name blob]))))))

(defn store-blob
  "Store a blob to memory"
  [db [file-name blob]]
  ;(debugf "Cached: %s" file-name)
  (assoc-in db [:cache file-name] blob))

(reg-event-db ::store-blob trim-v store-blob)
(reg-fx ::cache fetch-as-blob)
