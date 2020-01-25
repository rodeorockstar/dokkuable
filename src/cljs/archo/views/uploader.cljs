(ns archo.views.uploader
  (:require-macros [cljs.core.async.macros :refer [go go-loop]])
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch]]
            [oops.core :refer [oget ocall]]
            [archo.mem.uploader :as mem-uploader]
            [clojure.contrib.humanize :as h]
            [cljs.core.async :as async]
            [archo.mem.assets :as mem-assets]
            [cljs-http.client :as http]
            [archo.mem.view :as mem-view]
            [cljs.core.async :refer [<!]]
            [archo.routes :refer [url-for]]
            [ajax.core :as ajax :refer [GET POST]]))

(defn reset-input! [atom]
  (fn [evt]
    (reset! atom (oget evt :target :value))))

(defn modal-new-folder []
  (let [new-key (r/atom nil)]
    (fn [{:keys [Prefix]}]
      [:div.popup
       [:div.popup-background
        [:div.popup-dialog.rounded.shadow
         [:div.popup-body
          [:div.container
           [:div.form
            [:div.form-group
             [:label "Name"]
             [:input.form-control {:type      "text"
                                   :value     @new-key
                                   :on-change (reset-input! new-key)}]]
            [:button.btn
             {:on-click (fn [e] (dispatch [::mem-assets/close-modal]))}
             "Cancel"]
            [:button.btn.btn-primary.ml-2
             {:on-click (fn [] (dispatch [::mem-assets/mkdir Prefix @new-key]))}
             "Save"]]]]]]])))

(defn modal-delete []
  (let [new-key (r/atom nil)]
    (fn [{:keys [Prefix]} selected-key]
      [:div.popup
       [:div.popup-background
        [:div.popup-dialog.rounded.shadow
         [:div.popup-body
          [:div.container
           [:div.alert.alert-danger
            [:h4 "Are you sure you want to delete this file?"]
            [:code (str selected-key)]]
           [:button.btn
            {:on-click (fn [e] (dispatch [::mem-assets/close-modal]))}
            "Cancel"]
           [:button.btn.btn-danger.ml-2
            {:on-click (fn [] (dispatch [::mem-assets/rm Prefix selected-key]))}
            "Delete"]
           #_[:div.form
            [:div.form-group
             [:label "Delete" (str selected-key)]
             [:input.form-control {:type      "text"
                                   :value     @new-key
                                   :on-change (reset-input! new-key)}]]
            [:button.btn
             {:on-click (fn [e] (dispatch [::mem-assets/close-modal]))}
             "Cancel"]
            [:button.btn.btn-primary.ml-2
             {:on-click (fn [] (dispatch [::mem-assets/mkdir Prefix @new-key]))}
             "Save"]]]]]]])))

(defn content-table []
  (let [r  (subscribe [::mem-view/active-space])
        a  (subscribe [::mem-view/active])
        ar (subscribe [::mem-view/active-route])]
    (fn [{{:keys [Contents CommonPrefixes Prefix] :as x} :files
          {:keys [back]}                                 :nav
          selected-key                                   :selected-key}]
      [:div


       [:h4.display-5.text-truncate.text-monospace (str Prefix)]
       [:div.btn-group
        [:a.btn.btn-lg
         {:href (url-for :route/upload @a {:Prefix back})}
         [:i.fal.fa-arrow-alt-left]]
        [:button.btn.btn-lg
         {:on-click (fn [] (dispatch [::mem-assets/show-modal :new-folder]))}
         [:i.fal.fa-folder-plus]]
        [:button.btn.btn-lg
         {:disabled (not selected-key)
          :class (when-not selected-key "disabled" )
          :on-click (fn [] (dispatch [::mem-assets/show-modal :delete]))
          ;:on-click (fn [] (dispatch [::mem-assets/rm Prefix selected-key]))
          }
         [:i.fal.fa-trash]]

        ]
       [:table.table
        [:thead

         [:tr
          [:th nil]
          [:th "Name"]
          [:th "Size"]
          ]]
        [:tbody
         [:<> (doall (map (fn [{:keys [Prefix Display]}]
                            ^{:key Prefix} [:tr
                                            [:td [:i.fas.fa-folder]]
                                            [:td.text-monospace
                                             [:a {:href (url-for :route/upload @a {:Prefix Prefix})}
                                              Display]]
                                            [:td]])
                          CommonPrefixes))]
         [:<> (doall (map (fn [{:keys [Display Key LastModified ETag Size StorageClass] :as p}]
                            ^{:key Key} [:tr.text-monospace
                                         {:class (when (= Key selected-key) "table-primary")
                                          :on-click (fn []
                                                      (dispatch [::mem-assets/set-selected-key Key]))}
                                         [:td [:i.fad.fa-file]]
                                         [:td.text-monospace
                                          {:style {:word-wrap   "break-word"
                                                   :white-space "pre-wrap"
                                                   :word-break  "break-all"}}
                                          Display]
                                         [:td (h/filesize Size)]
                                         #_[:td [:button.btn.btn-outline-secondary
                                                 {:on-click (fn []
                                                              (dispatch [::mem-uploader/make-video {:s3/key     Key
                                                                                                    :space/uuid (:node/uuid @r)}]))}
                                                 "Video"]]])
                          Contents))]]]]
      )))

(defn file-table []
  (fn [x]
    ;(js/console.log "DDDDDDD" x)
    [content-table x]
    ))


;;;;;;


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
                    [:div.font-weight-lighter (h/filesize size :format " %.0f" " ")]]
                   [:td]])
                details))]))

(defn main []
  (let [staged-files        (subscribe [::mem-uploader/files])
        staged-file-details (subscribe [::mem-uploader/file-details])
        progress-chan       (subscribe [::mem-uploader/progress-chan])
        fs                  (subscribe [::mem-assets/current-files])
        nav                 (subscribe [::mem-assets/nav])
        selected-key        (subscribe [::mem-assets/selected-key])
        showing-modal       (subscribe [::mem-assets/showing-modal])]
    (fn []
      (js/console.log "SHOWING" @showing-modal)
      [:div.p-4.border.shadow.bg-light

       ;(js/console.log "P" @progress-chan @staged-files)
       ;(js/console.log "FS" @fs)

       [file-table {:files @fs :nav @nav :selected-key @selected-key}]


       #_[:div.form
          [:div.form-group
           [:input {:type      "file"
                    :multiple  true
                    :on-change (fn [e]
                                 (dispatch [::mem-uploader/add-file (oget e :target :files)]))}]]]

       #_[staged-files-list {:files @staged-files :details @staged-file-details}]

       #_[:button.btn.btn-info
          {:on-click (fn [] (dispatch [::mem-uploader/upload-file @staged-files "cbs"]))}
          "Upload"]

       #_[:button.btn.btn-info
          {:on-click (fn []



                       (dispatch [::mem-assets/ls "edw/"])

                       )}
          "List Files"]

       #_[:button.btn.btn-info
          {:on-click (fn []



                       (let [form-data (doto
                                         (js/FormData.)
                                         (.append "org" "cbs")
                                         (.append "file" (first @staged-files))
                                         (.append "file" (second @staged-files)))]
                         (POST "http://localhost:5000/assets/upload" {:body             form-data
                                                                      ;:response-format (ajax/transit-request-format)
                                                                      :response-format  (ajax/raw-response-format)
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
       (when-let [modal-kw @showing-modal]
         (case modal-kw
           :new-folder [modal-new-folder @fs]
           :delete [modal-delete @fs @selected-key]))
       ])))