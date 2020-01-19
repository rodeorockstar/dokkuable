(ns archo.views.uploader
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]
            [oops.core :refer [oget]]
            [archo.mem.uploader :as mem-uploader]
            [clojure.contrib.humanize :as h]
            [cljs.core.async :as async]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [ajax.core :as ajax :refer [GET POST]]

            ))


(defn random-file []
  (js/Blob. [js/ArrayBuffer 100000000000000000000000] {:type "application/octet-stream"}))


(defn staged-files-list []
  (fn [{:keys [files details]}]
    [:table.table
     (into [:tbody]
           (map (fn [{:keys [name lastModified lastModifiedDate size :as f]}]
                  [:tr
                   [:td
                    [:div name]
                    [:div (h/filesize size :format " %.0f" " ")]]
                   [:td]])
                details))]))

(defn main []
  (let [staged-files        (subscribe [::mem-uploader/files])
        staged-file-details (subscribe [::mem-uploader/file-details])
        progress-chan       (subscribe [::mem-uploader/progress-chan])]
    (fn []
      [:div.p-4.border.shadow.bg-light

       (js/console.log "P" @progress-chan @staged-files)


       [:div.form
        [:div.form-group
         [:input {:type      "file"
                  :multiple  true
                  :on-change (fn [e]
                               (dispatch [::mem-uploader/add-file (oget e :target :files)]))}]]]

       [staged-files-list {:files @staged-files :details @staged-file-details}]

       [:button.btn.btn-info
        {:on-click (fn [] (dispatch [::mem-uploader/upload-file @staged-files "cbs"]))}
        "Upload"]

       [:button.btn.btn-info
        {:on-click (fn []



                     (let [form-data (doto
                                       (js/FormData.)
                                       (.append "org" "cbs")
                                       (.append "file" (first @staged-files))
                                       (.append "file" (second @staged-files)))]
                       (POST "http://localhost:5000/assets/upload" {:body            form-data
                                                                    ;:response-format (ajax/transit-request-format)
                                                                    :response-format (ajax/raw-response-format)
                                                                    ;:headers         {:Accept "application/edn"
                                                                    ;                  :Content-Type "application/edn"}

                                                                    ;:timeout         100
                                                                    ;:format :json
                                                                    :progress-handler (fn [p] (js/console.log "PROGRESS" p))
                                                                    })

                       #_(GET "http://localhost:5000/api/admin/orgs" {:response-format (ajax/raw-response-format)
                                                                      :headers         {:Accept "application/edn"}})

                       )

                     ) #_(fn []
                           (js/console.log "X" (random-file))
                           (let [progress-channel (async/chan)]
                             (let [r (http/post "http://localhost:5000/assets/upload"
                                                {:multipart-params [["org" "cbs"]
                                                                    ["file" (random-file)]
                                                                    ["file" (random-file)]]
                                                 :progress         progress-channel})]
                               (go-loop []
                                        (let [ppp (<! progress-channel)]
                                          (js/console.log "progress:" ppp (gensym))
                                          (recur)))

                               )))}
        "Rando"]

       ])))