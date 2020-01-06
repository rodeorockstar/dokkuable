(ns archo.views.wizard
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.upload :as mem-upload]
            [archo.views.kinds.pdf :as pdf]))



(defn main []
  (let [file (subscribe [::mem-upload/stage-file])
        r (subscribe [:archo.mem.view/active-space])]
    (fn []
      [:div.mb-2.border-bottom
       [:input {:type      "file"
                :on-change (fn [e]

                             (dispatch [::mem-upload/store-stage-file
                                        (first (array-seq (oget e :target :files)))])
                             (dispatch [::mem-upload/fetch-nodes-from-object
                                        "cms-sandbox.obrizum"
                                        (oget (first (array-seq (oget e :target :files))) :name)
                                        (:node/uuid @r)
                                        ])
                             )}]
       [pdf/main {:file @file}]])))