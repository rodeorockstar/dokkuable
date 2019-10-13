(ns archo.views.org
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.upload :as mem-upload]
            [archo.mem.assets :as mem-assets]
            [archo.views.kinds.pdf :as pdf]
            [archo.routes :as routes]))


(defn main []
  (fn []
    [:div "an org"]))