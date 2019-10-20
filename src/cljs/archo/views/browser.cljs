(ns archo.views.browser
  (:require [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.assets :as mem-assets]
            [archo.views.kinds.pdf :as pdf]
            [archo.mem.graph :as mem-graph]
            [archo.views.wizard :as wizard]))


(defn main []
  (fn []
    [:div "browser"]))