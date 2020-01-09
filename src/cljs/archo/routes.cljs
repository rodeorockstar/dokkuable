(ns archo.routes
  (:require
    [taoensso.timbre :refer-macros [debug debugf info]]
    [re-frame.core :refer [dispatch subscribe reg-sub reg-fx reg-event-fx trim-v]]
    [reitit.frontend :as rf]
    [reitit.frontend.controllers :as rfc]
    [reitit.frontend.easy :as rfe]
    [reitit.coercion.spec]
    [reitit.coercion.schema :as rsc]
    [archo.mem.events :as mem-events]
    [taoensso.timbre :refer [logf]]
    ;[hubble.subs :as subs]
    [oops.core :refer [oget oset!]]
    [reitit.coercion.schema]
    [spec-tools.data-spec :as ds]
    [clojure.set :refer [rename-keys]]
    [reagent.core :as r]
    [archo.mem.assets :as mem-assets]
    ;[archo.views.org :as view-org]
    ;[hubble.mem.history :as mem-history]
    ;[hubble.mem.profile :as mem-profile]
    ;[hubble.mem.analytics :as mem-analytics]
    ;[hubble.mem.org :as mem-org]
    ;[hubble.mem.admin :as mem-admin]
    ;[hubble.mem.profile :as mem-cert]
    ;[hubble.breadcrumb.name.subscription :as bc-name-sub]
    ;[hubble.subs :as subs]
    ;[breaking-point.core :as bp]
    ))

;; another binding for reitit's href fn so that view namespaces already requiring this route namespace
;; don't have to also require reitit.frontend.easy.
;; rfe/href is stateful in that it contains its own rfe/history atom
;; we could potentially use app-db instead which would be great
(def url-for rfe/href)

(def routes
  ;; apply Spec coercion to all routes
  ["" {:coercion    reitit.coercion.spec/coercion
       ; fetch orgs on any route match
       :controllers [{:identity identity
                      :start    (fn []
                                  (dispatch [::mem-assets/fetch-orgs])
                                  (dispatch [::mem-assets/fetch-spaces])
                                  (dispatch [::mem-assets/fetch-available-files])
                                  )}]}
   ["/org" {:controllers [{:identity identity
                           :start (fn [e]
                                    (dispatch [::mem-assets/fetch-available-files (-> e :parameters :path :org/short-name)]))}]}
    ["/{org/short-name}" {:parameters {:path {:org/short-name string?}}}
     ["" {:name :route/org-home}]
     ["/space/{space/uuid}" {:parameters {:path {:space/uuid uuid?}}}
      ["" {:name :route/space}]
      ["/split" {:name :route/split}]]]]
   ["/files" {:name :route/files}]])

; http://localhost:5000/org/cbs/1ac3385f-39d8-4673-8697-95ddb3f80810

; def the reitit router which can later be referenced by reitit.core/match-by-name
(defonce router (rf/router routes {:conflicts nil}))

(defonce match (r/atom nil))

(defn init! []
  (rfe/start!
    router
    (fn [new-match]
      (js/console.log "MATCH" new-match)
      (swap! match (fn [old-match]
                     (if new-match
                       (assoc new-match :controllers (rfc/apply-controllers (:controllers old-match) new-match)))))
      (dispatch [::mem-events/set-active-route new-match]))
    ;; set to false to enable HistoryAPI
    {:use-fragment false}))

(defn safe-not-empty [x]
  (if (coll? x)
    (not-empty x)
    x))

(defn redirect!
  "If `replace` is truthy, previous page will be replaced in history, otherwise added."
  [{:keys [name path-params query-params replace]}]
  ; query params returns an empty map {} and replace-state and push-state are multiarity
  ; this if-let prevents a lonely ? from being appended to the url if there are no query params
  (if-let [query-params (not-empty query-params)]
    (if replace
      (rfe/replace-state name path-params query-params)
      (rfe/push-state name path-params query-params))
    (if replace
      (rfe/replace-state name path-params)
      (rfe/push-state name path-params))))

(defn load-route! [{:keys [data path-params query-params replace] :as _route}]
  (redirect! {:name         (-> data :name)
              :path-params  path-params
              :query-params query-params
              :replace      replace}))

(defn load-url!
  "Given a URL, build a reitit Match and then load it."
  [url]
  (load-route! (assoc (rf/match-by-path router url) :replace true)))

(defn dispatch-load-route!
  "A coeffect to dispatch the load-route! effect."
  [_ [route]]
  {::load-route! route})

(reg-fx ::load-route! load-route!)
(reg-fx ::load-url! load-url!)
(reg-event-fx ::dispatch-load-route! [trim-v] dispatch-load-route!)


;; Client-side URL routings is used in the way:
;; - path parameters - to determine place/page
;; - query parametres - parameterize place/page

;; In Hubble SPA we partly comply to that, having some routes determined via query-params (classroom, catalogue)

;; In our application, we have <body> varying in height, between pages
;; In some cases <body> height more than screen height, in such case browsers attach to <body> dom-element a mutable state of the scroll position.
;; As the application switches from page A to page B, ReactJS mutates dom-tree, rendering page B, which makes <body> content to change in height,
;; however scroll state of the previous page is partly preserved, rendering us with the behaviour,
;; when we e.g. go to the bottom of page A, navigate to page B, as page B is rendered scroll is preserved and is on the bottom.

;; There is a  couple of approaches to achieve the desired “scroll always on top” when render a page behavior:
;; - mutate scroll position on page content change
;;   https://stackoverflow.com/questions/33188994/scroll-to-the-top-of-the-page-after-render-in-react-js
;;
;; - re-mount the page node on page change
;;   ReactJS allows to supply "key" metadata for a component - it is identity of the component
;;   If "key"s match - component is being reused, if they don't - old component is being remounted, new mounted
;;   By default "key" is based on passed to a component parameters
;;   However we are able to manually define/ovverride it

;;   Setting "key" to page identity will ensure previous page to be unmounted and new mounted,
;;   launching all lifecycle methods and dropping scroll state
