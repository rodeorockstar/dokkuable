(ns archo.views.wizard
  (:require [reagent.core :as r]
            [re-frame.core :refer [dispatch subscribe]]
            [oops.core :refer [oget]]
            [archo.mem.upload :as mem-upload]
            [archo.mem.assets :as mem-assets]
            [archo.mem.view :as mem-view]
            [archo.views.kinds.pdf :as pdf]
            [cljs.core.async :as async]
            [archo.routes :refer [url-for]]))



(defn file-dropdown []
  (let [r (subscribe [:archo.mem.view/active-space])
        theview (subscribe [::mem-view/active])]
    (fn [files]
      [:div
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
                    ) files))
       #_[:a.btn.btn-secondary {:href (url-for :route/upload @theview)} "Upload"]
       ])))


(defn uploader []
  (let [active-org (subscribe [::mem-view/active-org])
        f (r/atom nil)
        theview (subscribe [::mem-view/active])
        progress-channel (async/chan)]
    (fn []
      [:div.p-4.border.shadow.bg-light
       (js/console.log "FFF" @f)
       [:h4 "Upload a file"]
       [:div.form
        [:div.form-group
         [:input {:type      "file"
                  :on-change (fn [e]

                               (reset! f (first (array-seq (oget e :target :files))))
                               #_(dispatch [::mem-upload/fetch-nodes-from-object
                                            "cms-sandbox.obrizum"
                                            (oget (first (array-seq (oget e :target :files))) :name)
                                            (:node/uuid @r)
                                            ])
                               )}]]]
       [:button.btn.btn-outline-secondary
        {:on-click (fn [] (dispatch [::mem-upload/upload-file @f (:org/short-name @active-org)]))}
        "Upload"]


       ])))

(defn main []
  (let [file            (subscribe [::mem-upload/stage-file])
        r               (subscribe [:archo.mem.view/active-space])
        available-files (subscribe [::mem-assets/available-files])]
    (fn []
      [:div.mb-2.border-bottom
       ;[:h3 @file]
       [file-dropdown @available-files]
       ;[uploader]
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