(ns archo.views.fs
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.upload :as mem-upload]
            [archo.mem.assets :as mem-assets]
            [archo.views.kinds.pdf :as pdf]
            [archo.routes :as routes]
            [reitit.core :as reitit]))

(defn main []
  (fn []
    [:h1 "files"]))