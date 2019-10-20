(ns archo.views.home
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget ocall]]
            [archo.mem.upload :as mem-upload]
            [archo.mem.assets :as mem-assets]
            [archo.mem.graph :as mem-graph]
            [archo.views.kinds.pdf :as pdf]
            [archo.routes :as routes]
            [reitit.core :as reitit]
    ;["react-d3-graph" :refer [Graph]]
            ["react-force-graph" :refer [ForceGraph2D]]
            ["d3" :as d3]))

;(def graph (r/adapt-react-class Graph))
(def d2g (r/adapt-react-class ForceGraph2D))

;(js/console.log "d3is" d3)


(defn org-entry []
  (fn [[id {:keys [org/name org/short-name]}]]
    [:div.col-sm-6.col-md-4.col-lg-3
     [:div.card.h-100.p-2 [:div.card-body name]]]
    [:a.list-group-item.list-group-item-action
     {:href (routes/url-for :route/org {:org/short-name short-name})} name]))

(defn org-list []
  (fn [orgs]
    (into [:div.list-group] (map (fn [o] [org-entry o]) orgs))))


(defn node []
  (fn [{:keys [x y local-attributes] :as n}]
    ;(js/console.log "N" (-> local-attributes :id))
    [:circle {:cx       x
              :cy       y
              :r        10
              :style    {:fill "black"}
              :on-click (fn [d]
                          (dispatch [::mem-graph/fetch-node-references (-> local-attributes :id)]))}]))

(defn link []
  (fn [{:keys [source target] :as n}]
    ;(js/console.log "N" (-> local-attributes :id))
    ;(js/console.log "link" n)
    [:line
     {
      :style {:stroke "red"}
      :x1 (-> source :x)
      :y1 (-> source :y)
      :x2 (-> target :x)
      :y2 (-> target :y)
      }
     ]))

(defn graph-inner []
  (let [
        node-positions (subscribe [::mem-graph/node-positions])
        link-positions (subscribe [::mem-graph/link-positions])
        ]
    (fn []
      [:svg {:width 500 :height 500}
       (into [:g]
             (map (fn [l]
                    [link l]
                    ) @link-positions))
       (into [:g]
             (map (fn [[k v]]
                    [node v]
                    ) @node-positions))])))



(defn graphit []
  (let [simulation (r/atom nil)
        pos        (r/atom nil)
        links      (r/atom nil)
        jslinks         (r/atom nil)]
    (r/create-class
      {
       :component-did-mount
       (fn [this]
         (reset! simulation (-> d3
                                (ocall :forceSimulation)
                                (ocall :force "y" (-> d3 (ocall :forceY 0.001)))
                                (ocall :force "x" (-> d3 (ocall :forceX 0.001)))
                                (ocall :force "charge" (-> d3
                                                           (ocall :forceManyBody)
                                                           ;(ocall :strength -700)
                                                           ;(ocall :distanceMin 1000)
                                                           ;(ocall :distanceMax 1000)
                                                           ))
                                (ocall :force "link" (-> d3
                                                         (ocall :forceLink)
                                                         (ocall :id (fn [d]
                                                                      (oget d :local-attributes :id)))
                                                         ))
                                (ocall :force "center" (-> d3 (ocall :forceCenter 250 250)))))
         (ocall @simulation :on "tick" (fn [e]
                                         ;(js/console.log "TICK" @links)

                                         (dispatch [::mem-graph/store-positions {:nodes (ocall @simulation :nodes)
                                                                                 :links (or @jslinks (clj->js []))}])))
         ; force the react component to update
         (r/force-update this))
       :component-did-update
       (fn [this]
         ;(js/console.log "UPDATE NODES" (clj->js (-> this r/props :nodes)))
         (js/console.log "UPDATE LINKS" (reset! jslinks (clj->js (-> this r/props :links))))
         ; add the nodes from the props to the simulation
         (ocall @simulation :nodes (clj->js (-> this r/props :nodes)))

         (-> @simulation
             (ocall :force "link" (-> d3
                                      (ocall :forceLink)
                                      (ocall :id (fn [d]
                                                   (oget d :local-attributes :id)))
                                      (ocall :links (or @jslinks (clj->js [])))
                                      )))
         ;(js/console.log "sim" @simulation)

         ;(js/console.log "UPDATE LINKS22222222" @jslinks)
         ; and restart it
         (-> @simulation (ocall :alpha 1) (ocall :restart)))
       :reagent-render
       (fn []
         [graph-inner])})))

(defn main []
  (let [orgs      (subscribe [::mem-assets/assets :orgs])
        nodes     (subscribe [::mem-graph/nodes])
        links     (subscribe [::mem-graph/links])
        the-nodes (r/atom [{:id "A"} {:id "B"} {:id "C"} {:id "D"}])
        ]
    (fn []
      [:div
       (js/console.log "NODES" @nodes)
       (js/console.log "LINKS" @links)
       #_(js/console.log "NN" (map (fn [id] {:id    id
                                             :label "ABC"}) (keys @nodes)))
       ;(js/console.log "links" @links)
       [:button {:on-click (fn []
                             (swap! the-nodes conj {:id "E"}))} "clicky"]
       (when @nodes [graphit {:nodes (or (clj->js (seq @nodes)) [])
                              :links (or (clj->js @links) (clj->js []))}])
       [:button {:on-click (fn []
                             ;(dispatch [::mem-graph/fetch-node 3078632563232929])
                             (dispatch [::mem-graph/fetch-node "52578646044903633"])
                             )} "load node"]

       #_[d2g {:graphData       {:nodes (map (fn [id]
                                               ;(js/console.log "typeis" (type id))
                                               {:id        id
                                                :nodeLabel "ABC"}) (keys @nodes))
                                 :links @links}
               :width           400
               :height          400
               :backgroundColor "aliceblue"
               :nodeLabel       (fn [d]
                                  "IAMLABEL"
                                  )
               :on-node-click   (fn [e]
                                  (let [e (js->clj e :keywordize-keys true)]
                                    (dispatch [::mem-graph/fetch-node-references (:id e)])))}]

       [:h1 "Organisations"]
       [org-list @orgs]
       [:button {:on-click (fn []
                             (dispatch [::mem-assets/fetch-asset :orgs])
                             )} "click"]])))