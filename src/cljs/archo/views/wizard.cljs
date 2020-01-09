(ns archo.views.wizard
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.upload :as mem-upload]
            [archo.mem.assets :as mem-assets]
            [archo.views.kinds.pdf :as pdf]))



(defn file-dropdown []
  (let [r (subscribe [:archo.mem.view/active-space])]
    (fn [files]
     (into [:select {:on-change (fn [e]

                                  (dispatch [::mem-upload/store-stage-file (oget e :target :value)])

                                  (dispatch [::mem-upload/fetch-nodes-from-object
                                             "cms-sandbox.obrizum"
                                             (oget e :target :value)
                                             (:node/uuid @r)
                                             ])



                                  #_(js/console.log (str "https://s3-eu-west-1.amazonaws.com/cms-sandbox.obrizum/"
                                                         (js/encodeURIComponent (oget e :target :value))
                                                         )))}
            [:option " - "]
            ]
           (map (fn [f]
                  [:option (:Key f)]
                  ) files)))))

(defn main []
  (let [file (subscribe [::mem-upload/stage-file])
        r (subscribe [:archo.mem.view/active-space])
        available-files (subscribe [::mem-assets/available-files])]
    (fn []
      [:div.mb-2.border-bottom
       [:h3 @file]
       [file-dropdown @available-files]
       #_[:input {:type      "file"
                :on-change (fn [e]

                             (dispatch [::mem-upload/store-stage-file
                                        (first (array-seq (oget e :target :files)))])
                             (dispatch [::mem-upload/fetch-nodes-from-object
                                        "cms-sandbox.obrizum"
                                        (oget (first (array-seq (oget e :target :files))) :name)
                                        (:node/uuid @r)
                                        ])
                             )}]
       (when @file [pdf/main {:file @file}])])))