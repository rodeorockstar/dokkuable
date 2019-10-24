(ns archo.views.browser
  (:require [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.assets :as mem-assets]
            [archo.views.kinds.pdf :as pdf]
            [archo.mem.graph :as mem-graph]
            [archo.views.wizard :as wizard]
            [archo.mem.browser :as mem-browser]))


(def t
  {:db/id 1234567
   :local-attributes {:db/id 1234567
                      :node/uuid (random-uuid)
                      }})

(defn entity []
  (fn [e]
    [:pre {} (str e)]
    ))

(defn main []
  (let [schema (subscribe [::mem-browser/schema])]
    (fn []
      (js/console.log "S" @schema)
     [:div "browser"
      [:button {:on-click (fn []
                            ;(dispatch [::mem-graph/fetch-node 3078632563232929])
                            (dispatch [::mem-graph/fetch-node "52578646044903633"])
                            )} "load node"]
      [entity @schema]])))