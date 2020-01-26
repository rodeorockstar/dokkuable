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
            [cuerdas.core :as str]
            [archo.mem.view :as mem-view]
            [cljs.core.async :refer [<!]]
            [archo.routes :refer [url-for]]
            [ajax.core :as ajax :refer [GET POST]]))

(defn reset-input! [atom]
  (fn [evt]
    (reset! atom (oget evt :target :value))))

(defn modal-upload []
  (let [new-key      (r/atom nil)
        file-details (subscribe [::mem-uploader/file-details])
        files        (subscribe [::mem-uploader/files])
        status       (subscribe [::mem-uploader/uploader-status])
        file-el      (r/atom nil)]
    (r/create-class
      {:component-will-unmount (fn []
                                 (dispatch [::mem-uploader/add-file nil]))
       :reagent-render         (fn [{:keys [Prefix]}]
                                 (js/console.log "files" @files)
                                 [:div.popup
                                  [:div.popup-background
                                   [:div.popup-dialog.rounded.shadow
                                    [:div.popup-body
                                     [:div.container
                                      [:div.form


                                       (if (not-empty @files)
                                         [:table.table
                                          [:thead
                                           [:tr
                                            [:th "File"]
                                            [:th "Size"]]]
                                          (into [:tbody]
                                                (map (fn [{:keys [name size]}]
                                                       [:tr
                                                        [:td name]
                                                        [:td (h/filesize size)]]
                                                       ) @file-details))]
                                         [:form.p-4.dropzone.border-primary.rounded.d-flex.align-items-center.justify-content-center
                                          {:on-drop      (fn [e]
                                                           (ocall e :preventDefault)
                                                           (ocall e :stopPropagation)
                                                           (js/console.log "DROPPING")
                                                           (dispatch [::mem-uploader/add-file (oget e :dataTransfer :files)]))
                                           :on-drag-over (fn [e]
                                                           (ocall e :preventDefault)
                                                           (ocall e :stopPropagation)
                                                           (js/console.log "DRAGOVER"))
                                           :on-click     (fn []
                                                           (ocall @file-el :click))}
                                          [:div.d-flex.flex-column.align-items-center
                                           [:h4.display-4 [:i.fal.fa-arrow-to-bottom]]
                                           [:div [:span.font-weight-bold "Choose files"] " or drag them here."]]
                                          ])

                                       [:div.form.d-none
                                        [:div.form-group
                                         [:input {:type      "file"
                                                  :ref       (fn [e] (reset! file-el e))
                                                  :multiple  true
                                                  :on-change (fn [e]
                                                               (dispatch [::mem-uploader/add-file (oget e :target :files)]))}]]]







                                       [:div.mt-4
                                        [:button.btn
                                         {:on-click (fn [e] (dispatch [::mem-assets/close-modal]))}
                                         "Cancel"]
                                        [:button.btn.btn-primary.ml-2
                                         {:on-click (fn [] (dispatch [::mem-uploader/upload-file @files Prefix]))}
                                         (when (= :uploading @status) [:span.spinner-border.spinner-border-sm])
                                         [:span.ml-2 (if (= :uploading @status)
                                                       "Uploading"
                                                       "Upload")]]]]]]]]])})))

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

(defn key->friendly [s]
  (last (str/split s #"/")))

(defn modal-delete []
  (let [new-key (r/atom nil)]
    (fn [{:keys [Prefix]} selected-key]
      [:div.popup
       [:div.popup-background
        [:div.popup-dialog.rounded.shadow
         [:div.popup-body
          [:div.container
           [:div.alert.alert-danger
            [:h4 "Are you sure you want to delete this file?"]]
           [:h4 (key->friendly selected-key)]
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


(defn breadcrumb-list []
  (fn [steps]
    (into [:div.d-flex.display-4]
          (interpose
            [:div.mx-2 "/"]
            (map (fn [{:keys [Display Prefix Position]}]
                   [:div (case Position
                           :first [:i.fas.fa-home-alt]
                           [:div Display])]
                   ) steps)
            ))
    ))

(def button-kind "btn-white")

(defn content-table []
  (let [r  (subscribe [::mem-view/active-space])
        a  (subscribe [::mem-view/active])
        ar (subscribe [::mem-view/active-route])]
    (fn [{{:keys [Contents CommonPrefixes Prefix] :as x} :files
          {:keys [back breadcrumb]}                      :nav
          selected-key                                   :selected-key}]


      [:div
       ;[breadcrumb-list breadcrumb]

       [:h4.display-5.text-truncate (str Prefix)]

       [:div.d-flex

        ; left
        [:div.flex-grow-1

         [:div.btn-group.mr-2
          [:a.btn.btn-lg
           {:class button-kind
            :href  (url-for :route/upload @a {:Prefix back})}
           [:i.fal.fa-arrow-alt-left]]]


         [:div.btn-group.mr-2

          [:button.btn.btn-lg
           {:class    button-kind
            :on-click (fn [] (dispatch [::mem-assets/show-modal :new-folder]))}
           [:i.fal.fa-folder-plus]]
          [:button.btn.btn-lg
           {
            ;:disabled (not selected-key)
            ;:class (when-not selected-key "disabled" )
            :class    button-kind
            :on-click (fn [] (dispatch [::mem-assets/show-modal :upload]))}
           [:i.fal.fa-upload]]

          ]

         ]

        ; right

        [:div.flex-grow-0

         [:button.btn.btn-lg
          {:disabled (not selected-key)
           :class    button-kind
           ;:class (when-not selected-key "disabled" )
           :on-click (fn [] (dispatch [::mem-assets/show-modal :delete]))}
          [:i.fal.fa-trash]]

         ]
        ]




       [:table.table.table-hover
        [:thead

         [:tr
          [:th nil]
          [:th "Name"]
          [:th "Nodes"]
          [:th "Size"]
          ]]
        [:tbody
         [:<> (doall (map (fn [{:keys [Prefix Display]}]
                            ^{:key Prefix} [:tr
                                            {:class    (when (= Prefix selected-key) "table-primary text-white")
                                             :on-click (fn []
                                                         (dispatch [::mem-assets/set-selected-key Prefix]))}
                                            [:td [:i.fal.fa-folder]]
                                            [:td
                                             [:a.text-decoration-none.text-reset {:href (url-for :route/upload @a {:Prefix Prefix})}
                                              Display]]
                                            [:td]
                                            [:td]])
                          CommonPrefixes))]
         [:<> (doall (map (fn [{:keys [Display Key LastModified ETag Size StorageClass Count] :as p}]
                            ^{:key Key} [:tr
                                         {:class    (when (= Key selected-key) "table-primary text-white")
                                          :on-click (fn []
                                                      (dispatch [::mem-assets/set-selected-key Key]))}
                                         [:td [:i.fal.fa-file]]
                                         [:td
                                          {:style {:word-wrap   "break-word"
                                                   :white-space "pre-wrap"
                                                   :word-break  "break-all"}}
                                          Display]
                                         [:td [:span.badge.badge-success Count]]
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
      ;(js/console.log "NAV" @nav)
      [:div.border.shadow.p-4 ;.p-4.border.shadow.bg-light

       ;(js/console.log "P" @progress-chan @staged-files)
       ;(js/console.log "FS" @fs)

       [file-table {:files @fs :nav @nav :selected-key @selected-key}]




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
           :delete [modal-delete @fs @selected-key]
           :upload [modal-upload @fs @selected-key]
           ))
       ])))