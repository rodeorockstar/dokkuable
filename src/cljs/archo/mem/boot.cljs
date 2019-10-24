(ns archo.mem.boot
  (:require [re-frame.core :refer [reg-event-db]]))

(reg-event-db
  ::initialize-db
  (fn [_ _]
    {:deliveries {"A" {:label "Bucket A"
                       :id    "A"}
                  "B" {:label "Bucket B"
                       :id    "B"}
                  "C" {:label "Bucket C"
                       :id    "C"}
                  "D" {:label "Bucket D"
                       :id    "C"}
                  "E" {:label "Bucket E"
                       :id    "C"}
                  "F" {:label "Bucket F"
                       :id    "C"}
                  "G" {:label "Bucket G"
                       :id    "C"}}
     :datomic    {:schema (into (sorted-map) {:lang/tr                                                #:db{:id          497,
                                                                                                           :ident       :lang/tr,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Turkish"},
                                              :lang/ur                                                #:db{:id          504,
                                                                                                           :ident       :lang/ur,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Urdu"},
                                              :question.answer-type/open-answer                       #:db{:id 63802460742494806, :ident :question.answer-type/open-answer},
                                              :image/svg+xml                                          #:db{:id 61550660926707728, :ident :image/svg+xml},
                                              :lang/et                                                #:db{:id          375,
                                                                                                           :ident       :lang/et,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Estonian"},
                                              :product/size                                           #:db{:id          522,
                                                                                                           :ident       :product/size,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A product's unit size - a ref to a measurement {:volume/ml 5.0}"},
                                              :deprecated/pl                                          #:db{:id          266,
                                                                                                           :ident       :deprecated/pl,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Polish"},
                                              :perm/level                                             #:db{:id          539,
                                                                                                           :ident       :perm/level,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Level of the permission"},
                                              :image/png                                              #:db{:id 514571446463717, :ident :image/png},
                                              :lang/bn                                                #:db{:id          351,
                                                                                                           :ident       :lang/bn,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bengali"},
                                              :draft/clearness-ratings                                #:db{:id          626,
                                                                                                           :ident       :draft/clearness-ratings,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :noHistory   true,
                                                                                                           :doc         "The clearness ratings of the draft."},
                                              :lang/hu                                                #:db{:id          395,
                                                                                                           :ident       :lang/hu,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hungarian"},
                                              :node/ext-url                                           #:db{:id          620,
                                                                                                           :ident       :node/ext-url,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "the external URL of a node's content, e.g. a full link to a YouTube video"},
                                              :lang/vo                                                #:db{:id          508,
                                                                                                           :ident       :lang/vo,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Volapük"},
                                              :deprecated/cr                                          #:db{:id          172,
                                                                                                           :ident       :deprecated/cr,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Cree"},
                                              :deprecated/mr                                          #:db{:id          244,
                                                                                                           :ident       :deprecated/mr,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Marathi"},
                                              :lang/zh                                                #:db{:id          362,
                                                                                                           :ident       :lang/zh,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chinese"},
                                              :db.type/instant                                        {:db/id        25,
                                                                                                       :db/ident     :db.type/instant,
                                                                                                       :fressian/tag :inst,
                                                                                                       :db/doc       "Value type for instants in time. Stored internally as a number of milliseconds since midnight, January 1, 1970 UTC. Representation type will vary depending on the language you are using."},
                                              :lang/av                                                #:db{:id          343,
                                                                                                           :ident       :lang/av,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Avaric"},
                                              :deprecated/km                                          #:db{:id          219,
                                                                                                           :ident       :deprecated/km,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Central Khmer"},
                                              :learn-map/due-date                                     #:db{:id          582,
                                                                                                           :ident       :learn-map/due-date,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :question/about                                         #:db{:id          622,
                                                                                                           :ident       :question/about,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Node related to question."},
                                              :lang/ik                                                #:db{:id          401,
                                                                                                           :ident       :lang/ik,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Inupiaq"},
                                              :db/excise                                              #:db{:id          15,
                                                                                                           :ident       :db/excise,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :deprecated/id                                          #:db{:id          203,
                                                                                                           :ident       :deprecated/id,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Indonesian"},
                                              :deprecated/sc                                          #:db{:id          275,
                                                                                                           :ident       :deprecated/sc,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sardinian"},
                                              :deprecated/nv                                          #:db{:id          248,
                                                                                                           :ident       :deprecated/nv,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Navajo"},
                                              :lang/za                                                #:db{:id          516,
                                                                                                           :ident       :lang/za,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Zhuang"},
                                              :deprecated/ie                                          #:db{:id          204,
                                                                                                           :ident       :deprecated/ie,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Interlingue"},
                                              :pass/description                                       #:db{:id          105,
                                                                                                           :ident       :pass/description,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The password to redeem the pass"},
                                              :recall-context/due-date                                #:db{:id          635,
                                                                                                           :ident       :recall-context/due-date,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The date the flashcard should be recalled."},
                                              :deprecated/vi                                          #:db{:id          313,
                                                                                                           :ident       :deprecated/vi,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Vietnamese"},
                                              :lang/sk                                                #:db{:id          478,
                                                                                                           :ident       :lang/sk,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Slovak"},
                                              :format.kind/svg                                        #:db{:id 61555058973004634, :ident :format.kind/svg},
                                              :deprecated/sq                                          #:db{:id          143,
                                                                                                           :ident       :deprecated/sq,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Albanian"},
                                              :lang/lb                                                #:db{:id          423,
                                                                                                           :ident       :lang/lb,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Luxembourgish"},
                                              :draft.status/open                                      #:db{:id 40418047442694361, :ident :draft.status/open},
                                              :lang/se                                                #:db{:id          471,
                                                                                                           :ident       :lang/se,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Northern Sami"},
                                              :deprecated/si                                          #:db{:id          283,
                                                                                                           :ident       :deprecated/si,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sinhala"},
                                              :lang/am                                                #:db{:id          338,
                                                                                                           :ident       :lang/am,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Amharic"},
                                              :deprecated/eo                                          #:db{:id          180,
                                                                                                           :ident       :deprecated/eo,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Esperanto"},
                                              :lang/ia                                                #:db{:id          396,
                                                                                                           :ident       :lang/ia,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Interlingua"},
                                              :deprecated/st                                          #:db{:id          287,
                                                                                                           :ident       :deprecated/st,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Southern Sotho"},
                                              :deprecated/mi                                          #:db{:id          243,
                                                                                                           :ident       :deprecated/mi,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Maori"},
                                              :deprecated/nr                                          #:db{:id          256,
                                                                                                           :ident       :deprecated/nr,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "South Ndebele"},
                                              :lang/kw                                                #:db{:id          364,
                                                                                                           :ident       :lang/kw,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Cornish"},
                                              :deprecated/tg                                          #:db{:id          295,
                                                                                                           :ident       :deprecated/tg,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tajik"},
                                              :draft/obsoleted-editors                                #:db{:id          589,
                                                                                                           :ident       :draft/obsoleted-editors,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many}},
                                              :lang/ho                                                #:db{:id          394,
                                                                                                           :ident       :lang/ho,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hiri Motu"},
                                              :deprecated.edge/path-uuid                              #:db{:id          126,
                                                                                                           :ident       :deprecated.edge/path-uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The path this edge is part of - there can be many paths per set/space"},
                                              :pass/redeemers                                         #:db{:id          110,
                                                                                                           :ident       :pass/redeemers,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "The courses to which this pass grants access"},
                                              :address/postcode-str                                   #:db{:id          74,
                                                                                                           :ident       :address/postcode-str,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Address postcode"},
                                              :deprecated/bs                                          #:db{:id          160,
                                                                                                           :ident       :deprecated/bs,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bosnian"},
                                              :person/telephone-number-str                            #:db{:id          86,
                                                                                                           :ident       :person/telephone-number-str,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's telephone number"},
                                              :lang/fy                                                #:db{:id          512,
                                                                                                           :ident       :lang/fy,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Western Frisian"},
                                              :lang/sn                                                #:db{:id          476,
                                                                                                           :ident       :lang/sn,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Shona"},
                                              :feedback.reason/topic-not-fully-understood             #:db{:id    48097036650339240,
                                                                                                           :ident :feedback.reason/topic-not-fully-understood},
                                              :lang/cy                                                #:db{:id          510,
                                                                                                           :ident       :lang/cy,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Welsh"},
                                              :lang/ar                                                #:db{:id          339,
                                                                                                           :ident       :lang/ar,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Arabic"},
                                              :lang/na                                                #:db{:id          441,
                                                                                                           :ident       :lang/na,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Nauru"},
                                              :db.type/boolean                                        {:db/id 24, :db/ident :db.type/boolean, :fressian/tag :bool, :db/doc "Boolean value type."},
                                              :lang/an                                                #:db{:id          340,
                                                                                                           :ident       :lang/an,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Aragonese"},
                                              :db.unique/identity                                     #:db{:id    38,
                                                                                                           :ident :db.unique/identity,
                                                                                                           :doc   "Specifies that an attribute's value is unique. Attempts to create a new entity with a colliding value for a :db.unique/value will become upserts."},
                                              :question/explanation                                   #:db{:id          570,
                                                                                                           :ident       :question/explanation,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Explanation of a question"},
                                              :lang/ve                                                #:db{:id          506,
                                                                                                           :ident       :lang/ve,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Venda"},
                                              :person/avatar                                          #:db{:id          596,
                                                                                                           :ident       :person/avatar,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Base 64 image field"},
                                              :brand/uuid                                             #:db{:id          527,
                                                                                                           :ident       :brand/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "A brand's uuid"},
                                              :lang/ts                                                #:db{:id          498,
                                                                                                           :ident       :lang/ts,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tsonga"},
                                              :length/m                                               #:db{:id          324,
                                                                                                           :ident       :length/m,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "metre"},
                                              :db.type/uri                                            {:db/id        60,
                                                                                                       :db/ident     :db.type/uri,
                                                                                                       :fressian/tag :uri,
                                                                                                       :db/doc       "Value type for URIs. Maps to java.net.URI on the JVM."},
                                              :list/index                                             #:db{:id          561,
                                                                                                           :ident       :list/index,
                                                                                                           :valueType   #:db{:id 22, :ident :db.type/long},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Index of an item in a list"},
                                              :lang/nl                                                #:db{:id          371,
                                                                                                           :ident       :lang/nl,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Dutch"},
                                              :lang/mk                                                #:db{:id          432,
                                                                                                           :ident       :lang/mk,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Macedonian"},
                                              :lang/pl                                                #:db{:id          460,
                                                                                                           :ident       :lang/pl,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Polish"},
                                              :deprecated/bh                                          #:db{:id          158,
                                                                                                           :ident       :deprecated/bh,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bihari languages"},
                                              :deprecated/gl                                          #:db{:id          189,
                                                                                                           :ident       :deprecated/gl,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Galician"},
                                              :lang/en                                                #:db{:id          373,
                                                                                                           :ident       :lang/en,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "English"},
                                              :person/tokens                                          #:db{:id          541,
                                                                                                           :ident       :person/tokens,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A relationship to a user's various tokens"},
                                              :deprecated/om                                          #:db{:id          260,
                                                                                                           :ident       :deprecated/om,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Oromo"},
                                              :lang/ml                                                #:db{:id          435,
                                                                                                           :ident       :lang/ml,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Malayalam"},
                                              :db.type/uuid                                           {:db/id        54,
                                                                                                       :db/ident     :db.type/uuid,
                                                                                                       :fressian/tag :uuid,
                                                                                                       :db/doc       "Value type for UUIDs. Maps to java.util.UUID on the JVM."},
                                              :text/url                                               #:db{:id          559,
                                                                                                           :ident       :text/url,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Text representing a URL"},
                                              :editor/obsoleted-type                                  #:db{:id          590,
                                                                                                           :ident       :editor/obsoleted-type,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :lang/tt                                                #:db{:id          499,
                                                                                                           :ident       :lang/tt,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tatar"},
                                              :text/markup                                            #:db{:id          578,
                                                                                                           :ident       :text/markup,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "Text that also contains some sort of markup (html, markdown etc.)"},
                                              :lang/hy                                                #:db{:id          341,
                                                                                                           :ident       :lang/hy,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Armenian"},
                                              :graph/matrix-shape                                     #:db{:id          114,
                                                                                                           :ident       :graph/matrix-shape,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A matrix-shape as a vector of refs to graph/bigdecs"},
                                              :accreditation-type/certificate                         #:db{:id 11175436190576811, :ident :accreditation-type/certificate},
                                              :lang/oc                                                #:db{:id          451,
                                                                                                           :ident       :lang/oc,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Occitan"},
                                              :deprecated/fa                                          #:db{:id          265,
                                                                                                           :ident       :deprecated/fa,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Persian"},
                                              :db/unique                                              #:db{:id          42,
                                                                                                           :ident       :db/unique,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Property of an attribute. If value is :db.unique/value, then attribute value is unique to each entity. Attempts to insert a duplicate value for a temporary entity id will fail. If value is :db.unique/identity, then attribute value is unique, and upsert is enabled. Attempting to insert a duplicate value for a temporary entity id will cause all attributes associated with that temporary id to be merged with the entity already in the database. Defaults to nil."},
                                              :obr/permissions                                        #:db{:id          551,
                                                                                                           :ident       :obr/permissions,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "An entities permissions to access resources"},
                                              :lang/ru                                                #:db{:id          467,
                                                                                                           :ident       :lang/ru,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Russian"},
                                              :text/text                                              #:db{:id          128,
                                                                                                           :ident       :text/text,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The actual text of this text"},
                                              :lang/gd                                                #:db{:id          475,
                                                                                                           :ident       :lang/gd,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Gaelic"},
                                              :paper/author                                           #:db{:id          138,
                                                                                                           :ident       :paper/author,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "A paper's authors"},
                                              :deprecated/co                                          #:db{:id          171,
                                                                                                           :ident       :deprecated/co,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Corsican"},
                                              :feedback.reason/believed-it-right-though-not-confident #:db{:id    25666999443952080,
                                                                                                           :ident :feedback.reason/believed-it-right-though-not-confident},
                                              :lang/dv                                                #:db{:id          370,
                                                                                                           :ident       :lang/dv,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Divehi"},
                                              :lang/nb                                                #:db{:id          446,
                                                                                                           :ident       :lang/nb,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Norwegian Bokmål"},
                                              :db/txUUID                                              #:db{:id          55,
                                                                                                           :ident       :db/txUUID,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Unique id of transaction."},
                                              :person/purchasing-rights?                              #:db{:id          90,
                                                                                                           :ident       :person/purchasing-rights?,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "States that a person has purchasing rights: true or false"},
                                              :person/classroom-timer                                 #:db{:id          599,
                                                                                                           :ident       :person/classroom-timer,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "User's classroom timer setting."},
                                              :deprecated/yi                                          #:db{:id          320,
                                                                                                           :ident       :deprecated/yi,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Yiddish"},
                                              :lang/ss                                                #:db{:id          485,
                                                                                                           :ident       :lang/ss,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Swati"},
                                              :edge/mass                                              #:db{:id          122,
                                                                                                           :ident       :edge/mass,
                                                                                                           :valueType   #:db{:id 58, :ident :db.type/double},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The mass (weight, probability) of this edge"},
                                              :deprecated/ig                                          #:db{:id          206,
                                                                                                           :ident       :deprecated/ig,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Igbo"},
                                              :deprecated/ee                                          #:db{:id          182,
                                                                                                           :ident       :deprecated/ee,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ewe"},
                                              :lang/bs                                                #:db{:id          354,
                                                                                                           :ident       :lang/bs,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bosnian"},
                                              :db.part/user                                           #:db{:id    4,
                                                                                                           :ident :db.part/user,
                                                                                                           :doc   "Name of the user partition. The user partition is analogous to the default namespace in a programming language, and should be used as a temporary home for data during interactive development."},
                                              :deprecated/bn                                          #:db{:id          157,
                                                                                                           :ident       :deprecated/bn,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bengali"},
                                              :space/accreditation-type                               #:db{:id          630,
                                                                                                           :ident       :space/accreditation-type,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "Space's accreditation types."},
                                              :product/id                                             #:db{:id          518,
                                                                                                           :ident       :product/id,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "A product ID, e.g. SKU, catalog number"},
                                              :person/active?                                         #:db{:id          545,
                                                                                                           :ident       :person/active?,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Determine's whether a user's account is active"},
                                              :certificate/completion                                 #:db{:id          603,
                                                                                                           :ident       :certificate/completion,
                                                                                                           :valueType   #:db{:id 58, :ident :db.type/double},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The completion percentage when the certificate was issued, e.g: 0.8."},
                                              :time.end/ms                                            #:db{:id          576,
                                                                                                           :ident       :time.end/ms,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "End time, in milliseconds"},
                                              :person/explorative-journey                             #:db{:id          621,
                                                                                                           :ident       :person/explorative-journey,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "User's explorative journey setting."},
                                              :pass/code                                              #:db{:id          106,
                                                                                                           :ident       :pass/code,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The password to redeem the pass"},
                                              :graph/distribution                                     #:db{:id          116,
                                                                                                           :ident       :graph/distribution,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A reference to a matrix value; i.e. {:graph/uuid :graph/matrix-value :graph/matrix-shape}"},
                                              :accreditation-type/accreditation                       #:db{:id 14944562050592940, :ident :accreditation-type/accreditation},
                                              :deprecated/fj                                          #:db{:id          184,
                                                                                                           :ident       :deprecated/fj,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Fijian"},
                                              :deprecated/he                                          #:db{:id          197,
                                                                                                           :ident       :deprecated/he,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hebrew"},
                                              :deprecated/zh                                          #:db{:id          168,
                                                                                                           :ident       :deprecated/zh,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chinese"},
                                              :draft/published-by                                     #:db{:id          616,
                                                                                                           :ident       :draft/published-by,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The manager who published the draft."},
                                              :deprecated/kk                                          #:db{:id          218,
                                                                                                           :ident       :deprecated/kk,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kazakh"},
                                              :db.excise/beforeT                                      #:db{:id          17,
                                                                                                           :ident       :db.excise/beforeT,
                                                                                                           :valueType   #:db{:id 22, :ident :db.type/long},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :lang/iu                                                #:db{:id          405,
                                                                                                           :ident       :lang/iu,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Inuktitut"},
                                              :lang/nr                                                #:db{:id          450,
                                                                                                           :ident       :lang/nr,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "South Ndebele"},
                                              :lang/id                                                #:db{:id          397,
                                                                                                           :ident       :lang/id,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Indonesian"},
                                              :lang/km                                                #:db{:id          413,
                                                                                                           :ident       :lang/km,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Central Khmer"},
                                              :module.exploration/unlocked?                           #:db{:id          604,
                                                                                                           :ident       :module.exploration/unlocked?,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Module's exploration unlocked?."},
                                              :lang/br                                                #:db{:id          355,
                                                                                                           :ident       :lang/br,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Breton"},
                                              :deprecated/en                                          #:db{:id          179,
                                                                                                           :ident       :deprecated/en,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "English"},
                                              :db.part/db                                             {:db/id                0,
                                                                                                       :db/ident             :db.part/db,
                                                                                                       :db.install/partition [#:db{:id 0, :ident :db.part/db}
                                                                                                                              #:db{:id 3, :ident :db.part/tx}
                                                                                                                              #:db{:id 4, :ident :db.part/user}],
                                                                                                       :db.install/valueType [#:db{:id 20, :ident :db.type/ref}
                                                                                                                              #:db{:id 21, :ident :db.type/keyword}
                                                                                                                              #:db{:id 22, :ident :db.type/long}
                                                                                                                              #:db{:id 23, :ident :db.type/string}
                                                                                                                              #:db{:id 24, :ident :db.type/boolean}
                                                                                                                              #:db{:id 25, :ident :db.type/instant}
                                                                                                                              #:db{:id 26, :ident :db.type/fn}
                                                                                                                              #:db{:id 54, :ident :db.type/uuid}
                                                                                                                              #:db{:id 58, :ident :db.type/double}
                                                                                                                              #:db{:id 59, :ident :db.type/float}
                                                                                                                              #:db{:id 60, :ident :db.type/uri}
                                                                                                                              #:db{:id 61, :ident :db.type/bigint}
                                                                                                                              #:db{:id 62, :ident :db.type/bigdec}],
                                                                                                       :db.install/attribute [#:db{:id 7, :ident :db/system-tx}
                                                                                                                              #:db{:id 10, :ident :db/ident}
                                                                                                                              #:db{:id 11, :ident :db.install/partition}
                                                                                                                              #:db{:id 12, :ident :db.install/valueType}
                                                                                                                              #:db{:id 13, :ident :db.install/attribute}
                                                                                                                              #:db{:id 15, :ident :db/excise}
                                                                                                                              #:db{:id 16, :ident :db.excise/attrs}
                                                                                                                              #:db{:id 17, :ident :db.excise/beforeT}
                                                                                                                              #:db{:id 18, :ident :db.excise/before}
                                                                                                                              #:db{:id 19, :ident :db.alter/attribute}
                                                                                                                              #:db{:id 39, :ident :fressian/tag}
                                                                                                                              #:db{:id 40, :ident :db/valueType}
                                                                                                                              #:db{:id 41, :ident :db/cardinality}
                                                                                                                              #:db{:id 42, :ident :db/unique}
                                                                                                                              #:db{:id 43, :ident :db/isComponent}
                                                                                                                              #:db{:id 45, :ident :db/noHistory}
                                                                                                                              #:db{:id 50, :ident :db/txInstant}
                                                                                                                              #:db{:id 51, :ident :db/fulltext}
                                                                                                                              #:db{:id 55, :ident :db/txUUID}
                                                                                                                              #:db{:id 63, :ident :db/doc}
                                                                                                                              #:db{:id 64, :ident :institution/name}
                                                                                                                              #:db{:id 65, :ident :institution/dep}
                                                                                                                              #:db{:id 66, :ident :affiliation/uuid}
                                                                                                                              #:db{:id 67, :ident :affiliation/institution}
                                                                                                                              #:db{:id 68, :ident :affiliation/dep}
                                                                                                                              #:db{:id 69, :ident :address/uuid}
                                                                                                                              #:db{:id 70, :ident :address/name}
                                                                                                                              #:db{:id 71, :ident :address/street}
                                                                                                                              #:db{:id 72, :ident :address/town}
                                                                                                                              #:db{:id 73, :ident :address/country}
                                                                                                                              #:db{:id 74, :ident :address/postcode-str}
                                                                                                                              #:db{:id 75, :ident :person/uuid}
                                                                                                                              #:db{:id 76, :ident :person/friend}
                                                                                                                              #:db{:id 77, :ident :person/friends}
                                                                                                                              #:db{:id 78, :ident :person/password}
                                                                                                                              #:db{:id 79, :ident :person/title}
                                                                                                                              #:db{:id 80, :ident :person/first-name}
                                                                                                                              #:db{:id 81, :ident :person/last-name}
                                                                                                                              #:db{:id 82, :ident :person/email}
                                                                                                                              #:db{:id 83, :ident :person/affiliations}
                                                                                                                              #:db{:id 84, :ident :person/field-of-study}
                                                                                                                              #:db{:id 85, :ident :person/job-title}
                                                                                                                              #:db{:id 86, :ident :person/telephone-number-str}
                                                                                                                              #:db{:id 87, :ident :person/billing-contact-name}
                                                                                                                              #:db{:id 88, :ident :person/address}
                                                                                                                              #:db{:id 89, :ident :person/previous-qualifications}
                                                                                                                              #:db{:id 90, :ident :person/purchasing-rights?}
                                                                                                                              #:db{:id 91, :ident :person/manages}
                                                                                                                              #:db{:id 92, :ident :password/hash}
                                                                                                                              #:db{:id 93, :ident :coc/uuid}
                                                                                                                              #:db{:id 94, :ident :coc/managee}
                                                                                                                              #:db{:id 95, :ident :coc/role}
                                                                                                                              #:db{:id 96, :ident :coc/license-count}
                                                                                                                              #:db{:id 97, :ident :space/uuid}
                                                                                                                              #:db{:id 98, :ident :space/title}
                                                                                                                              #:db{:id 99, :ident :space/point}
                                                                                                                              #:db{:id 100, :ident :role/uuid}
                                                                                                                              #:db{:id 101, :ident :role/person}
                                                                                                                              #:db{:id 102, :ident :role/space}
                                                                                                                              #:db{:id 103, :ident :role/type}
                                                                                                                              #:db{:id 104, :ident :pass/uuid}
                                                                                                                              #:db{:id 105, :ident :pass/description}
                                                                                                                              #:db{:id 106, :ident :pass/code}
                                                                                                                              #:db{:id 107, :ident :pass/valid-from}
                                                                                                                              #:db{:id 108, :ident :pass/valid-to}
                                                                                                                              #:db{:id 109, :ident :pass/courses}
                                                                                                                              #:db{:id 110, :ident :pass/redeemers}
                                                                                                                              #:db{:id 111, :ident :graph/double}
                                                                                                                              #:db{:id 112, :ident :graph/bigdec}
                                                                                                                              #:db{:id 113, :ident :graph/matrix-value}
                                                                                                                              #:db{:id 114, :ident :graph/matrix-shape}
                                                                                                                              #:db{:id 115, :ident :graph/distribution-key}
                                                                                                                              #:db{:id 116, :ident :graph/distribution}
                                                                                                                              #:db{:id 117, :ident :graph/distributions}
                                                                                                                              #:db{:id 118, :ident :node/uuid}
                                                                                                                              #:db{:id 119, :ident :node/text}
                                                                                                                              #:db{:id 120, :ident :node/edge}
                                                                                                                              #:db{:id 121, :ident :node/kind}
                                                                                                                              #:db{:id 122, :ident :edge/mass}
                                                                                                                              #:db{:id 123, :ident :edge/distributions}
                                                                                                                              #:db{:id 124, :ident :edge/node}
                                                                                                                              #:db{:id 125, :ident :edge/kind}
                                                                                                                              #:db{:id 126, :ident :deprecated.edge/path-uuid}
                                                                                                                              #:db{:id 127, :ident :text/lang}
                                                                                                                              #:db{:id 128, :ident :text/text}
                                                                                                                              #:db{:id 129, :ident :deprecated.text/tran}
                                                                                                                              #:db{:id 130, :ident :concept/key}
                                                                                                                              #:db{:id 131, :ident :question/answer}
                                                                                                                              #:db{:id 132, :ident :time/ms}
                                                                                                                              #:db{:id 133, :ident :media/extension}
                                                                                                                              #:db{:id 134, :ident :video/slide}
                                                                                                                              #:db{:id 135, :ident :note/author}
                                                                                                                              #:db{:id 136, :ident :note/space}
                                                                                                                              #:db{:id 137, :ident :paper/doi}
                                                                                                                              #:db{:id 138, :ident :paper/author}
                                                                                                                              #:db{:id 139, :ident :deprecated/ab}
                                                                                                                              #:db{:id 140, :ident :deprecated/aa}
                                                                                                                              #:db{:id 141, :ident :deprecated/af}
                                                                                                                              #:db{:id 142, :ident :deprecated/ak}
                                                                                                                              #:db{:id 143, :ident :deprecated/sq}
                                                                                                                              #:db{:id 144, :ident :deprecated/am}
                                                                                                                              #:db{:id 145, :ident :deprecated/ar}
                                                                                                                              #:db{:id 146, :ident :deprecated/an}
                                                                                                                              #:db{:id 147, :ident :deprecated/hy}
                                                                                                                              #:db{:id 148, :ident :deprecated/as}
                                                                                                                              #:db{:id 149, :ident :deprecated/av}
                                                                                                                              #:db{:id 150, :ident :deprecated/ae}
                                                                                                                              #:db{:id 151, :ident :deprecated/ay}
                                                                                                                              #:db{:id 152, :ident :deprecated/az}
                                                                                                                              #:db{:id 153, :ident :deprecated/bm}
                                                                                                                              #:db{:id 154, :ident :deprecated/ba}
                                                                                                                              #:db{:id 155, :ident :deprecated/eu}
                                                                                                                              #:db{:id 156, :ident :deprecated/be}
                                                                                                                              #:db{:id 157, :ident :deprecated/bn}
                                                                                                                              #:db{:id 158, :ident :deprecated/bh}
                                                                                                                              #:db{:id 159, :ident :deprecated/bi}
                                                                                                                              #:db{:id 160, :ident :deprecated/bs}
                                                                                                                              #:db{:id 161, :ident :deprecated/br}
                                                                                                                              #:db{:id 162, :ident :deprecated/bg}
                                                                                                                              #:db{:id 163, :ident :deprecated/my}
                                                                                                                              #:db{:id 164, :ident :deprecated/ca}
                                                                                                                              #:db{:id 165, :ident :deprecated/ch}
                                                                                                                              #:db{:id 166, :ident :deprecated/ce}
                                                                                                                              #:db{:id 167, :ident :deprecated/ny}
                                                                                                                              #:db{:id 168, :ident :deprecated/zh}
                                                                                                                              #:db{:id 169, :ident :deprecated/cv}
                                                                                                                              #:db{:id 170, :ident :deprecated/kw}
                                                                                                                              #:db{:id 171, :ident :deprecated/co}
                                                                                                                              #:db{:id 172, :ident :deprecated/cr}
                                                                                                                              #:db{:id 173, :ident :deprecated/hr}
                                                                                                                              #:db{:id 174, :ident :deprecated/cs}
                                                                                                                              #:db{:id 175, :ident :deprecated/da}
                                                                                                                              #:db{:id 176, :ident :deprecated/dv}
                                                                                                                              #:db{:id 177, :ident :deprecated/nl}
                                                                                                                              #:db{:id 178, :ident :deprecated/dz}
                                                                                                                              #:db{:id 179, :ident :deprecated/en}
                                                                                                                              #:db{:id 180, :ident :deprecated/eo}
                                                                                                                              #:db{:id 181, :ident :deprecated/et}
                                                                                                                              #:db{:id 182, :ident :deprecated/ee}
                                                                                                                              #:db{:id 183, :ident :deprecated/fo}
                                                                                                                              #:db{:id 184, :ident :deprecated/fj}
                                                                                                                              #:db{:id 185, :ident :deprecated/fl}
                                                                                                                              #:db{:id 186, :ident :deprecated/fi}
                                                                                                                              #:db{:id 187, :ident :deprecated/fr}
                                                                                                                              #:db{:id 188, :ident :deprecated/ff}
                                                                                                                              #:db{:id 189, :ident :deprecated/gl}
                                                                                                                              #:db{:id 190, :ident :deprecated/ka}
                                                                                                                              #:db{:id 191, :ident :deprecated/de}
                                                                                                                              #:db{:id 192, :ident :deprecated/el}
                                                                                                                              #:db{:id 193, :ident :deprecated/gn}
                                                                                                                              #:db{:id 194, :ident :deprecated/gu}
                                                                                                                              #:db{:id 195, :ident :deprecated/ht}
                                                                                                                              #:db{:id 196, :ident :deprecated/ha}
                                                                                                                              #:db{:id 197, :ident :deprecated/he}
                                                                                                                              #:db{:id 198, :ident :deprecated/hz}
                                                                                                                              #:db{:id 199, :ident :deprecated/hi}
                                                                                                                              #:db{:id 200, :ident :deprecated/ho}
                                                                                                                              #:db{:id 201, :ident :deprecated/hu}
                                                                                                                              #:db{:id 202, :ident :deprecated/ia}
                                                                                                                              #:db{:id 203, :ident :deprecated/id}
                                                                                                                              #:db{:id 204, :ident :deprecated/ie}
                                                                                                                              #:db{:id 205, :ident :deprecated/ga}
                                                                                                                              #:db{:id 206, :ident :deprecated/ig}
                                                                                                                              #:db{:id 207, :ident :deprecated/ik}
                                                                                                                              #:db{:id 208, :ident :deprecated/io}
                                                                                                                              #:db{:id 209, :ident :deprecated/is}
                                                                                                                              #:db{:id 210, :ident :deprecated/it}
                                                                                                                              #:db{:id 211, :ident :deprecated/iu}
                                                                                                                              #:db{:id 212, :ident :deprecated/ja}
                                                                                                                              #:db{:id 213, :ident :deprecated/jv}
                                                                                                                              #:db{:id 214, :ident :deprecated/kl}
                                                                                                                              #:db{:id 215, :ident :deprecated/kn}
                                                                                                                              #:db{:id 216, :ident :deprecated/kr}
                                                                                                                              #:db{:id 217, :ident :deprecated/ks}
                                                                                                                              #:db{:id 218, :ident :deprecated/kk}
                                                                                                                              #:db{:id 219, :ident :deprecated/km}
                                                                                                                              #:db{:id 220, :ident :deprecated/ki}
                                                                                                                              #:db{:id 221, :ident :deprecated/rw}
                                                                                                                              #:db{:id 222, :ident :deprecated/ky}
                                                                                                                              #:db{:id 223, :ident :deprecated/kv}
                                                                                                                              #:db{:id 224, :ident :deprecated/kg}
                                                                                                                              #:db{:id 225, :ident :deprecated/ko}
                                                                                                                              #:db{:id 226, :ident :deprecated/ku}
                                                                                                                              #:db{:id 227, :ident :deprecated/kj}
                                                                                                                              #:db{:id 228, :ident :deprecated/la}
                                                                                                                              #:db{:id 229, :ident :deprecated/lb}
                                                                                                                              #:db{:id 230, :ident :deprecated/lg}
                                                                                                                              #:db{:id 231, :ident :deprecated/li}
                                                                                                                              #:db{:id 232, :ident :deprecated/ln}
                                                                                                                              #:db{:id 233, :ident :deprecated/lo}
                                                                                                                              #:db{:id 234, :ident :deprecated/lt}
                                                                                                                              #:db{:id 235, :ident :deprecated/lu}
                                                                                                                              #:db{:id 236, :ident :deprecated/lv}
                                                                                                                              #:db{:id 237, :ident :deprecated/gv}
                                                                                                                              #:db{:id 238, :ident :deprecated/mk}
                                                                                                                              #:db{:id 239, :ident :deprecated/mg}
                                                                                                                              #:db{:id 240, :ident :deprecated/ms}
                                                                                                                              #:db{:id 241, :ident :deprecated/ml}
                                                                                                                              #:db{:id 242, :ident :deprecated/mt}
                                                                                                                              #:db{:id 243, :ident :deprecated/mi}
                                                                                                                              #:db{:id 244, :ident :deprecated/mr}
                                                                                                                              #:db{:id 245, :ident :deprecated/mh}
                                                                                                                              #:db{:id 246, :ident :deprecated/mn}
                                                                                                                              #:db{:id 247, :ident :deprecated/na}
                                                                                                                              #:db{:id 248, :ident :deprecated/nv}
                                                                                                                              #:db{:id 249, :ident :deprecated/nd}
                                                                                                                              #:db{:id 250, :ident :deprecated/ne}
                                                                                                                              #:db{:id 251, :ident :deprecated/ng}
                                                                                                                              #:db{:id 252, :ident :deprecated/nb}
                                                                                                                              #:db{:id 253, :ident :deprecated/nn}
                                                                                                                              #:db{:id 254, :ident :deprecated/no}
                                                                                                                              #:db{:id 255, :ident :deprecated/ii}
                                                                                                                              #:db{:id 256, :ident :deprecated/nr}
                                                                                                                              #:db{:id 257, :ident :deprecated/oc}
                                                                                                                              #:db{:id 258, :ident :deprecated/oj}
                                                                                                                              #:db{:id 259, :ident :deprecated/cu}
                                                                                                                              #:db{:id 260, :ident :deprecated/om}
                                                                                                                              #:db{:id 261, :ident :deprecated/or}
                                                                                                                              #:db{:id 262, :ident :deprecated/os}
                                                                                                                              #:db{:id 263, :ident :deprecated/pa}
                                                                                                                              #:db{:id 264, :ident :deprecated/pi}
                                                                                                                              #:db{:id 265, :ident :deprecated/fa}
                                                                                                                              #:db{:id 266, :ident :deprecated/pl}
                                                                                                                              #:db{:id 267, :ident :deprecated/ps}
                                                                                                                              #:db{:id 268, :ident :deprecated/pt}
                                                                                                                              #:db{:id 269, :ident :deprecated/qu}
                                                                                                                              #:db{:id 270, :ident :deprecated/rm}
                                                                                                                              #:db{:id 271, :ident :deprecated/rn}
                                                                                                                              #:db{:id 272, :ident :deprecated/ro}
                                                                                                                              #:db{:id 273, :ident :deprecated/ru}
                                                                                                                              #:db{:id 274, :ident :deprecated/sa}
                                                                                                                              #:db{:id 275, :ident :deprecated/sc}
                                                                                                                              #:db{:id 276, :ident :deprecated/sd}
                                                                                                                              #:db{:id 277, :ident :deprecated/se}
                                                                                                                              #:db{:id 278, :ident :deprecated/sm}
                                                                                                                              #:db{:id 279, :ident :deprecated/sg}
                                                                                                                              #:db{:id 280, :ident :deprecated/sr}
                                                                                                                              #:db{:id 281, :ident :deprecated/gd}
                                                                                                                              #:db{:id 282, :ident :deprecated/sn}
                                                                                                                              #:db{:id 283, :ident :deprecated/si}
                                                                                                                              #:db{:id 284, :ident :deprecated/sk}
                                                                                                                              #:db{:id 285, :ident :deprecated/sl}
                                                                                                                              #:db{:id 286, :ident :deprecated/so}
                                                                                                                              #:db{:id 287, :ident :deprecated/st}
                                                                                                                              #:db{:id 288, :ident :deprecated/es}
                                                                                                                              #:db{:id 289, :ident :deprecated/su}
                                                                                                                              #:db{:id 290, :ident :deprecated/sw}
                                                                                                                              #:db{:id 291, :ident :deprecated/ss}
                                                                                                                              #:db{:id 292, :ident :deprecated/sv}
                                                                                                                              #:db{:id 293, :ident :deprecated/ta}
                                                                                                                              #:db{:id 294, :ident :deprecated/te}
                                                                                                                              #:db{:id 295, :ident :deprecated/tg}
                                                                                                                              #:db{:id 296, :ident :deprecated/th}
                                                                                                                              #:db{:id 297, :ident :deprecated/ti}
                                                                                                                              #:db{:id 298, :ident :deprecated/bo}
                                                                                                                              #:db{:id 299, :ident :deprecated/tk}
                                                                                                                              #:db{:id 300, :ident :deprecated/tl}
                                                                                                                              #:db{:id 301, :ident :deprecated/tn}
                                                                                                                              #:db{:id 302, :ident :deprecated/to}
                                                                                                                              #:db{:id 303, :ident :deprecated/tr}
                                                                                                                              #:db{:id 304, :ident :deprecated/ts}
                                                                                                                              #:db{:id 305, :ident :deprecated/tt}
                                                                                                                              #:db{:id 306, :ident :deprecated/tw}
                                                                                                                              #:db{:id 307, :ident :deprecated/ty}
                                                                                                                              #:db{:id 308, :ident :deprecated/ug}
                                                                                                                              #:db{:id 309, :ident :deprecated/uk}
                                                                                                                              #:db{:id 310, :ident :deprecated/ur}
                                                                                                                              #:db{:id 311, :ident :deprecated/uz}
                                                                                                                              #:db{:id 312, :ident :deprecated/ve}
                                                                                                                              #:db{:id 313, :ident :deprecated/vi}
                                                                                                                              #:db{:id 314, :ident :deprecated/vo}
                                                                                                                              #:db{:id 315, :ident :deprecated/wa}
                                                                                                                              #:db{:id 316, :ident :deprecated/cy}
                                                                                                                              #:db{:id 317, :ident :deprecated/wo}
                                                                                                                              #:db{:id 318, :ident :deprecated/fy}
                                                                                                                              #:db{:id 319, :ident :deprecated/xh}
                                                                                                                              #:db{:id 320, :ident :deprecated/yi}
                                                                                                                              #:db{:id 321, :ident :deprecated/yo}
                                                                                                                              #:db{:id 322, :ident :deprecated/za}
                                                                                                                              #:db{:id 323, :ident :deprecated/zu}
                                                                                                                              #:db{:id 324, :ident :length/m}
                                                                                                                              #:db{:id 325, :ident :temperature/K}
                                                                                                                              #:db{:id 326, :ident :volume/m3}
                                                                                                                              #:db{:id 327, :ident :volume/litre}
                                                                                                                              #:db{:id 328, :ident :mass/kg}
                                                                                                                              #:db{:id 329, :ident :volume/ml}
                                                                                                                              #:db{:id 330, :ident :number/mole}
                                                                                                                              #:db{:id 331, :ident :time/s}
                                                                                                                              #:db{:id 332, :ident :current/A}
                                                                                                                              #:db{:id 333, :ident :lang/ab}
                                                                                                                              #:db{:id 334, :ident :lang/aa}
                                                                                                                              #:db{:id 335, :ident :lang/af}
                                                                                                                              #:db{:id 336, :ident :lang/ak}
                                                                                                                              #:db{:id 337, :ident :lang/sq}
                                                                                                                              #:db{:id 338, :ident :lang/am}
                                                                                                                              #:db{:id 339, :ident :lang/ar}
                                                                                                                              #:db{:id 340, :ident :lang/an}
                                                                                                                              #:db{:id 341, :ident :lang/hy}
                                                                                                                              #:db{:id 342, :ident :lang/as}
                                                                                                                              #:db{:id 343, :ident :lang/av}
                                                                                                                              #:db{:id 344, :ident :lang/ae}
                                                                                                                              #:db{:id 345, :ident :lang/ay}
                                                                                                                              #:db{:id 346, :ident :lang/az}
                                                                                                                              #:db{:id 347, :ident :lang/bm}
                                                                                                                              #:db{:id 348, :ident :lang/ba}
                                                                                                                              #:db{:id 349, :ident :lang/eu}
                                                                                                                              #:db{:id 350, :ident :lang/be}
                                                                                                                              #:db{:id 351, :ident :lang/bn}
                                                                                                                              #:db{:id 352, :ident :lang/bh}
                                                                                                                              #:db{:id 353, :ident :lang/bi}
                                                                                                                              #:db{:id 354, :ident :lang/bs}
                                                                                                                              #:db{:id 355, :ident :lang/br}
                                                                                                                              #:db{:id 356, :ident :lang/bg}
                                                                                                                              #:db{:id 357, :ident :lang/my}
                                                                                                                              #:db{:id 358, :ident :lang/ca}
                                                                                                                              #:db{:id 359, :ident :lang/ch}
                                                                                                                              #:db{:id 360, :ident :lang/ce}
                                                                                                                              #:db{:id 361, :ident :lang/ny}
                                                                                                                              #:db{:id 362, :ident :lang/zh}
                                                                                                                              #:db{:id 363, :ident :lang/cv}
                                                                                                                              #:db{:id 364, :ident :lang/kw}
                                                                                                                              #:db{:id 365, :ident :lang/co}
                                                                                                                              #:db{:id 366, :ident :lang/cr}
                                                                                                                              #:db{:id 367, :ident :lang/hr}
                                                                                                                              #:db{:id 368, :ident :lang/cs}
                                                                                                                              #:db{:id 369, :ident :lang/da}
                                                                                                                              #:db{:id 370, :ident :lang/dv}
                                                                                                                              #:db{:id 371, :ident :lang/nl}
                                                                                                                              #:db{:id 372, :ident :lang/dz}
                                                                                                                              #:db{:id 373, :ident :lang/en}
                                                                                                                              #:db{:id 374, :ident :lang/eo}
                                                                                                                              #:db{:id 375, :ident :lang/et}
                                                                                                                              #:db{:id 376, :ident :lang/ee}
                                                                                                                              #:db{:id 377, :ident :lang/fo}
                                                                                                                              #:db{:id 378, :ident :lang/fj}
                                                                                                                              #:db{:id 379, :ident :lang/fl}
                                                                                                                              #:db{:id 380, :ident :lang/fi}
                                                                                                                              #:db{:id 381, :ident :lang/fr}
                                                                                                                              #:db{:id 382, :ident :lang/ff}
                                                                                                                              #:db{:id 383, :ident :lang/gl}
                                                                                                                              #:db{:id 384, :ident :lang/ka}
                                                                                                                              #:db{:id 385, :ident :lang/de}
                                                                                                                              #:db{:id 386, :ident :lang/el}
                                                                                                                              #:db{:id 387, :ident :lang/gn}
                                                                                                                              #:db{:id 388, :ident :lang/gu}
                                                                                                                              #:db{:id 389, :ident :lang/ht}
                                                                                                                              #:db{:id 390, :ident :lang/ha}
                                                                                                                              #:db{:id 391, :ident :lang/he}
                                                                                                                              #:db{:id 392, :ident :lang/hz}
                                                                                                                              #:db{:id 393, :ident :lang/hi}
                                                                                                                              #:db{:id 394, :ident :lang/ho}
                                                                                                                              #:db{:id 395, :ident :lang/hu}
                                                                                                                              #:db{:id 396, :ident :lang/ia}
                                                                                                                              #:db{:id 397, :ident :lang/id}
                                                                                                                              #:db{:id 398, :ident :lang/ie}
                                                                                                                              #:db{:id 399, :ident :lang/ga}
                                                                                                                              #:db{:id 400, :ident :lang/ig}
                                                                                                                              #:db{:id 401, :ident :lang/ik}
                                                                                                                              #:db{:id 402, :ident :lang/io}
                                                                                                                              #:db{:id 403, :ident :lang/is}
                                                                                                                              #:db{:id 404, :ident :lang/it}
                                                                                                                              #:db{:id 405, :ident :lang/iu}
                                                                                                                              #:db{:id 406, :ident :lang/ja}
                                                                                                                              #:db{:id 407, :ident :lang/jv}
                                                                                                                              #:db{:id 408, :ident :lang/kl}
                                                                                                                              #:db{:id 409, :ident :lang/kn}
                                                                                                                              #:db{:id 410, :ident :lang/kr}
                                                                                                                              #:db{:id 411, :ident :lang/ks}
                                                                                                                              #:db{:id 412, :ident :lang/kk}
                                                                                                                              #:db{:id 413, :ident :lang/km}
                                                                                                                              #:db{:id 414, :ident :lang/ki}
                                                                                                                              #:db{:id 415, :ident :lang/rw}
                                                                                                                              #:db{:id 416, :ident :lang/ky}
                                                                                                                              #:db{:id 417, :ident :lang/kv}
                                                                                                                              #:db{:id 418, :ident :lang/kg}
                                                                                                                              #:db{:id 419, :ident :lang/ko}
                                                                                                                              #:db{:id 420, :ident :lang/ku}
                                                                                                                              #:db{:id 421, :ident :lang/kj}
                                                                                                                              #:db{:id 422, :ident :lang/la}
                                                                                                                              #:db{:id 423, :ident :lang/lb}
                                                                                                                              #:db{:id 424, :ident :lang/lg}
                                                                                                                              #:db{:id 425, :ident :lang/li}
                                                                                                                              #:db{:id 426, :ident :lang/ln}
                                                                                                                              #:db{:id 427, :ident :lang/lo}
                                                                                                                              #:db{:id 428, :ident :lang/lt}
                                                                                                                              #:db{:id 429, :ident :lang/lu}
                                                                                                                              #:db{:id 430, :ident :lang/lv}
                                                                                                                              #:db{:id 431, :ident :lang/gv}
                                                                                                                              #:db{:id 432, :ident :lang/mk}
                                                                                                                              #:db{:id 433, :ident :lang/mg}
                                                                                                                              #:db{:id 434, :ident :lang/ms}
                                                                                                                              #:db{:id 435, :ident :lang/ml}
                                                                                                                              #:db{:id 436, :ident :lang/mt}
                                                                                                                              #:db{:id 437, :ident :lang/mi}
                                                                                                                              #:db{:id 438, :ident :lang/mr}
                                                                                                                              #:db{:id 439, :ident :lang/mh}
                                                                                                                              #:db{:id 440, :ident :lang/mn}
                                                                                                                              #:db{:id 441, :ident :lang/na}
                                                                                                                              #:db{:id 442, :ident :lang/nv}
                                                                                                                              #:db{:id 443, :ident :lang/nd}
                                                                                                                              #:db{:id 444, :ident :lang/ne}
                                                                                                                              #:db{:id 445, :ident :lang/ng}
                                                                                                                              #:db{:id 446, :ident :lang/nb}
                                                                                                                              #:db{:id 447, :ident :lang/nn}
                                                                                                                              #:db{:id 448, :ident :lang/no}
                                                                                                                              #:db{:id 449, :ident :lang/ii}
                                                                                                                              #:db{:id 450, :ident :lang/nr}
                                                                                                                              #:db{:id 451, :ident :lang/oc}
                                                                                                                              #:db{:id 452, :ident :lang/oj}
                                                                                                                              #:db{:id 453, :ident :lang/cu}
                                                                                                                              #:db{:id 454, :ident :lang/om}
                                                                                                                              #:db{:id 455, :ident :lang/or}
                                                                                                                              #:db{:id 456, :ident :lang/os}
                                                                                                                              #:db{:id 457, :ident :lang/pa}
                                                                                                                              #:db{:id 458, :ident :lang/pi}
                                                                                                                              #:db{:id 459, :ident :lang/fa}
                                                                                                                              #:db{:id 460, :ident :lang/pl}
                                                                                                                              #:db{:id 461, :ident :lang/ps}
                                                                                                                              #:db{:id 462, :ident :lang/pt}
                                                                                                                              #:db{:id 463, :ident :lang/qu}
                                                                                                                              #:db{:id 464, :ident :lang/rm}
                                                                                                                              #:db{:id 465, :ident :lang/rn}
                                                                                                                              #:db{:id 466, :ident :lang/ro}
                                                                                                                              #:db{:id 467, :ident :lang/ru}
                                                                                                                              #:db{:id 468, :ident :lang/sa}
                                                                                                                              #:db{:id 469, :ident :lang/sc}
                                                                                                                              #:db{:id 470, :ident :lang/sd}
                                                                                                                              #:db{:id 471, :ident :lang/se}
                                                                                                                              #:db{:id 472, :ident :lang/sm}
                                                                                                                              #:db{:id 473, :ident :lang/sg}
                                                                                                                              #:db{:id 474, :ident :lang/sr}
                                                                                                                              #:db{:id 475, :ident :lang/gd}
                                                                                                                              #:db{:id 476, :ident :lang/sn}
                                                                                                                              #:db{:id 477, :ident :lang/si}
                                                                                                                              #:db{:id 478, :ident :lang/sk}
                                                                                                                              #:db{:id 479, :ident :lang/sl}
                                                                                                                              #:db{:id 480, :ident :lang/so}
                                                                                                                              #:db{:id 481, :ident :lang/st}
                                                                                                                              #:db{:id 482, :ident :lang/es}
                                                                                                                              #:db{:id 483, :ident :lang/su}
                                                                                                                              #:db{:id 484, :ident :lang/sw}
                                                                                                                              #:db{:id 485, :ident :lang/ss}
                                                                                                                              #:db{:id 486, :ident :lang/sv}
                                                                                                                              #:db{:id 487, :ident :lang/ta}
                                                                                                                              #:db{:id 488, :ident :lang/te}
                                                                                                                              #:db{:id 489, :ident :lang/tg}
                                                                                                                              #:db{:id 490, :ident :lang/th}
                                                                                                                              #:db{:id 491, :ident :lang/ti}
                                                                                                                              #:db{:id 492, :ident :lang/bo}
                                                                                                                              #:db{:id 493, :ident :lang/tk}
                                                                                                                              #:db{:id 494, :ident :lang/tl}
                                                                                                                              #:db{:id 495, :ident :lang/tn}
                                                                                                                              #:db{:id 496, :ident :lang/to}
                                                                                                                              #:db{:id 497, :ident :lang/tr}
                                                                                                                              #:db{:id 498, :ident :lang/ts}
                                                                                                                              #:db{:id 499, :ident :lang/tt}
                                                                                                                              #:db{:id 500, :ident :lang/tw}
                                                                                                                              #:db{:id 501, :ident :lang/ty}
                                                                                                                              #:db{:id 502, :ident :lang/ug}
                                                                                                                              #:db{:id 503, :ident :lang/uk}
                                                                                                                              #:db{:id 504, :ident :lang/ur}
                                                                                                                              #:db{:id 505, :ident :lang/uz}
                                                                                                                              #:db{:id 506, :ident :lang/ve}
                                                                                                                              #:db{:id 507, :ident :lang/vi}
                                                                                                                              #:db{:id 508, :ident :lang/vo}
                                                                                                                              #:db{:id 509, :ident :lang/wa}
                                                                                                                              #:db{:id 510, :ident :lang/cy}
                                                                                                                              #:db{:id 511, :ident :lang/wo}
                                                                                                                              #:db{:id 512, :ident :lang/fy}
                                                                                                                              #:db{:id 513, :ident :lang/xh}
                                                                                                                              #:db{:id 514, :ident :lang/yi}
                                                                                                                              #:db{:id 515, :ident :lang/yo}
                                                                                                                              #:db{:id 516, :ident :lang/za}
                                                                                                                              #:db{:id 517, :ident :lang/zu}
                                                                                                                              #:db{:id 518, :ident :product/id}
                                                                                                                              #:db{:id 519, :ident :deprecated.product/manufacturerd}
                                                                                                                              #:db{:id 520, :ident :product/title}
                                                                                                                              #:db{:id 521, :ident :product/description}
                                                                                                                              #:db{:id 522, :ident :product/size}
                                                                                                                              #:db{:id 523, :ident :deprecated.edge/path}
                                                                                                                              #:db{:id 524, :ident :edge/path}
                                                                                                                              #:db{:id 525, :ident :product/manufacturer}
                                                                                                                              #:db{:id 526, :ident :text/tran}
                                                                                                                              #:db{:id 527, :ident :brand/uuid}
                                                                                                                              #:db{:id 528, :ident :brand/logo-url}
                                                                                                                              #:db{:id 529, :ident :brand/color-major}
                                                                                                                              #:db{:id 530, :ident :brand/color-minor}
                                                                                                                              #:db{:id 531, :ident :org/uuid}
                                                                                                                              #:db{:id 532, :ident :org/name}
                                                                                                                              #:db{:id 533, :ident :org/short-name}
                                                                                                                              #:db{:id 534, :ident :org/brand}
                                                                                                                              #:db{:id 535, :ident :obr/uuid}
                                                                                                                              #:db{:id 536, :ident :org/spaces}
                                                                                                                              #:db{:id 537, :ident :perm/source}
                                                                                                                              #:db{:id 538, :ident :perm/target}
                                                                                                                              #:db{:id 539, :ident :perm/level}
                                                                                                                              #:db{:id 540, :ident :view/visibility}
                                                                                                                              #:db{:id 541, :ident :person/tokens}
                                                                                                                              #:db{:id 542, :ident :token/value}
                                                                                                                              #:db{:id 543, :ident :token/kind}
                                                                                                                              #:db{:id 544, :ident :token/exp}
                                                                                                                              #:db{:id 545, :ident :person/active?}
                                                                                                                              #:db{:id 546, :ident :org/urls}
                                                                                                                              #:db{:id 547, :ident :url/domain}
                                                                                                                              #:db{:id 548, :ident :person/role}
                                                                                                                              #:db{:id 549, :ident :permission/target}
                                                                                                                              #:db{:id 550, :ident :permission/level}
                                                                                                                              #:db{:id 551, :ident :obr/permissions}
                                                                                                                              #:db{:id 552, :ident :person/orgs}
                                                                                                                              #:db{:id 553, :ident :brand/dark?}
                                                                                                                              #:db{:id 554, :ident :node/active?}
                                                                                                                              #:db{:id 555, :ident :text/title}
                                                                                                                              #:db{:id 556, :ident :text/summary}
                                                                                                                              #:db{:id 557, :ident :text/description}
                                                                                                                              #:db{:id 558, :ident :brand/hero-images}
                                                                                                                              #:db{:id 559, :ident :text/url}
                                                                                                                              #:db{:id 560, :ident :nav/mode}
                                                                                                                              #:db{:id 561, :ident :list/index}
                                                                                                                              #:db{:id 562, :ident :edge/context}
                                                                                                                              #:db{:id 563, :ident :time/start}
                                                                                                                              #:db{:id 564, :ident :time/end}
                                                                                                                              #:db{:id 565, :ident :time/duration}
                                                                                                                              #:db{:id 566, :ident :response/expected-answer}
                                                                                                                              #:db{:id 567, :ident :eula/md5}
                                                                                                                              #:db{:id 568, :ident :edge/ref}
                                                                                                                              #:db{:id 569, :ident :node/ext-id}
                                                                                                                              #:db{:id 570, :ident :question/explanation}
                                                                                                                              #:db{:id 571, :ident :document/format}
                                                                                                                              #:db{:id 572, :ident :space/intro-summary}
                                                                                                                              #:db{:id 573, :ident :space/intro-video}
                                                                                                                              #:db{:id 574, :ident :node/slides}
                                                                                                                              #:db{:id 575, :ident :time.start/ms}
                                                                                                                              #:db{:id 576, :ident :time.end/ms}
                                                                                                                              #:db{:id 577, :ident :org/allow-signup}
                                                                                                                              #:db{:id 578, :ident :text/markup}
                                                                                                                              #:db{:id 579, :ident :learn-map/obsolete-difficulty}
                                                                                                                              #:db{:id 580, :ident :learn-map/interval}
                                                                                                                              #:db{:id 581, :ident :learn-map/percent-overdue}
                                                                                                                              #:db{:id 582, :ident :learn-map/due-date}
                                                                                                                              #:db{:id 583, :ident :graph/learn-map}
                                                                                                                              #:db{:id 584, :ident :learn-map/difficulty}
                                                                                                                              #:db{:id 585, :ident :certificate/title}
                                                                                                                              #:db{:id 586, :ident :certificate/course}
                                                                                                                              #:db{:id 587, :ident :certificate/subject}
                                                                                                                              #:db{:id 588, :ident :certificate/issued-on}
                                                                                                                              #:db{:id 589, :ident :draft/obsoleted-editors}
                                                                                                                              #:db{:id 590, :ident :editor/obsoleted-type}
                                                                                                                              #:db{:id 591, :ident :editor/obsoleted-person}
                                                                                                                              #:db{:id 592, :ident :certificate/expires-on}
                                                                                                                              #:db{:id 593, :ident :feedback/reason}
                                                                                                                              #:db{:id 594, :ident :feedback/person-question-edge}
                                                                                                                              #:db{:id 595, :ident :feedback/target}
                                                                                                                              #:db{:id 596, :ident :person/avatar}
                                                                                                                              #:db{:id 597, :ident :person/is-memorizing}
                                                                                                                              #:db{:id 598, :ident :person/is-mastering}
                                                                                                                              #:db{:id 599, :ident :person/classroom-timer}
                                                                                                                              #:db{:id 600, :ident :text/learning-outcomes}
                                                                                                                              #:db{:id 601, :ident :text/info}
                                                                                                                              #:db{:id 602, :ident :matrix/string}
                                                                                                                              #:db{:id 603, :ident :certificate/completion}
                                                                                                                              #:db{:id 604, :ident :module.exploration/unlocked?}
                                                                                                                              #:db{:id 605, :ident :comment-thread/id}
                                                                                                                              #:db{:id 606, :ident :comment-thread/comments}
                                                                                                                              #:db{:id 607, :ident :comment/id}
                                                                                                                              #:db{:id 608, :ident :comment/user}
                                                                                                                              #:db{:id 609, :ident :comment/content}
                                                                                                                              #:db{:id 610, :ident :comment/created-at}
                                                                                                                              #:db{:id 611, :ident :node/comment-threads}
                                                                                                                              #:db{:id 612, :ident :draft/answers}
                                                                                                                              #:db{:id 613, :ident :draft-answer/mass}
                                                                                                                              #:db{:id 614, :ident :draft/answer}
                                                                                                                              #:db{:id 615, :ident :draft/status}
                                                                                                                              #:db{:id 616, :ident :draft/published-by}
                                                                                                                              #:db{:id 617, :ident :question/flashcard?}
                                                                                                                              #:db{:id 618, :ident :question/documents}
                                                                                                                              #:db{:id 619, :ident :node/ext-provider}
                                                                                                                              #:db{:id 620, :ident :node/ext-url}
                                                                                                                              #:db{:id 621, :ident :person/explorative-journey}
                                                                                                                              #:db{:id 622, :ident :question/about}
                                                                                                                              #:db{:id 623, :ident :brand/img-url}
                                                                                                                              #:db{:id 624, :ident :draft/final-ratings}
                                                                                                                              #:db{:id 625, :ident :draft/originality-ratings}
                                                                                                                              #:db{:id 626, :ident :draft/clearness-ratings}
                                                                                                                              #:db{:id 627, :ident :rating/author}
                                                                                                                              #:db{:id 628, :ident :rating/score}
                                                                                                                              #:db{:id 629, :ident :space/length}
                                                                                                                              #:db{:id 630, :ident :space/accreditation-type}
                                                                                                                              #:db{:id 631, :ident :space/languages}
                                                                                                                              #:db{:id 632, :ident :recall-context/difficulty}
                                                                                                                              #:db{:id 633, :ident :recall-context/interval}
                                                                                                                              #:db{:id 634, :ident :recall-context/percent-overdue}
                                                                                                                              #:db{:id 635, :ident :recall-context/due-date}
                                                                                                                              #:db{:id 636, :ident :graph/recall-context}
                                                                                                                              #:db{:id 637, :ident :question/answer-type}],
                                                                                                       :db.alter/attribute   [#:db{:id 64, :ident :institution/name}
                                                                                                                              #:db{:id 65, :ident :institution/dep}
                                                                                                                              #:db{:id 66, :ident :affiliation/uuid}
                                                                                                                              #:db{:id 66, :ident :affiliation/uuid}
                                                                                                                              #:db{:id 66, :ident :affiliation/uuid}
                                                                                                                              #:db{:id 67, :ident :affiliation/institution}
                                                                                                                              #:db{:id 67, :ident :affiliation/institution}
                                                                                                                              #:db{:id 67, :ident :affiliation/institution}
                                                                                                                              #:db{:id 68, :ident :affiliation/dep}
                                                                                                                              #:db{:id 68, :ident :affiliation/dep}
                                                                                                                              #:db{:id 68, :ident :affiliation/dep}
                                                                                                                              #:db{:id 69, :ident :address/uuid}
                                                                                                                              #:db{:id 69, :ident :address/uuid}
                                                                                                                              #:db{:id 70, :ident :address/name}
                                                                                                                              #:db{:id 70, :ident :address/name}
                                                                                                                              #:db{:id 71, :ident :address/street}
                                                                                                                              #:db{:id 71, :ident :address/street}
                                                                                                                              #:db{:id 72, :ident :address/town}
                                                                                                                              #:db{:id 72, :ident :address/town}
                                                                                                                              #:db{:id 73, :ident :address/country}
                                                                                                                              #:db{:id 73, :ident :address/country}
                                                                                                                              #:db{:id 74, :ident :address/postcode-str}
                                                                                                                              #:db{:id 74, :ident :address/postcode-str}
                                                                                                                              #:db{:id 75, :ident :person/uuid}
                                                                                                                              #:db{:id 75, :ident :person/uuid}
                                                                                                                              #:db{:id 78, :ident :person/password}
                                                                                                                              #:db{:id 78, :ident :person/password}
                                                                                                                              #:db{:id 80, :ident :person/first-name}
                                                                                                                              #:db{:id 80, :ident :person/first-name}
                                                                                                                              #:db{:id 81, :ident :person/last-name}
                                                                                                                              #:db{:id 81, :ident :person/last-name}
                                                                                                                              #:db{:id 82, :ident :person/email}
                                                                                                                              #:db{:id 82, :ident :person/email}
                                                                                                                              #:db{:id 92, :ident :password/hash}
                                                                                                                              #:db{:id 92, :ident :password/hash}
                                                                                                                              #:db{:id 93, :ident :coc/uuid}
                                                                                                                              #:db{:id 93, :ident :coc/uuid}
                                                                                                                              #:db{:id 94, :ident :coc/managee}
                                                                                                                              #:db{:id 94, :ident :coc/managee}
                                                                                                                              #:db{:id 95, :ident :coc/role}
                                                                                                                              #:db{:id 95, :ident :coc/role}
                                                                                                                              #:db{:id 96, :ident :coc/license-count}
                                                                                                                              #:db{:id 96, :ident :coc/license-count}
                                                                                                                              #:db{:id 98, :ident :space/title}
                                                                                                                              #:db{:id 98, :ident :space/title}
                                                                                                                              #:db{:id 99, :ident :space/point}
                                                                                                                              #:db{:id 99, :ident :space/point}
                                                                                                                              #:db{:id 104, :ident :pass/uuid}
                                                                                                                              #:db{:id 104, :ident :pass/uuid}
                                                                                                                              #:db{:id 105, :ident :pass/description}
                                                                                                                              #:db{:id 105, :ident :pass/description}
                                                                                                                              #:db{:id 106, :ident :pass/code}
                                                                                                                              #:db{:id 106, :ident :pass/code}
                                                                                                                              #:db{:id 107, :ident :pass/valid-from}
                                                                                                                              #:db{:id 107, :ident :pass/valid-from}
                                                                                                                              #:db{:id 108, :ident :pass/valid-to}
                                                                                                                              #:db{:id 108, :ident :pass/valid-to}
                                                                                                                              #:db{:id 109, :ident :pass/courses}
                                                                                                                              #:db{:id 109, :ident :pass/courses}
                                                                                                                              #:db{:id 111, :ident :graph/double}
                                                                                                                              #:db{:id 111, :ident :graph/double}
                                                                                                                              #:db{:id 112, :ident :graph/bigdec}
                                                                                                                              #:db{:id 112, :ident :graph/bigdec}
                                                                                                                              #:db{:id 113, :ident :graph/matrix-value}
                                                                                                                              #:db{:id 113, :ident :graph/matrix-value}
                                                                                                                              #:db{:id 114, :ident :graph/matrix-shape}
                                                                                                                              #:db{:id 114, :ident :graph/matrix-shape}
                                                                                                                              #:db{:id 115, :ident :graph/distribution-key}
                                                                                                                              #:db{:id 115, :ident :graph/distribution-key}
                                                                                                                              #:db{:id 116, :ident :graph/distribution}
                                                                                                                              #:db{:id 116, :ident :graph/distribution}
                                                                                                                              #:db{:id 117, :ident :graph/distributions}
                                                                                                                              #:db{:id 117, :ident :graph/distributions}
                                                                                                                              #:db{:id 118, :ident :node/uuid}
                                                                                                                              #:db{:id 118, :ident :node/uuid}
                                                                                                                              #:db{:id 120, :ident :node/edge}
                                                                                                                              #:db{:id 120, :ident :node/edge}
                                                                                                                              #:db{:id 121, :ident :node/kind}
                                                                                                                              #:db{:id 121, :ident :node/kind}
                                                                                                                              #:db{:id 122, :ident :edge/mass}
                                                                                                                              #:db{:id 122, :ident :edge/mass}
                                                                                                                              #:db{:id 123, :ident :edge/distributions}
                                                                                                                              #:db{:id 123, :ident :edge/distributions}
                                                                                                                              #:db{:id 124, :ident :edge/node}
                                                                                                                              #:db{:id 124, :ident :edge/node}
                                                                                                                              #:db{:id 124, :ident :edge/node}
                                                                                                                              #:db{:id 125, :ident :edge/kind}
                                                                                                                              #:db{:id 125, :ident :edge/kind}
                                                                                                                              #:db{:id 127, :ident :text/lang}
                                                                                                                              #:db{:id 127, :ident :text/lang}
                                                                                                                              #:db{:id 130, :ident :concept/key}
                                                                                                                              #:db{:id 130, :ident :concept/key}
                                                                                                                              #:db{:id 131, :ident :question/answer}
                                                                                                                              #:db{:id 131, :ident :question/answer}
                                                                                                                              #:db{:id 132, :ident :time/ms}
                                                                                                                              #:db{:id 132, :ident :time/ms}
                                                                                                                              #:db{:id 132, :ident :time/ms}
                                                                                                                              #:db{:id 133, :ident :media/extension}
                                                                                                                              #:db{:id 133, :ident :media/extension}
                                                                                                                              #:db{:id 134, :ident :video/slide}
                                                                                                                              #:db{:id 134, :ident :video/slide}
                                                                                                                              #:db{:id 134, :ident :video/slide}
                                                                                                                              #:db{:id 134, :ident :video/slide}
                                                                                                                              #:db{:id 137, :ident :paper/doi}
                                                                                                                              #:db{:id 137, :ident :paper/doi}
                                                                                                                              #:db{:id 332, :ident :current/A}
                                                                                                                              #:db{:id 332, :ident :current/A}
                                                                                                                              #:db{:id 333, :ident :lang/ab}
                                                                                                                              #:db{:id 333, :ident :lang/ab}
                                                                                                                              #:db{:id 334, :ident :lang/aa}
                                                                                                                              #:db{:id 334, :ident :lang/aa}
                                                                                                                              #:db{:id 335, :ident :lang/af}
                                                                                                                              #:db{:id 335, :ident :lang/af}
                                                                                                                              #:db{:id 336, :ident :lang/ak}
                                                                                                                              #:db{:id 336, :ident :lang/ak}
                                                                                                                              #:db{:id 337, :ident :lang/sq}
                                                                                                                              #:db{:id 337, :ident :lang/sq}
                                                                                                                              #:db{:id 338, :ident :lang/am}
                                                                                                                              #:db{:id 338, :ident :lang/am}
                                                                                                                              #:db{:id 339, :ident :lang/ar}
                                                                                                                              #:db{:id 339, :ident :lang/ar}
                                                                                                                              #:db{:id 340, :ident :lang/an}
                                                                                                                              #:db{:id 340, :ident :lang/an}
                                                                                                                              #:db{:id 341, :ident :lang/hy}
                                                                                                                              #:db{:id 341, :ident :lang/hy}
                                                                                                                              #:db{:id 342, :ident :lang/as}
                                                                                                                              #:db{:id 342, :ident :lang/as}
                                                                                                                              #:db{:id 343, :ident :lang/av}
                                                                                                                              #:db{:id 343, :ident :lang/av}
                                                                                                                              #:db{:id 344, :ident :lang/ae}
                                                                                                                              #:db{:id 344, :ident :lang/ae}
                                                                                                                              #:db{:id 345, :ident :lang/ay}
                                                                                                                              #:db{:id 345, :ident :lang/ay}
                                                                                                                              #:db{:id 346, :ident :lang/az}
                                                                                                                              #:db{:id 346, :ident :lang/az}
                                                                                                                              #:db{:id 347, :ident :lang/bm}
                                                                                                                              #:db{:id 347, :ident :lang/bm}
                                                                                                                              #:db{:id 348, :ident :lang/ba}
                                                                                                                              #:db{:id 348, :ident :lang/ba}
                                                                                                                              #:db{:id 349, :ident :lang/eu}
                                                                                                                              #:db{:id 349, :ident :lang/eu}
                                                                                                                              #:db{:id 350, :ident :lang/be}
                                                                                                                              #:db{:id 350, :ident :lang/be}
                                                                                                                              #:db{:id 351, :ident :lang/bn}
                                                                                                                              #:db{:id 351, :ident :lang/bn}
                                                                                                                              #:db{:id 352, :ident :lang/bh}
                                                                                                                              #:db{:id 352, :ident :lang/bh}
                                                                                                                              #:db{:id 353, :ident :lang/bi}
                                                                                                                              #:db{:id 353, :ident :lang/bi}
                                                                                                                              #:db{:id 354, :ident :lang/bs}
                                                                                                                              #:db{:id 354, :ident :lang/bs}
                                                                                                                              #:db{:id 355, :ident :lang/br}
                                                                                                                              #:db{:id 355, :ident :lang/br}
                                                                                                                              #:db{:id 356, :ident :lang/bg}
                                                                                                                              #:db{:id 356, :ident :lang/bg}
                                                                                                                              #:db{:id 357, :ident :lang/my}
                                                                                                                              #:db{:id 357, :ident :lang/my}
                                                                                                                              #:db{:id 358, :ident :lang/ca}
                                                                                                                              #:db{:id 358, :ident :lang/ca}
                                                                                                                              #:db{:id 359, :ident :lang/ch}
                                                                                                                              #:db{:id 359, :ident :lang/ch}
                                                                                                                              #:db{:id 360, :ident :lang/ce}
                                                                                                                              #:db{:id 360, :ident :lang/ce}
                                                                                                                              #:db{:id 361, :ident :lang/ny}
                                                                                                                              #:db{:id 361, :ident :lang/ny}
                                                                                                                              #:db{:id 362, :ident :lang/zh}
                                                                                                                              #:db{:id 362, :ident :lang/zh}
                                                                                                                              #:db{:id 363, :ident :lang/cv}
                                                                                                                              #:db{:id 363, :ident :lang/cv}
                                                                                                                              #:db{:id 364, :ident :lang/kw}
                                                                                                                              #:db{:id 364, :ident :lang/kw}
                                                                                                                              #:db{:id 365, :ident :lang/co}
                                                                                                                              #:db{:id 365, :ident :lang/co}
                                                                                                                              #:db{:id 366, :ident :lang/cr}
                                                                                                                              #:db{:id 366, :ident :lang/cr}
                                                                                                                              #:db{:id 367, :ident :lang/hr}
                                                                                                                              #:db{:id 367, :ident :lang/hr}
                                                                                                                              #:db{:id 368, :ident :lang/cs}
                                                                                                                              #:db{:id 368, :ident :lang/cs}
                                                                                                                              #:db{:id 369, :ident :lang/da}
                                                                                                                              #:db{:id 369, :ident :lang/da}
                                                                                                                              #:db{:id 370, :ident :lang/dv}
                                                                                                                              #:db{:id 370, :ident :lang/dv}
                                                                                                                              #:db{:id 371, :ident :lang/nl}
                                                                                                                              #:db{:id 371, :ident :lang/nl}
                                                                                                                              #:db{:id 372, :ident :lang/dz}
                                                                                                                              #:db{:id 372, :ident :lang/dz}
                                                                                                                              #:db{:id 373, :ident :lang/en}
                                                                                                                              #:db{:id 373, :ident :lang/en}
                                                                                                                              #:db{:id 374, :ident :lang/eo}
                                                                                                                              #:db{:id 374, :ident :lang/eo}
                                                                                                                              #:db{:id 375, :ident :lang/et}
                                                                                                                              #:db{:id 375, :ident :lang/et}
                                                                                                                              #:db{:id 376, :ident :lang/ee}
                                                                                                                              #:db{:id 376, :ident :lang/ee}
                                                                                                                              #:db{:id 377, :ident :lang/fo}
                                                                                                                              #:db{:id 377, :ident :lang/fo}
                                                                                                                              #:db{:id 378, :ident :lang/fj}
                                                                                                                              #:db{:id 378, :ident :lang/fj}
                                                                                                                              #:db{:id 379, :ident :lang/fl}
                                                                                                                              #:db{:id 379, :ident :lang/fl}
                                                                                                                              #:db{:id 380, :ident :lang/fi}
                                                                                                                              #:db{:id 380, :ident :lang/fi}
                                                                                                                              #:db{:id 381, :ident :lang/fr}
                                                                                                                              #:db{:id 381, :ident :lang/fr}
                                                                                                                              #:db{:id 382, :ident :lang/ff}
                                                                                                                              #:db{:id 382, :ident :lang/ff}
                                                                                                                              #:db{:id 383, :ident :lang/gl}
                                                                                                                              #:db{:id 383, :ident :lang/gl}
                                                                                                                              #:db{:id 384, :ident :lang/ka}
                                                                                                                              #:db{:id 384, :ident :lang/ka}
                                                                                                                              #:db{:id 385, :ident :lang/de}
                                                                                                                              #:db{:id 385, :ident :lang/de}
                                                                                                                              #:db{:id 386, :ident :lang/el}
                                                                                                                              #:db{:id 386, :ident :lang/el}
                                                                                                                              #:db{:id 387, :ident :lang/gn}
                                                                                                                              #:db{:id 387, :ident :lang/gn}
                                                                                                                              #:db{:id 388, :ident :lang/gu}
                                                                                                                              #:db{:id 388, :ident :lang/gu}
                                                                                                                              #:db{:id 389, :ident :lang/ht}
                                                                                                                              #:db{:id 389, :ident :lang/ht}
                                                                                                                              #:db{:id 390, :ident :lang/ha}
                                                                                                                              #:db{:id 390, :ident :lang/ha}
                                                                                                                              #:db{:id 391, :ident :lang/he}
                                                                                                                              #:db{:id 391, :ident :lang/he}
                                                                                                                              #:db{:id 392, :ident :lang/hz}
                                                                                                                              #:db{:id 392, :ident :lang/hz}
                                                                                                                              #:db{:id 393, :ident :lang/hi}
                                                                                                                              #:db{:id 393, :ident :lang/hi}
                                                                                                                              #:db{:id 394, :ident :lang/ho}
                                                                                                                              #:db{:id 394, :ident :lang/ho}
                                                                                                                              #:db{:id 395, :ident :lang/hu}
                                                                                                                              #:db{:id 395, :ident :lang/hu}
                                                                                                                              #:db{:id 396, :ident :lang/ia}
                                                                                                                              #:db{:id 396, :ident :lang/ia}
                                                                                                                              #:db{:id 397, :ident :lang/id}
                                                                                                                              #:db{:id 397, :ident :lang/id}
                                                                                                                              #:db{:id 398, :ident :lang/ie}
                                                                                                                              #:db{:id 398, :ident :lang/ie}
                                                                                                                              #:db{:id 399, :ident :lang/ga}
                                                                                                                              #:db{:id 399, :ident :lang/ga}
                                                                                                                              #:db{:id 400, :ident :lang/ig}
                                                                                                                              #:db{:id 400, :ident :lang/ig}
                                                                                                                              #:db{:id 401, :ident :lang/ik}
                                                                                                                              #:db{:id 401, :ident :lang/ik}
                                                                                                                              #:db{:id 402, :ident :lang/io}
                                                                                                                              #:db{:id 402, :ident :lang/io}
                                                                                                                              #:db{:id 403, :ident :lang/is}
                                                                                                                              #:db{:id 403, :ident :lang/is}
                                                                                                                              #:db{:id 404, :ident :lang/it}
                                                                                                                              #:db{:id 404, :ident :lang/it}
                                                                                                                              #:db{:id 405, :ident :lang/iu}
                                                                                                                              #:db{:id 405, :ident :lang/iu}
                                                                                                                              #:db{:id 406, :ident :lang/ja}
                                                                                                                              #:db{:id 406, :ident :lang/ja}
                                                                                                                              #:db{:id 407, :ident :lang/jv}
                                                                                                                              #:db{:id 407, :ident :lang/jv}
                                                                                                                              #:db{:id 408, :ident :lang/kl}
                                                                                                                              #:db{:id 408, :ident :lang/kl}
                                                                                                                              #:db{:id 409, :ident :lang/kn}
                                                                                                                              #:db{:id 409, :ident :lang/kn}
                                                                                                                              #:db{:id 410, :ident :lang/kr}
                                                                                                                              #:db{:id 410, :ident :lang/kr}
                                                                                                                              #:db{:id 411, :ident :lang/ks}
                                                                                                                              #:db{:id 411, :ident :lang/ks}
                                                                                                                              #:db{:id 412, :ident :lang/kk}
                                                                                                                              #:db{:id 412, :ident :lang/kk}
                                                                                                                              #:db{:id 413, :ident :lang/km}
                                                                                                                              #:db{:id 413, :ident :lang/km}
                                                                                                                              #:db{:id 414, :ident :lang/ki}
                                                                                                                              #:db{:id 414, :ident :lang/ki}
                                                                                                                              #:db{:id 415, :ident :lang/rw}
                                                                                                                              #:db{:id 415, :ident :lang/rw}
                                                                                                                              #:db{:id 416, :ident :lang/ky}
                                                                                                                              #:db{:id 416, :ident :lang/ky}
                                                                                                                              #:db{:id 417, :ident :lang/kv}
                                                                                                                              #:db{:id 417, :ident :lang/kv}
                                                                                                                              #:db{:id 418, :ident :lang/kg}
                                                                                                                              #:db{:id 418, :ident :lang/kg}
                                                                                                                              #:db{:id 419, :ident :lang/ko}
                                                                                                                              #:db{:id 419, :ident :lang/ko}
                                                                                                                              #:db{:id 420, :ident :lang/ku}
                                                                                                                              #:db{:id 420, :ident :lang/ku}
                                                                                                                              #:db{:id 421, :ident :lang/kj}
                                                                                                                              #:db{:id 421, :ident :lang/kj}
                                                                                                                              #:db{:id 422, :ident :lang/la}
                                                                                                                              #:db{:id 422, :ident :lang/la}
                                                                                                                              #:db{:id 423, :ident :lang/lb}
                                                                                                                              #:db{:id 423, :ident :lang/lb}
                                                                                                                              #:db{:id 424, :ident :lang/lg}
                                                                                                                              #:db{:id 424, :ident :lang/lg}
                                                                                                                              #:db{:id 425, :ident :lang/li}
                                                                                                                              #:db{:id 425, :ident :lang/li}
                                                                                                                              #:db{:id 426, :ident :lang/ln}
                                                                                                                              #:db{:id 426, :ident :lang/ln}
                                                                                                                              #:db{:id 427, :ident :lang/lo}
                                                                                                                              #:db{:id 427, :ident :lang/lo}
                                                                                                                              #:db{:id 428, :ident :lang/lt}
                                                                                                                              #:db{:id 428, :ident :lang/lt}
                                                                                                                              #:db{:id 429, :ident :lang/lu}
                                                                                                                              #:db{:id 429, :ident :lang/lu}
                                                                                                                              #:db{:id 430, :ident :lang/lv}
                                                                                                                              #:db{:id 430, :ident :lang/lv}
                                                                                                                              #:db{:id 431, :ident :lang/gv}
                                                                                                                              #:db{:id 431, :ident :lang/gv}
                                                                                                                              #:db{:id 432, :ident :lang/mk}
                                                                                                                              #:db{:id 432, :ident :lang/mk}
                                                                                                                              #:db{:id 433, :ident :lang/mg}
                                                                                                                              #:db{:id 433, :ident :lang/mg}
                                                                                                                              #:db{:id 434, :ident :lang/ms}
                                                                                                                              #:db{:id 434, :ident :lang/ms}
                                                                                                                              #:db{:id 435, :ident :lang/ml}
                                                                                                                              #:db{:id 435, :ident :lang/ml}
                                                                                                                              #:db{:id 436, :ident :lang/mt}
                                                                                                                              #:db{:id 436, :ident :lang/mt}
                                                                                                                              #:db{:id 437, :ident :lang/mi}
                                                                                                                              #:db{:id 437, :ident :lang/mi}
                                                                                                                              #:db{:id 438, :ident :lang/mr}
                                                                                                                              #:db{:id 438, :ident :lang/mr}
                                                                                                                              #:db{:id 439, :ident :lang/mh}
                                                                                                                              #:db{:id 439, :ident :lang/mh}
                                                                                                                              #:db{:id 440, :ident :lang/mn}
                                                                                                                              #:db{:id 440, :ident :lang/mn}
                                                                                                                              #:db{:id 441, :ident :lang/na}
                                                                                                                              #:db{:id 441, :ident :lang/na}
                                                                                                                              #:db{:id 442, :ident :lang/nv}
                                                                                                                              #:db{:id 442, :ident :lang/nv}
                                                                                                                              #:db{:id 443, :ident :lang/nd}
                                                                                                                              #:db{:id 443, :ident :lang/nd}
                                                                                                                              #:db{:id 444, :ident :lang/ne}
                                                                                                                              #:db{:id 444, :ident :lang/ne}
                                                                                                                              #:db{:id 445, :ident :lang/ng}
                                                                                                                              #:db{:id 445, :ident :lang/ng}
                                                                                                                              #:db{:id 446, :ident :lang/nb}
                                                                                                                              #:db{:id 446, :ident :lang/nb}
                                                                                                                              #:db{:id 447, :ident :lang/nn}
                                                                                                                              #:db{:id 447, :ident :lang/nn}
                                                                                                                              #:db{:id 448, :ident :lang/no}
                                                                                                                              #:db{:id 448, :ident :lang/no}
                                                                                                                              #:db{:id 449, :ident :lang/ii}
                                                                                                                              #:db{:id 449, :ident :lang/ii}
                                                                                                                              #:db{:id 450, :ident :lang/nr}
                                                                                                                              #:db{:id 450, :ident :lang/nr}
                                                                                                                              #:db{:id 451, :ident :lang/oc}
                                                                                                                              #:db{:id 451, :ident :lang/oc}
                                                                                                                              #:db{:id 452, :ident :lang/oj}
                                                                                                                              #:db{:id 452, :ident :lang/oj}
                                                                                                                              #:db{:id 453, :ident :lang/cu}
                                                                                                                              #:db{:id 453, :ident :lang/cu}
                                                                                                                              #:db{:id 454, :ident :lang/om}
                                                                                                                              #:db{:id 454, :ident :lang/om}
                                                                                                                              #:db{:id 455, :ident :lang/or}
                                                                                                                              #:db{:id 455, :ident :lang/or}
                                                                                                                              #:db{:id 456, :ident :lang/os}
                                                                                                                              #:db{:id 456, :ident :lang/os}
                                                                                                                              #:db{:id 457, :ident :lang/pa}
                                                                                                                              #:db{:id 457, :ident :lang/pa}
                                                                                                                              #:db{:id 458, :ident :lang/pi}
                                                                                                                              #:db{:id 458, :ident :lang/pi}
                                                                                                                              #:db{:id 459, :ident :lang/fa}
                                                                                                                              #:db{:id 459, :ident :lang/fa}
                                                                                                                              #:db{:id 460, :ident :lang/pl}
                                                                                                                              #:db{:id 460, :ident :lang/pl}
                                                                                                                              #:db{:id 461, :ident :lang/ps}
                                                                                                                              #:db{:id 461, :ident :lang/ps}
                                                                                                                              #:db{:id 462, :ident :lang/pt}
                                                                                                                              #:db{:id 462, :ident :lang/pt}
                                                                                                                              #:db{:id 463, :ident :lang/qu}
                                                                                                                              #:db{:id 463, :ident :lang/qu}
                                                                                                                              #:db{:id 464, :ident :lang/rm}
                                                                                                                              #:db{:id 464, :ident :lang/rm}
                                                                                                                              #:db{:id 465, :ident :lang/rn}
                                                                                                                              #:db{:id 465, :ident :lang/rn}
                                                                                                                              #:db{:id 466, :ident :lang/ro}
                                                                                                                              #:db{:id 466, :ident :lang/ro}
                                                                                                                              #:db{:id 467, :ident :lang/ru}
                                                                                                                              #:db{:id 467, :ident :lang/ru}
                                                                                                                              #:db{:id 468, :ident :lang/sa}
                                                                                                                              #:db{:id 468, :ident :lang/sa}
                                                                                                                              #:db{:id 469, :ident :lang/sc}
                                                                                                                              #:db{:id 469, :ident :lang/sc}
                                                                                                                              #:db{:id 470, :ident :lang/sd}
                                                                                                                              #:db{:id 470, :ident :lang/sd}
                                                                                                                              #:db{:id 471, :ident :lang/se}
                                                                                                                              #:db{:id 471, :ident :lang/se}
                                                                                                                              #:db{:id 472, :ident :lang/sm}
                                                                                                                              #:db{:id 472, :ident :lang/sm}
                                                                                                                              #:db{:id 473, :ident :lang/sg}
                                                                                                                              #:db{:id 473, :ident :lang/sg}
                                                                                                                              #:db{:id 474, :ident :lang/sr}
                                                                                                                              #:db{:id 474, :ident :lang/sr}
                                                                                                                              #:db{:id 475, :ident :lang/gd}
                                                                                                                              #:db{:id 475, :ident :lang/gd}
                                                                                                                              #:db{:id 476, :ident :lang/sn}
                                                                                                                              #:db{:id 476, :ident :lang/sn}
                                                                                                                              #:db{:id 477, :ident :lang/si}
                                                                                                                              #:db{:id 477, :ident :lang/si}
                                                                                                                              #:db{:id 478, :ident :lang/sk}
                                                                                                                              #:db{:id 478, :ident :lang/sk}
                                                                                                                              #:db{:id 479, :ident :lang/sl}
                                                                                                                              #:db{:id 479, :ident :lang/sl}
                                                                                                                              #:db{:id 480, :ident :lang/so}
                                                                                                                              #:db{:id 480, :ident :lang/so}
                                                                                                                              #:db{:id 481, :ident :lang/st}
                                                                                                                              #:db{:id 481, :ident :lang/st}
                                                                                                                              #:db{:id 482, :ident :lang/es}
                                                                                                                              #:db{:id 482, :ident :lang/es}
                                                                                                                              #:db{:id 483, :ident :lang/su}
                                                                                                                              #:db{:id 483, :ident :lang/su}
                                                                                                                              #:db{:id 484, :ident :lang/sw}
                                                                                                                              #:db{:id 484, :ident :lang/sw}
                                                                                                                              #:db{:id 485, :ident :lang/ss}
                                                                                                                              #:db{:id 485, :ident :lang/ss}
                                                                                                                              #:db{:id 486, :ident :lang/sv}
                                                                                                                              #:db{:id 486, :ident :lang/sv}
                                                                                                                              #:db{:id 487, :ident :lang/ta}
                                                                                                                              #:db{:id 487, :ident :lang/ta}
                                                                                                                              #:db{:id 488, :ident :lang/te}
                                                                                                                              #:db{:id 488, :ident :lang/te}
                                                                                                                              #:db{:id 489, :ident :lang/tg}
                                                                                                                              #:db{:id 489, :ident :lang/tg}
                                                                                                                              #:db{:id 490, :ident :lang/th}
                                                                                                                              #:db{:id 490, :ident :lang/th}
                                                                                                                              #:db{:id 491, :ident :lang/ti}
                                                                                                                              #:db{:id 491, :ident :lang/ti}
                                                                                                                              #:db{:id 492, :ident :lang/bo}
                                                                                                                              #:db{:id 492, :ident :lang/bo}
                                                                                                                              #:db{:id 493, :ident :lang/tk}
                                                                                                                              #:db{:id 493, :ident :lang/tk}
                                                                                                                              #:db{:id 494, :ident :lang/tl}
                                                                                                                              #:db{:id 494, :ident :lang/tl}
                                                                                                                              #:db{:id 495, :ident :lang/tn}
                                                                                                                              #:db{:id 495, :ident :lang/tn}
                                                                                                                              #:db{:id 496, :ident :lang/to}
                                                                                                                              #:db{:id 496, :ident :lang/to}
                                                                                                                              #:db{:id 497, :ident :lang/tr}
                                                                                                                              #:db{:id 497, :ident :lang/tr}
                                                                                                                              #:db{:id 498, :ident :lang/ts}
                                                                                                                              #:db{:id 498, :ident :lang/ts}
                                                                                                                              #:db{:id 499, :ident :lang/tt}
                                                                                                                              #:db{:id 499, :ident :lang/tt}
                                                                                                                              #:db{:id 500, :ident :lang/tw}
                                                                                                                              #:db{:id 500, :ident :lang/tw}
                                                                                                                              #:db{:id 501, :ident :lang/ty}
                                                                                                                              #:db{:id 501, :ident :lang/ty}
                                                                                                                              #:db{:id 502, :ident :lang/ug}
                                                                                                                              #:db{:id 502, :ident :lang/ug}
                                                                                                                              #:db{:id 503, :ident :lang/uk}
                                                                                                                              #:db{:id 503, :ident :lang/uk}
                                                                                                                              #:db{:id 504, :ident :lang/ur}
                                                                                                                              #:db{:id 504, :ident :lang/ur}
                                                                                                                              #:db{:id 505, :ident :lang/uz}
                                                                                                                              #:db{:id 505, :ident :lang/uz}
                                                                                                                              #:db{:id 506, :ident :lang/ve}
                                                                                                                              #:db{:id 506, :ident :lang/ve}
                                                                                                                              #:db{:id 507, :ident :lang/vi}
                                                                                                                              #:db{:id 507, :ident :lang/vi}
                                                                                                                              #:db{:id 508, :ident :lang/vo}
                                                                                                                              #:db{:id 508, :ident :lang/vo}
                                                                                                                              #:db{:id 509, :ident :lang/wa}
                                                                                                                              #:db{:id 509, :ident :lang/wa}
                                                                                                                              #:db{:id 510, :ident :lang/cy}
                                                                                                                              #:db{:id 510, :ident :lang/cy}
                                                                                                                              #:db{:id 511, :ident :lang/wo}
                                                                                                                              #:db{:id 511, :ident :lang/wo}
                                                                                                                              #:db{:id 512, :ident :lang/fy}
                                                                                                                              #:db{:id 512, :ident :lang/fy}
                                                                                                                              #:db{:id 513, :ident :lang/xh}
                                                                                                                              #:db{:id 513, :ident :lang/xh}
                                                                                                                              #:db{:id 514, :ident :lang/yi}
                                                                                                                              #:db{:id 514, :ident :lang/yi}
                                                                                                                              #:db{:id 515, :ident :lang/yo}
                                                                                                                              #:db{:id 515, :ident :lang/yo}
                                                                                                                              #:db{:id 516, :ident :lang/za}
                                                                                                                              #:db{:id 516, :ident :lang/za}
                                                                                                                              #:db{:id 517, :ident :lang/zu}
                                                                                                                              #:db{:id 517, :ident :lang/zu}
                                                                                                                              #:db{:id 518, :ident :product/id}
                                                                                                                              #:db{:id 518, :ident :product/id}
                                                                                                                              #:db{:id 520, :ident :product/title}
                                                                                                                              #:db{:id 520, :ident :product/title}
                                                                                                                              #:db{:id 524, :ident :edge/path}
                                                                                                                              #:db{:id 524, :ident :edge/path}
                                                                                                                              #:db{:id 525, :ident :product/manufacturer}
                                                                                                                              #:db{:id 525, :ident :product/manufacturer}
                                                                                                                              #:db{:id 526, :ident :text/tran}
                                                                                                                              #:db{:id 526, :ident :text/tran}
                                                                                                                              #:db{:id 527, :ident :brand/uuid}
                                                                                                                              #:db{:id 527, :ident :brand/uuid}
                                                                                                                              #:db{:id 528, :ident :brand/logo-url}
                                                                                                                              #:db{:id 528, :ident :brand/logo-url}
                                                                                                                              #:db{:id 528, :ident :brand/logo-url}
                                                                                                                              #:db{:id 529, :ident :brand/color-major}
                                                                                                                              #:db{:id 529, :ident :brand/color-major}
                                                                                                                              #:db{:id 529, :ident :brand/color-major}
                                                                                                                              #:db{:id 530, :ident :brand/color-minor}
                                                                                                                              #:db{:id 530, :ident :brand/color-minor}
                                                                                                                              #:db{:id 530, :ident :brand/color-minor}
                                                                                                                              #:db{:id 532, :ident :org/name}
                                                                                                                              #:db{:id 532, :ident :org/name}
                                                                                                                              #:db{:id 533, :ident :org/short-name}
                                                                                                                              #:db{:id 533, :ident :org/short-name}
                                                                                                                              #:db{:id 534, :ident :org/brand}
                                                                                                                              #:db{:id 534, :ident :org/brand}
                                                                                                                              #:db{:id 535, :ident :obr/uuid}
                                                                                                                              #:db{:id 535, :ident :obr/uuid}
                                                                                                                              #:db{:id 537, :ident :perm/source}
                                                                                                                              #:db{:id 537, :ident :perm/source}
                                                                                                                              #:db{:id 538, :ident :perm/target}
                                                                                                                              #:db{:id 538, :ident :perm/target}
                                                                                                                              #:db{:id 539, :ident :perm/level}
                                                                                                                              #:db{:id 539, :ident :perm/level}
                                                                                                                              #:db{:id 540, :ident :view/visibility}
                                                                                                                              #:db{:id 540, :ident :view/visibility}
                                                                                                                              #:db{:id 541, :ident :person/tokens}
                                                                                                                              #:db{:id 541, :ident :person/tokens}
                                                                                                                              #:db{:id 542, :ident :token/value}
                                                                                                                              #:db{:id 542, :ident :token/value}
                                                                                                                              #:db{:id 543, :ident :token/kind}
                                                                                                                              #:db{:id 543, :ident :token/kind}
                                                                                                                              #:db{:id 544, :ident :token/exp}
                                                                                                                              #:db{:id 544, :ident :token/exp}
                                                                                                                              #:db{:id 545, :ident :person/active?}
                                                                                                                              #:db{:id 545, :ident :person/active?}
                                                                                                                              #:db{:id 546, :ident :org/urls}
                                                                                                                              #:db{:id 546, :ident :org/urls}
                                                                                                                              #:db{:id 547, :ident :url/domain}
                                                                                                                              #:db{:id 547, :ident :url/domain}
                                                                                                                              #:db{:id 548, :ident :person/role}
                                                                                                                              #:db{:id 548, :ident :person/role}
                                                                                                                              #:db{:id 551, :ident :obr/permissions}
                                                                                                                              #:db{:id 551, :ident :obr/permissions}
                                                                                                                              #:db{:id 552, :ident :person/orgs}
                                                                                                                              #:db{:id 552, :ident :person/orgs}
                                                                                                                              #:db{:id 552, :ident :person/orgs}
                                                                                                                              #:db{:id 553, :ident :brand/dark?}
                                                                                                                              #:db{:id 553, :ident :brand/dark?}
                                                                                                                              #:db{:id 555, :ident :text/title}
                                                                                                                              #:db{:id 555, :ident :text/title}
                                                                                                                              #:db{:id 555, :ident :text/title}
                                                                                                                              #:db{:id 556, :ident :text/summary}
                                                                                                                              #:db{:id 556, :ident :text/summary}
                                                                                                                              #:db{:id 556, :ident :text/summary}
                                                                                                                              #:db{:id 557, :ident :text/description}
                                                                                                                              #:db{:id 557, :ident :text/description}
                                                                                                                              #:db{:id 557, :ident :text/description}
                                                                                                                              #:db{:id 558, :ident :brand/hero-images}
                                                                                                                              #:db{:id 558, :ident :brand/hero-images}
                                                                                                                              #:db{:id 559, :ident :text/url}
                                                                                                                              #:db{:id 559, :ident :text/url}
                                                                                                                              #:db{:id 560, :ident :nav/mode}
                                                                                                                              #:db{:id 560, :ident :nav/mode}
                                                                                                                              #:db{:id 561, :ident :list/index}
                                                                                                                              #:db{:id 561, :ident :list/index}
                                                                                                                              #:db{:id 562, :ident :edge/context}
                                                                                                                              #:db{:id 562, :ident :edge/context}
                                                                                                                              #:db{:id 566, :ident :response/expected-answer}
                                                                                                                              #:db{:id 566, :ident :response/expected-answer}
                                                                                                                              #:db{:id 567, :ident :eula/md5}
                                                                                                                              #:db{:id 567, :ident :eula/md5}
                                                                                                                              #:db{:id 568, :ident :edge/ref}
                                                                                                                              #:db{:id 568, :ident :edge/ref}
                                                                                                                              #:db{:id 569, :ident :node/ext-id}
                                                                                                                              #:db{:id 569, :ident :node/ext-id}
                                                                                                                              #:db{:id 570, :ident :question/explanation}
                                                                                                                              #:db{:id 570, :ident :question/explanation}
                                                                                                                              #:db{:id 571, :ident :document/format}
                                                                                                                              #:db{:id 571, :ident :document/format}
                                                                                                                              #:db{:id 572, :ident :space/intro-summary}
                                                                                                                              #:db{:id 572, :ident :space/intro-summary}
                                                                                                                              #:db{:id 573, :ident :space/intro-video}
                                                                                                                              #:db{:id 573, :ident :space/intro-video}
                                                                                                                              #:db{:id 574, :ident :node/slides}
                                                                                                                              #:db{:id 574, :ident :node/slides}
                                                                                                                              #:db{:id 575, :ident :time.start/ms}
                                                                                                                              #:db{:id 575, :ident :time.start/ms}
                                                                                                                              #:db{:id 576, :ident :time.end/ms}
                                                                                                                              #:db{:id 576, :ident :time.end/ms}
                                                                                                                              #:db{:id 577, :ident :org/allow-signup}
                                                                                                                              #:db{:id 577, :ident :org/allow-signup}
                                                                                                                              #:db{:id 578, :ident :text/markup}
                                                                                                                              #:db{:id 578, :ident :text/markup}
                                                                                                                              #:db{:id 579, :ident :learn-map/obsolete-difficulty}
                                                                                                                              #:db{:id 580, :ident :learn-map/interval}
                                                                                                                              #:db{:id 580, :ident :learn-map/interval}
                                                                                                                              #:db{:id 581, :ident :learn-map/percent-overdue}
                                                                                                                              #:db{:id 581, :ident :learn-map/percent-overdue}
                                                                                                                              #:db{:id 582, :ident :learn-map/due-date}
                                                                                                                              #:db{:id 582, :ident :learn-map/due-date}
                                                                                                                              #:db{:id 583, :ident :graph/learn-map}
                                                                                                                              #:db{:id 583, :ident :graph/learn-map}
                                                                                                                              #:db{:id 585, :ident :certificate/title}
                                                                                                                              #:db{:id 585, :ident :certificate/title}
                                                                                                                              #:db{:id 586, :ident :certificate/course}
                                                                                                                              #:db{:id 586, :ident :certificate/course}
                                                                                                                              #:db{:id 587, :ident :certificate/subject}
                                                                                                                              #:db{:id 587, :ident :certificate/subject}
                                                                                                                              #:db{:id 588, :ident :certificate/issued-on}
                                                                                                                              #:db{:id 588, :ident :certificate/issued-on}
                                                                                                                              #:db{:id 592, :ident :certificate/expires-on}
                                                                                                                              #:db{:id 592, :ident :certificate/expires-on}
                                                                                                                              #:db{:id 593, :ident :feedback/reason}
                                                                                                                              #:db{:id 593, :ident :feedback/reason}
                                                                                                                              #:db{:id 594, :ident :feedback/person-question-edge}
                                                                                                                              #:db{:id 594, :ident :feedback/person-question-edge}
                                                                                                                              #:db{:id 595, :ident :feedback/target}
                                                                                                                              #:db{:id 595, :ident :feedback/target}
                                                                                                                              #:db{:id 596, :ident :person/avatar}
                                                                                                                              #:db{:id 596, :ident :person/avatar}
                                                                                                                              #:db{:id 597, :ident :person/is-memorizing}
                                                                                                                              #:db{:id 597, :ident :person/is-memorizing}
                                                                                                                              #:db{:id 598, :ident :person/is-mastering}
                                                                                                                              #:db{:id 598, :ident :person/is-mastering}
                                                                                                                              #:db{:id 599, :ident :person/classroom-timer}
                                                                                                                              #:db{:id 599, :ident :person/classroom-timer}
                                                                                                                              #:db{:id 600, :ident :text/learning-outcomes}
                                                                                                                              #:db{:id 600, :ident :text/learning-outcomes}
                                                                                                                              #:db{:id 601, :ident :text/info}
                                                                                                                              #:db{:id 601, :ident :text/info}
                                                                                                                              #:db{:id 603, :ident :certificate/completion}
                                                                                                                              #:db{:id 603, :ident :certificate/completion}
                                                                                                                              #:db{:id 604, :ident :module.exploration/unlocked?}
                                                                                                                              #:db{:id 604, :ident :module.exploration/unlocked?}
                                                                                                                              #:db{:id 605, :ident :comment-thread/id}
                                                                                                                              #:db{:id 606, :ident :comment-thread/comments}
                                                                                                                              #:db{:id 607, :ident :comment/id}
                                                                                                                              #:db{:id 608, :ident :comment/user}
                                                                                                                              #:db{:id 609, :ident :comment/content}
                                                                                                                              #:db{:id 610, :ident :comment/created-at}
                                                                                                                              #:db{:id 611, :ident :node/comment-threads}
                                                                                                                              #:db{:id 612, :ident :draft/answers}
                                                                                                                              #:db{:id 613, :ident :draft-answer/mass}
                                                                                                                              #:db{:id 614, :ident :draft/answer}
                                                                                                                              #:db{:id 615, :ident :draft/status}
                                                                                                                              #:db{:id 616, :ident :draft/published-by}
                                                                                                                              #:db{:id 617, :ident :question/flashcard?}
                                                                                                                              #:db{:id 618, :ident :question/documents}
                                                                                                                              #:db{:id 623, :ident :brand/img-url}],
                                                                                                       :db/doc               "Name of the system partition. The system partition includes the core of datomic, as well as user schemas: type definitions, attribute definitions, partition definitions, and data function definitions."},
                                              :lang/lv                                                #:db{:id          430,
                                                                                                           :ident       :lang/lv,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Latvian"},
                                              :lang/ie                                                #:db{:id          398,
                                                                                                           :ident       :lang/ie,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Interlingue"},
                                              :graph/matrix-value                                     #:db{:id          113,
                                                                                                           :ident       :graph/matrix-value,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A matrix-value as a vector of refs to graph/doubles]"},
                                              :graph/bigdec                                           #:db{:id          112,
                                                                                                           :ident       :graph/bigdec,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A bigdec value"},
                                              :deprecated/uz                                          #:db{:id          311,
                                                                                                           :ident       :deprecated/uz,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Uzbek"},
                                              :deprecated/hz                                          #:db{:id          198,
                                                                                                           :ident       :deprecated/hz,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Herero"},
                                              :lang/nv                                                #:db{:id          442,
                                                                                                           :ident       :lang/nv,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Navajo"},
                                              :lang/eo                                                #:db{:id          374,
                                                                                                           :ident       :lang/eo,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Esperanto"},
                                              :lang/sc                                                #:db{:id          469,
                                                                                                           :ident       :lang/sc,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sardinian"},
                                              :deprecated/tt                                          #:db{:id          305,
                                                                                                           :ident       :deprecated/tt,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tatar"},
                                              :lang/st                                                #:db{:id          481,
                                                                                                           :ident       :lang/st,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Southern Sotho"},
                                              :lang/sd                                                #:db{:id          470,
                                                                                                           :ident       :lang/sd,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sindhi"},
                                              :certificate/title                                      #:db{:id          585,
                                                                                                           :ident       :certificate/title,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "The title of the certificate, e.g. the title of the space it is linked to when it was created."},
                                              :text/document                                          #:db{:id 15876947909833011, :ident :text/document},
                                              :deprecated/ku                                          #:db{:id          226,
                                                                                                           :ident       :deprecated/ku,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kurdish"},
                                              :draft/final-ratings                                    #:db{:id          624,
                                                                                                           :ident       :draft/final-ratings,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :noHistory   true,
                                                                                                           :doc         "The final ratings of the draft, can only be authored by managers."},
                                              :lang/uk                                                #:db{:id          503,
                                                                                                           :ident       :lang/uk,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ukrainian"},
                                              :feedback.reason/distracted                             #:db{:id 47454921859718059, :ident :feedback.reason/distracted},
                                              :lang/zu                                                #:db{:id          517,
                                                                                                           :ident       :lang/zu,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Zulu"},
                                              :deprecated/ta                                          #:db{:id          293,
                                                                                                           :ident       :deprecated/ta,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tamil"},
                                              :org/urls                                               #:db{:id          546,
                                                                                                           :ident       :org/urls,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "urls owned by this organisation"},
                                              :person/manages                                         #:db{:id          91,
                                                                                                           :ident       :person/manages,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :noHistory   true,
                                                                                                           :doc         "A collection of people managed by this person"},
                                              :node/edge                                              #:db{:id          120,
                                                                                                           :ident       :node/edge,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A node's edges (this node is all those edges' src)"},
                                              :certificate/expires-on                                 #:db{:id          592,
                                                                                                           :ident       :certificate/expires-on,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent false,
                                                                                                           :doc         "The date and time when the certificate expires."},
                                              :deprecated/oj                                          #:db{:id          258,
                                                                                                           :ident       :deprecated/oj,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ojibwa"},
                                              :node/ext-provider                                      #:db{:id          619,
                                                                                                           :ident       :node/ext-provider,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "the external provider of a node's content, e.g. youtube"},
                                              :feedback/person-question-edge                          #:db{:id          594,
                                                                                                           :ident       :feedback/person-question-edge,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent false,
                                                                                                           :doc         "A reference to the edge that represents a specific person's answer to a question."},
                                              :text/info                                              #:db{:id          601,
                                                                                                           :ident       :text/info,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "The description of info about entity"},
                                              :lang/co                                                #:db{:id          365,
                                                                                                           :ident       :lang/co,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Corsican"},
                                              :comment/content                                        #:db{:id          609,
                                                                                                           :ident       :comment/content,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Comment's body."},
                                              :deprecated/ar                                          #:db{:id          145,
                                                                                                           :ident       :deprecated/ar,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Arabic"},
                                              :deprecated/th                                          #:db{:id          296,
                                                                                                           :ident       :deprecated/th,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Thai"},
                                              :deprecated/de                                          #:db{:id          191,
                                                                                                           :ident       :deprecated/de,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "German"},
                                              :deprecated/xh                                          #:db{:id          319,
                                                                                                           :ident       :deprecated/xh,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Xhosa"},
                                              :address/town                                           #:db{:id          72,
                                                                                                           :ident       :address/town,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Address town"},
                                              :db.bootstrap/part                                      #:db{:id 53, :ident :db.bootstrap/part},
                                              :deprecated/ik                                          #:db{:id          207,
                                                                                                           :ident       :deprecated/ik,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Inupiaq"},
                                              :deprecated/pa                                          #:db{:id          263,
                                                                                                           :ident       :deprecated/pa,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Panjabi"},
                                              :lang/gl                                                #:db{:id          383,
                                                                                                           :ident       :lang/gl,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Galician"},
                                              :lang/yo                                                #:db{:id          515,
                                                                                                           :ident       :lang/yo,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Yoruba"},
                                              :deprecated/lb                                          #:db{:id          229,
                                                                                                           :ident       :deprecated/lb,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Luxembourgish"},
                                              :lang/dz                                                #:db{:id          372,
                                                                                                           :ident       :lang/dz,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Dzongkha"},
                                              :lang/mg                                                #:db{:id          433,
                                                                                                           :ident       :lang/mg,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Malagasy"},
                                              :deprecated/mn                                          #:db{:id          246,
                                                                                                           :ident       :deprecated/mn,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Mongolian"},
                                              :db/valueType                                           #:db{:id          40,
                                                                                                           :ident       :db/valueType,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Property of an attribute that specifies the attribute's value type. Built-in value types include, :db.type/keyword, :db.type/string, :db.type/ref, :db.type/instant, :db.type/long, :db.type/bigdec, :db.type/boolean, :db.type/float, :db.type/uuid, :db.type/double, :db.type/bigint,  :db.type/uri."},
                                              :db.type/string                                         {:db/id 23, :db/ident :db.type/string, :fressian/tag :string, :db/doc "Value type for strings."},
                                              :node/ext-id                                            #:db{:id          569,
                                                                                                           :ident       :node/ext-id,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "external id reference for the entity, video for example"},
                                              :deprecated/sk                                          #:db{:id          284,
                                                                                                           :ident       :deprecated/sk,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Slovak"},
                                              :db/affinity                                            #:db{:id 34, :ident :db/affinity},
                                              :lang/wa                                                #:db{:id          509,
                                                                                                           :ident       :lang/wa,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Walloon"},
                                              :lang/bm                                                #:db{:id          347,
                                                                                                           :ident       :lang/bm,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bambara"},
                                              :lang/to                                                #:db{:id          496,
                                                                                                           :ident       :lang/to,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tongan"},
                                              :deprecated/ru                                          #:db{:id          273,
                                                                                                           :ident       :deprecated/ru,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Russian"},
                                              :temperature/K                                          #:db{:id          325,
                                                                                                           :ident       :temperature/K,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kelvin"},
                                              :address/country                                        #:db{:id          73,
                                                                                                           :ident       :address/country,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Address country"},
                                              :lang/ae                                                #:db{:id          344,
                                                                                                           :ident       :lang/ae,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Avestan"},
                                              :product/manufacturer                                   #:db{:id          525,
                                                                                                           :ident       :product/manufacturer,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A product's manufacturer - an institution"},
                                              :lang/bg                                                #:db{:id          356,
                                                                                                           :ident       :lang/bg,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bulgarian"},
                                              :volume/m3                                              #:db{:id          326,
                                                                                                           :ident       :volume/m3,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "metres cubed"},
                                              :deprecated/be                                          #:db{:id          156,
                                                                                                           :ident       :deprecated/be,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Belarusian"},
                                              :db.type/keyword                                        {:db/id        21,
                                                                                                       :db/ident     :db.type/keyword,
                                                                                                       :fressian/tag :key,
                                                                                                       :db/doc       "Value type for keywords. Keywords are used as names, and are interned for efficiency. Keywords map to the native interned-name type in languages that support them."},
                                              :lang/ms                                                #:db{:id          434,
                                                                                                           :ident       :lang/ms,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Malay"},
                                              :db/txInstant                                           #:db{:id          50,
                                                                                                           :ident       :db/txInstant,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Attribute whose value is a :db.type/instant. A :db/txInstant is recorded automatically with every transaction."},
                                              :deprecated/tw                                          #:db{:id          306,
                                                                                                           :ident       :deprecated/tw,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Twi"},
                                              :perm/target                                            #:db{:id          538,
                                                                                                           :ident       :perm/target,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Target of the permission"},
                                              :deprecated/dv                                          #:db{:id          176,
                                                                                                           :ident       :deprecated/dv,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Divehi"},
                                              :deprecated/nb                                          #:db{:id          252,
                                                                                                           :ident       :deprecated/nb,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Norwegian Bokmål"},
                                              :affiliation/uuid                                       #:db{:id          66,
                                                                                                           :ident       :affiliation/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "An affiliation's uuid"},
                                              :db.type/ref                                            {:db/id        20,
                                                                                                       :db/ident     :db.type/ref,
                                                                                                       :fressian/tag :ref,
                                                                                                       :db/doc       "Value type for references. All references from one entity to another are through attributes with this value type."},
                                              :volume/litre                                           #:db{:id          327,
                                                                                                           :ident       :volume/litre,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "litre"},
                                              :node/comment-threads                                   #:db{:id          611,
                                                                                                           :ident       :node/comment-threads,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "Node's comment threads."},
                                              :certificate/course                                     #:db{:id          586,
                                                                                                           :ident       :certificate/course,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent false,
                                                                                                           :doc         "The course of study or training of the certificate, e.g. expectably a space."},
                                              :deprecated/tl                                          #:db{:id          300,
                                                                                                           :ident       :deprecated/tl,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tagalog"},
                                              :deprecated/ss                                          #:db{:id          291,
                                                                                                           :ident       :deprecated/ss,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Swati"},
                                              :graph/distributions                                    #:db{:id          117,
                                                                                                           :ident       :graph/distributions,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "Graph distributions as a set of references to a :graph/distribution"},
                                              :space/intro-video                                      #:db{:id          573,
                                                                                                           :ident       :space/intro-video,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The intro video uuid of a space"},
                                              :deprecated/bo                                          #:db{:id          298,
                                                                                                           :ident       :deprecated/bo,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tibetan"},
                                              :lang/ig                                                #:db{:id          400,
                                                                                                           :ident       :lang/ig,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Igbo"},
                                              :lang/ee                                                #:db{:id          376,
                                                                                                           :ident       :lang/ee,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ewe"},
                                              :deprecated/kj                                          #:db{:id          227,
                                                                                                           :ident       :deprecated/kj,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kuanyama"},
                                              :time/ms                                                #:db{:id          132,
                                                                                                           :ident       :time/ms,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "millisecond"},
                                              :deprecated/lt                                          #:db{:id          234,
                                                                                                           :ident       :deprecated/lt,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Lithuanian"},
                                              :db.type/bigdec                                         {:db/id        62,
                                                                                                       :db/ident     :db.type/bigdec,
                                                                                                       :fressian/tag :bigdec,
                                                                                                       :db/doc       "Value type for arbitrary precision floating point numbers. Maps to java.math.BigDecimal on the JVM."},
                                              :permission/target                                      #:db{:id          549,
                                                                                                           :ident       :permission/target,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A permission's target"},
                                              :deprecated/my                                          #:db{:id          163,
                                                                                                           :ident       :deprecated/my,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Burmese"},
                                              :lang/aa                                                #:db{:id          334,
                                                                                                           :ident       :lang/aa,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Afar"},
                                              :brand/hero-images                                      #:db{:id          558,
                                                                                                           :ident       :brand/hero-images,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A collection of images suitable for UI backgrounds"},
                                              :lang/he                                                #:db{:id          391,
                                                                                                           :ident       :lang/he,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hebrew"},
                                              :edge/kind                                              #:db{:id          125,
                                                                                                           :ident       :edge/kind,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A edge's kind"},
                                              :lang/fl                                                #:db{:id          379,
                                                                                                           :ident       :lang/fl,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Filipino"},
                                              :deprecated/nd                                          #:db{:id          249,
                                                                                                           :ident       :deprecated/nd,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "North Ndebele"},
                                              :deprecated/hy                                          #:db{:id          147,
                                                                                                           :ident       :deprecated/hy,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Armenian"},
                                              :deprecated/ki                                          #:db{:id          220,
                                                                                                           :ident       :deprecated/ki,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kikuyu"},
                                              :lang/ng                                                #:db{:id          445,
                                                                                                           :ident       :lang/ng,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ndonga"},
                                              :deprecated/ay                                          #:db{:id          151,
                                                                                                           :ident       :deprecated/ay,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Aymara"},
                                              :lang/sv                                                #:db{:id          486,
                                                                                                           :ident       :lang/sv,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Swedish"},
                                              :lang/os                                                #:db{:id          456,
                                                                                                           :ident       :lang/os,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ossetian"},
                                              :draft/answers                                          #:db{:id          612,
                                                                                                           :ident       :draft/answers,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "List of answers."},
                                              :lang/kr                                                #:db{:id          410,
                                                                                                           :ident       :lang/kr,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kanuri"},
                                              :recall-context/percent-overdue                         #:db{:id          634,
                                                                                                           :ident       :recall-context/percent-overdue,
                                                                                                           :valueType   #:db{:id 58, :ident :db.type/double},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "How overdue a flashcard is. It's possible to sort by this field to prevent too many flashcards to be recalled at the same time."},
                                              :person/role                                            #:db{:id          548,
                                                                                                           :ident       :person/role,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A user's global role"},
                                              :deprecated/no                                          #:db{:id          254,
                                                                                                           :ident       :deprecated/no,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Norwegian"},
                                              :learn-map/percent-overdue                              #:db{:id          581,
                                                                                                           :ident       :learn-map/percent-overdue,
                                                                                                           :valueType   #:db{:id 58, :ident :db.type/double},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :deprecated/sn                                          #:db{:id          282,
                                                                                                           :ident       :deprecated/sn,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Shona"},
                                              :lang/ny                                                #:db{:id          361,
                                                                                                           :ident       :lang/ny,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chichewa"},
                                              :deprecated/fy                                          #:db{:id          318,
                                                                                                           :ident       :deprecated/fy,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Western Frisian"},
                                              :deprecated/ve                                          #:db{:id          312,
                                                                                                           :ident       :deprecated/ve,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Venda"},
                                              :feedback.reason/other                                  #:db{:id 10009953864178599, :ident :feedback.reason/other},
                                              :person/last-name                                       #:db{:id          81,
                                                                                                           :ident       :person/last-name,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's last name"},
                                              :deprecated/hi                                          #:db{:id          199,
                                                                                                           :ident       :deprecated/hi,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hindi"},
                                              :time/end                                               #:db{:id          564,
                                                                                                           :ident       :time/end,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The end of a time period"},
                                              :deprecated/qu                                          #:db{:id          269,
                                                                                                           :ident       :deprecated/qu,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Quechua"},
                                              :lang/ab                                                #:db{:id          333,
                                                                                                           :ident       :lang/ab,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Abkhazian"},
                                              :deprecated/da                                          #:db{:id          175,
                                                                                                           :ident       :deprecated/da,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Danish"},
                                              :lang/ta                                                #:db{:id          487,
                                                                                                           :ident       :lang/ta,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tamil"},
                                              :deprecated/ak                                          #:db{:id          142,
                                                                                                           :ident       :deprecated/ak,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Akan"},
                                              :deprecated/se                                          #:db{:id          277,
                                                                                                           :ident       :deprecated/se,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Northern Sami"},
                                              :deprecated/sw                                          #:db{:id          290,
                                                                                                           :ident       :deprecated/sw,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Swahili"},
                                              :learn-map/interval                                     #:db{:id          580,
                                                                                                           :ident       :learn-map/interval,
                                                                                                           :valueType   #:db{:id 22, :ident :db.type/long},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :lang/ps                                                #:db{:id          461,
                                                                                                           :ident       :lang/ps,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Pashto"},
                                              :recall-context/interval                                #:db{:id          633,
                                                                                                           :ident       :recall-context/interval,
                                                                                                           :valueType   #:db{:id 22, :ident :db.type/long},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Days between recalls."},
                                              :application/xml                                        #:db{:id 71648575717208697, :ident :application/xml},
                                              :db/noHistory                                           #:db{:id          45,
                                                                                                           :ident       :db/noHistory,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Property of an attribute. If true, past values of the attribute are not retained after indexing. Defaults to false."},
                                              :deprecated/eu                                          #:db{:id          155,
                                                                                                           :ident       :deprecated/eu,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Basque"},
                                              :deprecated/mt                                          #:db{:id          242,
                                                                                                           :ident       :deprecated/mt,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Maltese"},
                                              :text/learning-outcomes                                 #:db{:id          600,
                                                                                                           :ident       :text/learning-outcomes,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "The description of learning outcomes of entity"},
                                              :edge/context                                           #:db{:id          562,
                                                                                                           :ident       :edge/context,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The context of this edge"},
                                              :deprecated/ne                                          #:db{:id          250,
                                                                                                           :ident       :deprecated/ne,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Nepali"},
                                              :deprecated/ho                                          #:db{:id          200,
                                                                                                           :ident       :deprecated/ho,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hiri Motu"},
                                              :pass/uuid                                              #:db{:id          104,
                                                                                                           :ident       :pass/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "Pass ID"},
                                              :role/space                                             #:db{:id          102,
                                                                                                           :ident       :role/space,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A space ref"},
                                              :lang/bo                                                #:db{:id          492,
                                                                                                           :ident       :lang/bo,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tibetan"},
                                              :deprecated/ab                                          #:db{:id          139,
                                                                                                           :ident       :deprecated/ab,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Abkhazian"},
                                              :matrix/string                                          #:db{:id          602,
                                                                                                           :ident       :matrix/string,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A string-encoded matrix"},
                                              :lang/lt                                                #:db{:id          428,
                                                                                                           :ident       :lang/lt,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Lithuanian"},
                                              :lang/my                                                #:db{:id          357,
                                                                                                           :ident       :lang/my,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Burmese"},
                                              :deprecated/ha                                          #:db{:id          196,
                                                                                                           :ident       :deprecated/ha,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hausa"},
                                              :lang/pt                                                #:db{:id          462,
                                                                                                           :ident       :lang/pt,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Portuguese"},
                                              :deprecated/cy                                          #:db{:id          316,
                                                                                                           :ident       :deprecated/cy,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Welsh"},
                                              :document/format                                        #:db{:id          571,
                                                                                                           :ident       :document/format,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The format or markup of a document"},
                                              :deprecated/an                                          #:db{:id          146,
                                                                                                           :ident       :deprecated/an,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Aragonese"},
                                              :deprecated/na                                          #:db{:id          247,
                                                                                                           :ident       :deprecated/na,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Nauru"},
                                              :text/description                                       #:db{:id          557,
                                                                                                           :ident       :text/description,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "The description of an entity"},
                                              :org/name                                               #:db{:id          532,
                                                                                                           :ident       :org/name,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "An organisation's name"},
                                              :deprecated/dz                                          #:db{:id          178,
                                                                                                           :ident       :deprecated/dz,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Dzongkha"},
                                              :deprecated/ti                                          #:db{:id          297,
                                                                                                           :ident       :deprecated/ti,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tigrinya"},
                                              :deprecated/mg                                          #:db{:id          239,
                                                                                                           :ident       :deprecated/mg,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Malagasy"},
                                              :deprecated/fr                                          #:db{:id          187,
                                                                                                           :ident       :deprecated/fr,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "French"},
                                              :lang/ki                                                #:db{:id          414,
                                                                                                           :ident       :lang/ki,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kikuyu"},
                                              :address/uuid                                           #:db{:id          69,
                                                                                                           :ident       :address/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "Address uuid (currently the same as person uuid)"},
                                              :lang/fa                                                #:db{:id          459,
                                                                                                           :ident       :lang/fa,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Persian"},
                                              :person/address                                         #:db{:id          88,
                                                                                                           :ident       :person/address,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's job address"},
                                              :db/isComponent                                         #:db{:id          43,
                                                                                                           :ident       :db/isComponent,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Property of attribute whose vtype is :db.type/ref. If true, then the attribute is a component of the entity referencing it. When you query for an entire entity, components are fetched automatically. Defaults to nil."},
                                              :deprecated/rm                                          #:db{:id          270,
                                                                                                           :ident       :deprecated/rm,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Romansh"},
                                              :db.type/float                                          {:db/id        59,
                                                                                                       :db/ident     :db.type/float,
                                                                                                       :fressian/tag :float,
                                                                                                       :db/doc       "Floating point value type. Same semantics as a Java float: single-precision 32-bit IEEE 754 floating point."},
                                              :space/intro-summary                                    #:db{:id          572,
                                                                                                           :ident       :space/intro-summary,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "The summary of a space intro"},
                                              :deprecated/za                                          #:db{:id          322,
                                                                                                           :ident       :deprecated/za,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Zhuang"},
                                              :deprecated/ky                                          #:db{:id          222,
                                                                                                           :ident       :deprecated/ky,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kirghiz"},
                                              :person/affiliations                                    #:db{:id          83,
                                                                                                           :ident       :person/affiliations,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's affiliated institutions - a reference to (see. institutions.edn)"},
                                              :lang/tw                                                #:db{:id          500,
                                                                                                           :ident       :lang/tw,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Twi"},
                                              :token/kind                                             #:db{:id          543,
                                                                                                           :ident       :token/kind,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A token's kind (refresh, access etc)"},
                                              :graph/learn-map                                        #:db{:id          583,
                                                                                                           :ident       :graph/learn-map,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true},
                                              :db/cas                                                 #:db{:id 56, :ident :db/cas, :doc "Compare and swap the value of an entity's attribute."},
                                              :deprecated/zu                                          #:db{:id          323,
                                                                                                           :ident       :deprecated/zu,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Zulu"},
                                              :lang/rn                                                #:db{:id          465,
                                                                                                           :ident       :lang/rn,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Rundi"},
                                              :lang/da                                                #:db{:id          369,
                                                                                                           :ident       :lang/da,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Danish"},
                                              :role/uuid                                              #:db{:id          100,
                                                                                                           :ident       :role/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "Role uuid"},
                                              :lang/qu                                                #:db{:id          463,
                                                                                                           :ident       :lang/qu,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Quechua"},
                                              :db/fulltext                                            #:db{:id          51,
                                                                                                           :ident       :db/fulltext,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Property of an attribute. If true, create a fulltext search index for the attribute. Defaults to false."},
                                              :lang/ak                                                #:db{:id          336,
                                                                                                           :ident       :lang/ak,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Akan"},
                                              :org/spaces                                             #:db{:id          536,
                                                                                                           :ident       :org/spaces,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent false,
                                                                                                           :noHistory   false,
                                                                                                           :doc         "Organisation's spaces"},
                                              :deprecated/gd                                          #:db{:id          281,
                                                                                                           :ident       :deprecated/gd,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Gaelic"},
                                              :lang/sw                                                #:db{:id          484,
                                                                                                           :ident       :lang/sw,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Swahili"},
                                              :lang/eu                                                #:db{:id          349,
                                                                                                           :ident       :lang/eu,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Basque"},
                                              :lang/mh                                                #:db{:id          439,
                                                                                                           :ident       :lang/mh,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Marshallese"},
                                              :draft/originality-ratings                              #:db{:id          625,
                                                                                                           :ident       :draft/originality-ratings,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :noHistory   true,
                                                                                                           :doc         "The originality ratings of the draft."},
                                              :product/description                                    #:db{:id          521,
                                                                                                           :ident       :product/description,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A product's description"},
                                              :deprecated/sr                                          #:db{:id          280,
                                                                                                           :ident       :deprecated/sr,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Serbian"},
                                              :deprecated/lv                                          #:db{:id          236,
                                                                                                           :ident       :deprecated/lv,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Latvian"},
                                              :person/orgs                                            #:db{:id          552,
                                                                                                           :ident       :person/orgs,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A person's access level to organizations"},
                                              :lang/mt                                                #:db{:id          436,
                                                                                                           :ident       :lang/mt,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Maltese"},
                                              :lang/ne                                                #:db{:id          444,
                                                                                                           :ident       :lang/ne,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Nepali"},
                                              :deprecated/ng                                          #:db{:id          251,
                                                                                                           :ident       :deprecated/ng,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ndonga"},
                                              :db/system-tx                                           #:db{:id          7,
                                                                                                           :ident       :db/system-tx,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many}},
                                              :lang/fo                                                #:db{:id          377,
                                                                                                           :ident       :lang/fo,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Faroese"},
                                              :question/flashcard?                                    #:db{:id          617,
                                                                                                           :ident       :question/flashcard?,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Is the question a flashcard?"},
                                              :person/is-memorizing                                   #:db{:id          597,
                                                                                                           :ident       :person/is-memorizing,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Is memorizing concept's user"},
                                              :graph/distribution-key                                 #:db{:id          115,
                                                                                                           :ident       :graph/distribution-key,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A key to a :graph/distribution value"},
                                              :db.unique/value                                        #:db{:id    37,
                                                                                                           :ident :db.unique/value,
                                                                                                           :doc   "Specifies that an attribute's value is unique. Attempts to create a new entity with a colliding value for a :db.unique/value will fail."},
                                              :lang/vi                                                #:db{:id          507,
                                                                                                           :ident       :lang/vi,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Vietnamese"},
                                              :deprecated/nl                                          #:db{:id          177,
                                                                                                           :ident       :deprecated/nl,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Dutch"},
                                              :lang/no                                                #:db{:id          448,
                                                                                                           :ident       :lang/no,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Norwegian"},
                                              :deprecated/ae                                          #:db{:id          150,
                                                                                                           :ident       :deprecated/ae,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Avestan"},
                                              :deprecated/mk                                          #:db{:id          238,
                                                                                                           :ident       :deprecated/mk,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Macedonian"},
                                              :affiliation/institution                                #:db{:id          67,
                                                                                                           :ident       :affiliation/institution,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A reference to a :institution/name"},
                                              :deprecated/bg                                          #:db{:id          162,
                                                                                                           :ident       :deprecated/bg,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bulgarian"},
                                              :node/slides                                            #:db{:id          574,
                                                                                                           :ident       :node/slides,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "Slides, containing additional information about a subject piece of content"},
                                              :lang/sq                                                #:db{:id          337,
                                                                                                           :ident       :lang/sq,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Albanian"},
                                              :deprecated/ml                                          #:db{:id          241,
                                                                                                           :ident       :deprecated/ml,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Malayalam"},
                                              :certificate/issued-on                                  #:db{:id          588,
                                                                                                           :ident       :certificate/issued-on,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent false,
                                                                                                           :doc         "The date and time when the certificate is granted."},
                                              :deprecated/bm                                          #:db{:id          153,
                                                                                                           :ident       :deprecated/bm,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bambara"},
                                              :person/uuid                                            #:db{:id          75,
                                                                                                           :ident       :person/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "Person's UUID"},
                                              :deprecated/tk                                          #:db{:id          299,
                                                                                                           :ident       :deprecated/tk,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Turkmen"},
                                              :lang/si                                                #:db{:id          477,
                                                                                                           :ident       :lang/si,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sinhala"},
                                              :lang/hi                                                #:db{:id          393,
                                                                                                           :ident       :lang/hi,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hindi"},
                                              :nav/mode                                               #:db{:id          560,
                                                                                                           :ident       :nav/mode,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A reference to a collection of allowed ML modes"},
                                              :deprecated/ms                                          #:db{:id          240,
                                                                                                           :ident       :deprecated/ms,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Malay"},
                                              :url/domain                                             #:db{:id          547,
                                                                                                           :ident       :url/domain,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "domain name and extension (example.com"},
                                              :lang/tg                                                #:db{:id          489,
                                                                                                           :ident       :lang/tg,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tajik"},
                                              :note/space                                             #:db{:id          136,
                                                                                                           :ident       :note/space,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The space context of this note"},
                                              :feedback.reason/confusing-question                     #:db{:id 65627650043599786, :ident :feedback.reason/confusing-question},
                                              :lang/rm                                                #:db{:id          464,
                                                                                                           :ident       :lang/rm,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Romansh"},
                                              :space/languages                                        #:db{:id          631,
                                                                                                           :ident       :space/languages,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "Space's languages."},
                                              :deprecated/ps                                          #:db{:id          267,
                                                                                                           :ident       :deprecated/ps,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Pashto"},
                                              :db.type/bigint                                         {:db/id        61,
                                                                                                       :db/ident     :db.type/bigint,
                                                                                                       :fressian/tag :bigint,
                                                                                                       :db/doc       "Value type for arbitrary precision integers. Maps to java.math.BigInteger on the JVM."},
                                              :rating/score                                           #:db{:id          628,
                                                                                                           :ident       :rating/score,
                                                                                                           :valueType   #:db{:id 58, :ident :db.type/double},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The draft score, between 0.0 and 5.0."},
                                              :lang/ky                                                #:db{:id          416,
                                                                                                           :ident       :lang/ky,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kirghiz"},
                                              :comment-thread/comments                                #:db{:id          606,
                                                                                                           :ident       :comment-thread/comments,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "Comments belonging to the thread."},
                                              :lang/gv                                                #:db{:id          431,
                                                                                                           :ident       :lang/gv,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Manx"},
                                              :institution/name                                       #:db{:id          64,
                                                                                                           :ident       :institution/name,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "A name designated to an insititution"},
                                              :db/retractEntity                                       #:db{:id    57,
                                                                                                           :ident :db/retractEntity,
                                                                                                           :doc   "Retract all facts about an entity, including references from other entities and component attributes recursively."},
                                              :brand/logo-url                                         #:db{:id          528,
                                                                                                           :ident       :brand/logo-url,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A brand's logo url"},
                                              :edge/node                                              #:db{:id          124,
                                                                                                           :ident       :edge/node,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent false,
                                                                                                           :doc         "This edge's destination node"},
                                              :lang/cv                                                #:db{:id          363,
                                                                                                           :ident       :lang/cv,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chuvash"},
                                              :db/retract                                             #:db{:id    2,
                                                                                                           :ident :db/retract,
                                                                                                           :doc   "Primitive retraction. All transactions eventually reduce to a collection of assertions and retractions of facts, e.g. [:db/retract fred :age 42]."},
                                              :lang/hr                                                #:db{:id          367,
                                                                                                           :ident       :lang/hr,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Croatian"},
                                              :deprecated/cu                                          #:db{:id          259,
                                                                                                           :ident       :deprecated/cu,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Church Slavic"},
                                              :deprecated/ce                                          #:db{:id          166,
                                                                                                           :ident       :deprecated/ce,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chechen"},
                                              :deprecated/kv                                          #:db{:id          223,
                                                                                                           :ident       :deprecated/kv,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Komi"},
                                              :lang/io                                                #:db{:id          402,
                                                                                                           :ident       :lang/io,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ido"},
                                              :lang/lg                                                #:db{:id          424,
                                                                                                           :ident       :lang/lg,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ganda"},
                                              :institution/dep                                        #:db{:id          65,
                                                                                                           :ident       :institution/dep,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "A name desiganted for a department"},
                                              :coc/uuid                                               #:db{:id          93,
                                                                                                           :ident       :coc/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "A chain of command's uuid"},
                                              :role/type                                              #:db{:id          103,
                                                                                                           :ident       :role/type,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A person's role within a space #{:cbs/user :cbs/tutor :cbs/admi}"},
                                              :deprecated/sm                                          #:db{:id          278,
                                                                                                           :ident       :deprecated/sm,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Samoan"},
                                              :lang/fi                                                #:db{:id          380,
                                                                                                           :ident       :lang/fi,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Finnish"},
                                              :lang/ha                                                #:db{:id          390,
                                                                                                           :ident       :lang/ha,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hausa"},
                                              :lang/ln                                                #:db{:id          426,
                                                                                                           :ident       :lang/ln,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Lingala"},
                                              :lang/ii                                                #:db{:id          449,
                                                                                                           :ident       :lang/ii,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sichuan Yi"},
                                              :person/job-title                                       #:db{:id          85,
                                                                                                           :ident       :person/job-title,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's job title"},
                                              :lang/lu                                                #:db{:id          429,
                                                                                                           :ident       :lang/lu,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Luba-Katanga"},
                                              :deprecated/kr                                          #:db{:id          216,
                                                                                                           :ident       :deprecated/kr,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kanuri"},
                                              :lang/fr                                                #:db{:id          381,
                                                                                                           :ident       :lang/fr,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "French"},
                                              :lang/ti                                                #:db{:id          491,
                                                                                                           :ident       :lang/ti,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tigrinya"},
                                              :deprecated/kg                                          #:db{:id          224,
                                                                                                           :ident       :deprecated/kg,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kongo"},
                                              :node/active?                                           #:db{:id          554,
                                                                                                           :ident       :node/active?,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Is a node active?"},
                                              :deprecated/es                                          #:db{:id          288,
                                                                                                           :ident       :deprecated/es,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Spanish"},
                                              :deprecated/af                                          #:db{:id          141,
                                                                                                           :ident       :deprecated/af,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Afrikaans"},
                                              :lang/kl                                                #:db{:id          408,
                                                                                                           :ident       :lang/kl,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kalaallisut"},
                                              :deprecated/sa                                          #:db{:id          274,
                                                                                                           :ident       :deprecated/sa,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sanskrit"},
                                              :lang/ja                                                #:db{:id          406,
                                                                                                           :ident       :lang/ja,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Japanese"},
                                              :mass/kg                                                #:db{:id          328,
                                                                                                           :ident       :mass/kg,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "kilogram"},
                                              :deprecated/aa                                          #:db{:id          140,
                                                                                                           :ident       :deprecated/aa,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Afar"},
                                              :question/answer                                        #:db{:id          131,
                                                                                                           :ident       :question/answer,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A question's answers"},
                                              :lang/jv                                                #:db{:id          407,
                                                                                                           :ident       :lang/jv,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Javanese"},
                                              :rating/author                                          #:db{:id          627,
                                                                                                           :ident       :rating/author,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The person who authored the rating."},
                                              :deprecated/gn                                          #:db{:id          193,
                                                                                                           :ident       :deprecated/gn,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Guaraní"},
                                              :deprecated/ug                                          #:db{:id          308,
                                                                                                           :ident       :deprecated/ug,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Uighur"},
                                              :lang/ga                                                #:db{:id          399,
                                                                                                           :ident       :lang/ga,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Irish"},
                                              :org/brand                                              #:db{:id          534,
                                                                                                           :ident       :org/brand,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :isComponent true,
                                                                                                           :doc         "An organisations branding data"},
                                              :node/kind                                              #:db{:id          121,
                                                                                                           :ident       :node/kind,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The kind of this node"},
                                              :deprecated/az                                          #:db{:id          152,
                                                                                                           :ident       :deprecated/az,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Azerbaijani"},
                                              :lang/tk                                                #:db{:id          493,
                                                                                                           :ident       :lang/tk,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Turkmen"},
                                              :deprecated/bi                                          #:db{:id          159,
                                                                                                           :ident       :deprecated/bi,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bislama"},
                                              :deprecated/el                                          #:db{:id          192,
                                                                                                           :ident       :deprecated/el,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Greek"},
                                              :lang/wo                                                #:db{:id          511,
                                                                                                           :ident       :lang/wo,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Wolof"},
                                              :db.part/tx                                             #:db{:id    3,
                                                                                                           :ident :db.part/tx,
                                                                                                           :doc   "Partition used to store data about transactions. Transaction data always includes a :db/txInstant which is the transaction's timestamp, and can be extended to store other information at transaction granularity."},
                                              :deprecated/ks                                          #:db{:id          217,
                                                                                                           :ident       :deprecated/ks,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kashmiri"},
                                              :deprecated/gu                                          #:db{:id          194,
                                                                                                           :ident       :deprecated/gu,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Gujarati"},
                                              :lang/is                                                #:db{:id          403,
                                                                                                           :ident       :lang/is,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Icelandic"},
                                              :text/html                                              #:db{:id 46526934044776463, :ident :text/html},
                                              :deprecated/wa                                          #:db{:id          315,
                                                                                                           :ident       :deprecated/wa,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Walloon"},
                                              :deprecated/lo                                          #:db{:id          233,
                                                                                                           :ident       :deprecated/lo,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Lao"},
                                              :deprecated/to                                          #:db{:id          302,
                                                                                                           :ident       :deprecated/to,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tongan"},
                                              :lang/rw                                                #:db{:id          415,
                                                                                                           :ident       :lang/rw,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kinyarwanda"},
                                              :deprecated/pi                                          #:db{:id          264,
                                                                                                           :ident       :deprecated/pi,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Pali"},
                                              :deprecated/ff                                          #:db{:id          188,
                                                                                                           :ident       :deprecated/ff,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Fulah"},
                                              :lang/sr                                                #:db{:id          474,
                                                                                                           :ident       :lang/sr,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Serbian"},
                                              :text/title                                             #:db{:id          555,
                                                                                                           :ident       :text/title,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "The title of an entity"},
                                              :person/password                                        #:db{:id          78,
                                                                                                           :ident       :person/password,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's password hash"},
                                              :editor/obsoleted-person                                #:db{:id          591,
                                                                                                           :ident       :editor/obsoleted-person,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :lang/ka                                                #:db{:id          384,
                                                                                                           :ident       :lang/ka,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Georgian"},
                                              :feedback/target                                        #:db{:id          595,
                                                                                                           :ident       :feedback/target,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent false,
                                                                                                           :doc         "The target of the given feedback, e.g. a question, an answer."},
                                              :deprecated/fo                                          #:db{:id          183,
                                                                                                           :ident       :deprecated/fo,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Faroese"},
                                              :graph/double                                           #:db{:id          111,
                                                                                                           :ident       :graph/double,
                                                                                                           :valueType   #:db{:id 58, :ident :db.type/double},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A double value"},
                                              :lang/cr                                                #:db{:id          366,
                                                                                                           :ident       :lang/cr,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Cree"},
                                              :deprecated/rw                                          #:db{:id          221,
                                                                                                           :ident       :deprecated/rw,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kinyarwanda"},
                                              :lang/sm                                                #:db{:id          472,
                                                                                                           :ident       :lang/sm,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Samoan"},
                                              :deprecated.text/tran                                   #:db{:id          129,
                                                                                                           :ident       :deprecated.text/tran,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "The actual text of this text"},
                                              :media/extension                                        #:db{:id          133,
                                                                                                           :ident       :media/extension,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "the media's file extension: svg, mp4 etc"},
                                              :deprecated/fl                                          #:db{:id          185,
                                                                                                           :ident       :deprecated/fl,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Filipino"},
                                              :db/cardinality                                         #:db{:id          41,
                                                                                                           :ident       :db/cardinality,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Property of an attribute. Two possible values: :db.cardinality/one for single-valued attributes, and :db.cardinality/many for many-valued attributes. Defaults to :db.cardinality/one."},
                                              :space/title                                            #:db{:id          98,
                                                                                                           :ident       :space/title,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "space title"},
                                              :deprecated/ka                                          #:db{:id          190,
                                                                                                           :ident       :deprecated/ka,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Georgian"},
                                              :time/duration                                          #:db{:id          565,
                                                                                                           :ident       :time/duration,
                                                                                                           :valueType   #:db{:id 22, :ident :db.type/long},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The duration of a time period"},
                                              :coc/role                                               #:db{:id          95,
                                                                                                           :ident       :coc/role,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The level of access (could be manager or student)"},
                                              :lang/kg                                                #:db{:id          418,
                                                                                                           :ident       :lang/kg,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kongo"},
                                              :lang/es                                                #:db{:id          482,
                                                                                                           :ident       :lang/es,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Spanish"},
                                              :db/doc                                                 #:db{:id          63,
                                                                                                           :ident       :db/doc,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :fulltext    true,
                                                                                                           :doc         "Documentation string for an entity."},
                                              :comment/id                                             #:db{:id          607,
                                                                                                           :ident       :comment/id,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity}},
                                              :lang/ko                                                #:db{:id          419,
                                                                                                           :ident       :lang/ko,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Korean"},
                                              :deprecated/ja                                          #:db{:id          212,
                                                                                                           :ident       :deprecated/ja,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Japanese"},
                                              :lang/ce                                                #:db{:id          360,
                                                                                                           :ident       :lang/ce,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chechen"},
                                              :brand/dark?                                            #:db{:id          553,
                                                                                                           :ident       :brand/dark?,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Does the brand use a dark background?"},
                                              :lang/so                                                #:db{:id          480,
                                                                                                           :ident       :lang/so,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Somali"},
                                              :deprecated/jv                                          #:db{:id          213,
                                                                                                           :ident       :deprecated/jv,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Javanese"},
                                              :lang/te                                                #:db{:id          488,
                                                                                                           :ident       :lang/te,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Telugu"},
                                              :lang/kv                                                #:db{:id          417,
                                                                                                           :ident       :lang/kv,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Komi"},
                                              :deprecated/mh                                          #:db{:id          245,
                                                                                                           :ident       :deprecated/mh,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Marshallese"},
                                              :format.kind/html                                       #:db{:id 17979214140954899, :ident :format.kind/html},
                                              :draft/answer                                           #:db{:id          614,
                                                                                                           :ident       :draft/answer,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A draft's answers."},
                                              :lang/cu                                                #:db{:id          453,
                                                                                                           :ident       :lang/cu,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Church Slavic"},
                                              :lang/tn                                                #:db{:id          495,
                                                                                                           :ident       :lang/tn,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tswana"},
                                              :draft/status                                           #:db{:id          615,
                                                                                                           :ident       :draft/status,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "One of :draft.status."},
                                              :deprecated/wo                                          #:db{:id          317,
                                                                                                           :ident       :deprecated/wo,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Wolof"},
                                              :response/expected-answer                               #:db{:id          566,
                                                                                                           :ident       :response/expected-answer,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The expected answer entity of a response"},
                                              :lang/la                                                #:db{:id          422,
                                                                                                           :ident       :lang/la,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Latin"},
                                              :person/field-of-study                                  #:db{:id          84,
                                                                                                           :ident       :person/field-of-study,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's field of study"},
                                              :volume/ml                                              #:db{:id          329,
                                                                                                           :ident       :volume/ml,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "millilitre"},
                                              :db.excise/before                                       #:db{:id          18,
                                                                                                           :ident       :db.excise/before,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :deprecated/fi                                          #:db{:id          186,
                                                                                                           :ident       :deprecated/fi,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Finnish"},
                                              :TODO                                                   #:db{:id 12890674329190904, :ident :TODO},
                                              :lang/ff                                                #:db{:id          382,
                                                                                                           :ident       :lang/ff,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Fulah"},
                                              :deprecated.product/manufacturerd                       #:db{:id          519,
                                                                                                           :ident       :deprecated.product/manufacturerd,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "A product's manufacturer - an institution"},
                                              :lang/pi                                                #:db{:id          458,
                                                                                                           :ident       :lang/pi,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Pali"},
                                              :lang/ay                                                #:db{:id          345,
                                                                                                           :ident       :lang/ay,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Aymara"},
                                              :deprecated/ba                                          #:db{:id          154,
                                                                                                           :ident       :deprecated/ba,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bashkir"},
                                              :deprecated/lu                                          #:db{:id          235,
                                                                                                           :ident       :deprecated/lu,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Luba-Katanga"},
                                              :lang/nd                                                #:db{:id          443,
                                                                                                           :ident       :lang/nd,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "North Ndebele"},
                                              :lang/kj                                                #:db{:id          421,
                                                                                                           :ident       :lang/kj,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kuanyama"},
                                              :lang/ca                                                #:db{:id          358,
                                                                                                           :ident       :lang/ca,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Catalan"},
                                              :lang/gu                                                #:db{:id          388,
                                                                                                           :ident       :lang/gu,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Gujarati"},
                                              :pass/valid-from                                        #:db{:id          107,
                                                                                                           :ident       :pass/valid-from,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Pass Expiration Date"},
                                              :deprecated/rn                                          #:db{:id          271,
                                                                                                           :ident       :deprecated/rn,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Rundi"},
                                              :org/allow-signup                                       #:db{:id          577,
                                                                                                           :ident       :org/allow-signup,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Are new users allowed to register?"},
                                              :token/exp                                              #:db{:id          544,
                                                                                                           :ident       :token/exp,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A token's expiration date"},
                                              :deprecated/sv                                          #:db{:id          292,
                                                                                                           :ident       :deprecated/sv,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Swedish"},
                                              :certificate/subject                                    #:db{:id          587,
                                                                                                           :ident       :certificate/subject,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent false,
                                                                                                           :doc         "The subject to whom the certificate is granted."},
                                              :deprecated/os                                          #:db{:id          262,
                                                                                                           :ident       :deprecated/os,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ossetian"},
                                              :db/ident                                               #:db{:id          10,
                                                                                                           :ident       :db/ident,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "Attribute used to uniquely name an entity."},
                                              :deprecated/pt                                          #:db{:id          268,
                                                                                                           :ident       :deprecated/pt,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Portuguese"},
                                              :deprecated/ny                                          #:db{:id          167,
                                                                                                           :ident       :deprecated/ny,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chichewa"},
                                              :lang/be                                                #:db{:id          350,
                                                                                                           :ident       :lang/be,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Belarusian"},
                                              :token/value                                            #:db{:id          542,
                                                                                                           :ident       :token/value,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A token's value"},
                                              :lang/ks                                                #:db{:id          411,
                                                                                                           :ident       :lang/ks,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kashmiri"},
                                              :lang/ug                                                #:db{:id          502,
                                                                                                           :ident       :lang/ug,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Uighur"},
                                              :lang/gn                                                #:db{:id          387,
                                                                                                           :ident       :lang/gn,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Guaraní"},
                                              :person/is-mastering                                    #:db{:id          598,
                                                                                                           :ident       :person/is-mastering,
                                                                                                           :valueType   #:db{:id 24, :ident :db.type/boolean},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Is mastering concept's user"},
                                              :lang/az                                                #:db{:id          346,
                                                                                                           :ident       :lang/az,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Azerbaijani"},
                                              :lang/bi                                                #:db{:id          353,
                                                                                                           :ident       :lang/bi,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bislama"},
                                              :deprecated.edge/path                                   #:db{:id          523,
                                                                                                           :ident       :deprecated.edge/path,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "The path this edge is part of - there can be many paths per set/space"},
                                              :lang/tl                                                #:db{:id          494,
                                                                                                           :ident       :lang/tl,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tagalog"},
                                              :lang/el                                                #:db{:id          386,
                                                                                                           :ident       :lang/el,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Greek"},
                                              :draft-answer/mass                                      #:db{:id          613,
                                                                                                           :ident       :draft-answer/mass,
                                                                                                           :valueType   #:db{:id 58, :ident :db.type/double},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The mass (weight, probability) of this edge."},
                                              :deprecated/kn                                          #:db{:id          215,
                                                                                                           :ident       :deprecated/kn,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kannada"},
                                              :node/text                                              #:db{:id          119,
                                                                                                           :ident       :node/text,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "the text of this node"},
                                              :edge/path                                              #:db{:id          524,
                                                                                                           :ident       :edge/path,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The path this edge is part of - there can be many paths per set/space"},
                                              :deprecated/ht                                          #:db{:id          195,
                                                                                                           :ident       :deprecated/ht,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Haitian"},
                                              :db/add                                                 #:db{:id    1,
                                                                                                           :ident :db/add,
                                                                                                           :doc   "Primitive assertion. All transactions eventually reduce to a collection of primitive assertions and retractions of facts, e.g. [:db/add fred :age 42]."},
                                              :deprecated/it                                          #:db{:id          210,
                                                                                                           :ident       :deprecated/it,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Italian"},
                                              :video/slide                                            #:db{:id          134,
                                                                                                           :ident       :video/slide,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A video's slides"},
                                              :deprecated/su                                          #:db{:id          289,
                                                                                                           :ident       :deprecated/su,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sundanese"},
                                              :deprecated/yo                                          #:db{:id          321,
                                                                                                           :ident       :deprecated/yo,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Yoruba"},
                                              :lang/yi                                                #:db{:id          514,
                                                                                                           :ident       :lang/yi,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Yiddish"},
                                              :db.type/double                                         {:db/id        58,
                                                                                                       :db/ident     :db.type/double,
                                                                                                       :fressian/tag :double,
                                                                                                       :db/doc       "Floating point value type. Same semantics as a Java double: double-precision 64-bit IEEE 754 floating point."},
                                              :number/mole                                            #:db{:id          330,
                                                                                                           :ident       :number/mole,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "mole"},
                                              :pass/courses                                           #:db{:id          109,
                                                                                                           :ident       :pass/courses,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "The courses to which this pass grants access"},
                                              :format.kind/hiccup                                     #:db{:id 33389969115863316, :ident :format.kind/hiccup},
                                              :text/summary                                           #:db{:id          556,
                                                                                                           :ident       :text/summary,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "The summary of an entity"},
                                              :lang/hz                                                #:db{:id          392,
                                                                                                           :ident       :lang/hz,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Herero"},
                                              :lang/uz                                                #:db{:id          505,
                                                                                                           :ident       :lang/uz,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Uzbek"},
                                              :lang/mr                                                #:db{:id          438,
                                                                                                           :ident       :lang/mr,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Marathi"},
                                              :db.type/long                                           {:db/id        22,
                                                                                                       :db/ident     :db.type/long,
                                                                                                       :fressian/tag :int,
                                                                                                       :db/doc       "Fixed integer value type. Same semantics as a Java long: 64 bits wide, two's complement binary representation."},
                                              :comment/user                                           #:db{:id          608,
                                                                                                           :ident       :comment/user,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The author of the comment."},
                                              :eula/md5                                               #:db{:id          567,
                                                                                                           :ident       :eula/md5,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The md5 sum of the whole of a EULA document (including markup)"},
                                              :lang/ku                                                #:db{:id          420,
                                                                                                           :ident       :lang/ku,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kurdish"},
                                              :deprecated/is                                          #:db{:id          209,
                                                                                                           :ident       :deprecated/is,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Icelandic"},
                                              :paper/doi                                              #:db{:id          137,
                                                                                                           :ident       :paper/doi,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "DOI of a paper"},
                                              :lang/bh                                                #:db{:id          352,
                                                                                                           :ident       :lang/bh,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bihari languages"},
                                              :deprecated/ch                                          #:db{:id          165,
                                                                                                           :ident       :deprecated/ch,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chamorro"},
                                              :space/point                                            #:db{:id          99,
                                                                                                           :ident       :space/point,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "space nodes"},
                                              :video/mp4                                              #:db{:id 56721605858378987, :ident :video/mp4},
                                              :obr/uuid                                               #:db{:id          535,
                                                                                                           :ident       :obr/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "Entity's UUID"},
                                              :deprecated/ga                                          #:db{:id          205,
                                                                                                           :ident       :deprecated/ga,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Irish"},
                                              :person/first-name                                      #:db{:id          80,
                                                                                                           :ident       :person/first-name,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's first name"},
                                              :nav.mode/fg                                            #:db{:id 3118214978600130, :ident :nav.mode/fg},
                                              :time/start                                             #:db{:id          563,
                                                                                                           :ident       :time/start,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The start of a time period"},
                                              :deprecated/sg                                          #:db{:id          279,
                                                                                                           :ident       :deprecated/sg,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sango"},
                                              :affiliation/dep                                        #:db{:id          68,
                                                                                                           :ident       :affiliation/dep,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A reference to a :institution/dep"},
                                              :lang/om                                                #:db{:id          454,
                                                                                                           :ident       :lang/om,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Oromo"},
                                              :db.cardinality/many                                    #:db{:id    36,
                                                                                                           :ident :db.cardinality/many,
                                                                                                           :doc   "One of two legal values for the :db/cardinality attribute. Specify :db.cardinality/one for single-valued attributes, and :db.cardinality/many for many-valued attributes."},
                                              :question.answer-type/multiple-choice                   #:db{:id 752065959307863, :ident :question.answer-type/multiple-choice},
                                              :view/visibility                                        #:db{:id          540,
                                                                                                           :ident       :view/visibility,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The visibility of something"},
                                              :lang/kk                                                #:db{:id          412,
                                                                                                           :ident       :lang/kk,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kazakh"},
                                              :lang/fj                                                #:db{:id          378,
                                                                                                           :ident       :lang/fj,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Fijian"},
                                              :pass/valid-to                                          #:db{:id          108,
                                                                                                           :ident       :pass/valid-to,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Pass Expiration Date"},
                                              :brand/color-minor                                      #:db{:id          530,
                                                                                                           :ident       :brand/color-minor,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A brand's minor color"},
                                              :deprecated/uk                                          #:db{:id          309,
                                                                                                           :ident       :deprecated/uk,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ukrainian"},
                                              :deprecated/kl                                          #:db{:id          214,
                                                                                                           :ident       :deprecated/kl,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kalaallisut"},
                                              :deprecated/ln                                          #:db{:id          232,
                                                                                                           :ident       :deprecated/ln,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Lingala"},
                                              :deprecated/hu                                          #:db{:id          201,
                                                                                                           :ident       :deprecated/hu,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hungarian"},
                                              :edge/distributions                                     {:text/text      "an octopus",
                                                                                                       :db/valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                       :db/txInstant   #inst"2018-09-24T13:40:15.794-00:00",
                                                                                                       :db/isComponent true,
                                                                                                       :db/cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                       :db/doc         "Graph distributions as a set of references to a :graph/distribution",
                                                                                                       :db/id          123,
                                                                                                       :db/ident       :edge/distributions,
                                                                                                       :person/email   "chuong@gmail.com"},
                                              :lang/ty                                                #:db{:id          501,
                                                                                                           :ident       :lang/ty,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tahitian"},
                                              :deprecated/br                                          #:db{:id          161,
                                                                                                           :ident       :deprecated/br,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Breton"},
                                              :lang/or                                                #:db{:id          455,
                                                                                                           :ident       :lang/or,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Oriya"},
                                              :question/documents                                     #:db{:id          618,
                                                                                                           :ident       :question/documents,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "Documents associated with this question."},
                                              :lang/nn                                                #:db{:id          447,
                                                                                                           :ident       :lang/nn,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Norwegian Nynorsk"},
                                              :deprecated/vo                                          #:db{:id          314,
                                                                                                           :ident       :deprecated/vo,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Volapük"},
                                              :node/uuid                                              #:db{:id          118,
                                                                                                           :ident       :node/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "A node's uuid"},
                                              :deprecated/av                                          #:db{:id          149,
                                                                                                           :ident       :deprecated/av,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Avaric"},
                                              :db.install/valueType                                   #:db{:id          12,
                                                                                                           :ident       :db.install/valueType,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as a value type."},
                                              :lang/as                                                #:db{:id          342,
                                                                                                           :ident       :lang/as,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Assamese"},
                                              :deprecated/sd                                          #:db{:id          276,
                                                                                                           :ident       :deprecated/sd,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sindhi"},
                                              :person/billing-contact-name                            #:db{:id          87,
                                                                                                           :ident       :person/billing-contact-name,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's billing contact name"},
                                              :graph/recall-context                                   #:db{:id          636,
                                                                                                           :ident       :graph/recall-context,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A learn map belonging to a user and a question/flashcard."},
                                              :lang/ro                                                #:db{:id          466,
                                                                                                           :ident       :lang/ro,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Romanian"},
                                              :deprecated/et                                          #:db{:id          181,
                                                                                                           :ident       :deprecated/et,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Estonian"},
                                              :db.alter/attribute                                     #:db{:id          19,
                                                                                                           :ident       :db.alter/attribute,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will alter the definition of existing attribute v."},
                                              :address/street                                         #:db{:id          71,
                                                                                                           :ident       :address/street,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Address street"},
                                              :deprecated/tr                                          #:db{:id          303,
                                                                                                           :ident       :deprecated/tr,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Turkish"},
                                              :deprecated/ur                                          #:db{:id          310,
                                                                                                           :ident       :deprecated/ur,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Urdu"},
                                              :learn-map/difficulty                                   #:db{:id          584,
                                                                                                           :ident       :learn-map/difficulty,
                                                                                                           :valueType   #:db{:id 58, :ident :db.type/double},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :deprecated/sl                                          #:db{:id          285,
                                                                                                           :ident       :deprecated/sl,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Slovenian"},
                                              :deprecated/iu                                          #:db{:id          211,
                                                                                                           :ident       :deprecated/iu,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Inuktitut"},
                                              :lang/cs                                                #:db{:id          368,
                                                                                                           :ident       :lang/cs,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Czech"},
                                              :comment-thread/id                                      #:db{:id          605,
                                                                                                           :ident       :comment-thread/id,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity}},
                                              :recall-context/difficulty                              #:db{:id          632,
                                                                                                           :ident       :recall-context/difficulty,
                                                                                                           :valueType   #:db{:id 58, :ident :db.type/double},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The question's difficulty."},
                                              :brand/img-url                                          #:db{:id          623,
                                                                                                           :ident       :brand/img-url,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The URL of an image used for branding"},
                                              :lang/oj                                                #:db{:id          452,
                                                                                                           :ident       :lang/oj,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ojibwa"},
                                              :feedback.reason/confusing-answer                       #:db{:id 41447190325549993, :ident :feedback.reason/confusing-answer},
                                              :person/title                                           #:db{:id          79,
                                                                                                           :ident       :person/title,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's title: \"mr\" \"mrs\" \"ms\" \"dr.\" \"professor\" "},
                                              :lang/ba                                                #:db{:id          348,
                                                                                                           :ident       :lang/ba,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Bashkir"},
                                              :db.install/partition                                   #:db{:id          11,
                                                                                                           :ident       :db.install/partition,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as a partition."},
                                              :application/pdf                                        #:db{:id 46773224650261718, :ident :application/pdf},
                                              :lang/th                                                #:db{:id          490,
                                                                                                           :ident       :lang/th,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Thai"},
                                              :deprecated/cv                                          #:db{:id          169,
                                                                                                           :ident       :deprecated/cv,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chuvash"},
                                              :space/length                                           #:db{:id          629,
                                                                                                           :ident       :space/length,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "Space's length in ms, i.e. {:space/length {:time/ms xxxx}}"},
                                              :edge/ref                                               #:db{:id          568,
                                                                                                           :ident       :edge/ref,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "A reference to an edge's conditions"},
                                              :lang/xh                                                #:db{:id          513,
                                                                                                           :ident       :lang/xh,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Xhosa"},
                                              :lang/mi                                                #:db{:id          437,
                                                                                                           :ident       :lang/mi,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Maori"},
                                              :deprecated/li                                          #:db{:id          231,
                                                                                                           :ident       :deprecated/li,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Limburgan"},
                                              :text/tran                                              #:db{:id          526,
                                                                                                           :ident       :text/tran,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :isComponent true,
                                                                                                           :doc         "Translations map of the text"},
                                              :permission/level                                       #:db{:id          550,
                                                                                                           :ident       :permission/level,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A permission's level"},
                                              :address/name                                           #:db{:id          70,
                                                                                                           :ident       :address/name,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Address name"},
                                              :brand/color-major                                      #:db{:id          529,
                                                                                                           :ident       :brand/color-major,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A brand's major color"},
                                              :lang/mn                                                #:db{:id          440,
                                                                                                           :ident       :lang/mn,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Mongolian"},
                                              :deprecated/lg                                          #:db{:id          230,
                                                                                                           :ident       :deprecated/lg,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ganda"},
                                              :draft.status/published                                 #:db{:id 3153399354110170, :ident :draft.status/published},
                                              :deprecated/cs                                          #:db{:id          174,
                                                                                                           :ident       :deprecated/cs,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Czech"},
                                              :deprecated/ii                                          #:db{:id          255,
                                                                                                           :ident       :deprecated/ii,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sichuan Yi"},
                                              :perm/source                                            #:db{:id          537,
                                                                                                           :ident       :perm/source,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Source of the permission"},
                                              :deprecated/ts                                          #:db{:id          304,
                                                                                                           :ident       :deprecated/ts,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tsonga"},
                                              :person/friends                                         #:db{:id          77,
                                                                                                           :ident       :person/friends,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "Person's friends' UUIDs"},
                                              :lang/sg                                                #:db{:id          473,
                                                                                                           :ident       :lang/sg,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sango"},
                                              :lang/pa                                                #:db{:id          457,
                                                                                                           :ident       :lang/pa,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Panjabi"},
                                              :comment/created-at                                     #:db{:id          610,
                                                                                                           :ident       :comment/created-at,
                                                                                                           :valueType   #:db{:id 25, :ident :db.type/instant},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Comment's creation date."},
                                              :person/email                                           #:db{:id          82,
                                                                                                           :ident       :person/email,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 37, :ident :db.unique/value},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's email address"},
                                              :lang/lo                                                #:db{:id          427,
                                                                                                           :ident       :lang/lo,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Lao"},
                                              :org/uuid                                               #:db{:id          531,
                                                                                                           :ident       :org/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "An organisation's uuid"},
                                              :lang/ch                                                #:db{:id          359,
                                                                                                           :ident       :lang/ch,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Chamorro"},
                                              :learn-map/obsolete-difficulty                          #:db{:id          579,
                                                                                                           :ident       :learn-map/obsolete-difficulty,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one}},
                                              :person/friend                                          #:db{:id          76,
                                                                                                           :ident       :person/friend,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Person's friends' UUIDs"},
                                              :coc/license-count                                      #:db{:id          96,
                                                                                                           :ident       :coc/license-count,
                                                                                                           :valueType   #:db{:id 22, :ident :db.type/long},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The number of licenses a manager has to dispense"},
                                              :text/lang                                              #:db{:id          127,
                                                                                                           :ident       :text/lang,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "An text's language code"},
                                              :coc/managee                                            #:db{:id          94,
                                                                                                           :ident       :coc/managee,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The person being managed"},
                                              :concept/key                                            #:db{:id          130,
                                                                                                           :ident       :concept/key,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "An concept's keyword"},
                                              :deprecated/kw                                          #:db{:id          170,
                                                                                                           :ident       :deprecated/kw,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Cornish"},
                                              :db.install/attribute                                   #:db{:id          13,
                                                                                                           :ident       :db.install/attribute,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :doc         "System attribute with type :db.type/ref. Asserting this attribute on :db.part/db with value v will install v as an attribute."},
                                              :lang/kn                                                #:db{:id          409,
                                                                                                           :ident       :lang/kn,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Kannada"},
                                              :deprecated/as                                          #:db{:id          148,
                                                                                                           :ident       :deprecated/as,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Assamese"},
                                              :db.type/fn                                             {:db/id        26,
                                                                                                       :db/ident     :db.type/fn,
                                                                                                       :fressian/tag :datomic/fn,
                                                                                                       :db/doc       "Value type for database functions. See Javadoc for Peer.function."},
                                              :db.cardinality/one                                     #:db{:id    35,
                                                                                                           :ident :db.cardinality/one,
                                                                                                           :doc   "One of two legal values for the :db/cardinality attribute. Specify :db.cardinality/one for single-valued attributes, and :db.cardinality/many for many-valued attributes."},
                                              :org/short-name                                         #:db{:id          533,
                                                                                                           :ident       :org/short-name,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "An organisation's short name (used for URLs)"},
                                              :db.excise/attrs                                        #:db{:id          16,
                                                                                                           :ident       :db.excise/attrs,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many}},
                                              :deprecated/ty                                          #:db{:id          307,
                                                                                                           :ident       :deprecated/ty,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tahitian"},
                                              :deprecated/or                                          #:db{:id          261,
                                                                                                           :ident       :deprecated/or,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Oriya"},
                                              :time.start/ms                                          #:db{:id          575,
                                                                                                           :ident       :time.start/ms,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Start time, in milliseconds"},
                                              :deprecated/gv                                          #:db{:id          237,
                                                                                                           :ident       :deprecated/gv,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Manx"},
                                              :question/answer-type                                   #:db{:id          637,
                                                                                                           :ident       :question/answer-type,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The answer type of a question. Indicates which way is meant to be used to assess a person"},
                                              :note/author                                            #:db{:id          135,
                                                                                                           :ident       :note/author,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "The person who authored the note"},
                                              :lang/su                                                #:db{:id          483,
                                                                                                           :ident       :lang/su,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sundanese"},
                                              :lang/it                                                #:db{:id          404,
                                                                                                           :ident       :lang/it,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Italian"},
                                              :feedback/reason                                        #:db{:id          593,
                                                                                                           :ident       :feedback/reason,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 36, :ident :db.cardinality/many},
                                                                                                           :isComponent true,
                                                                                                           :doc         "A reference to an entity that represents the reason given for the feedback."},
                                              :lang/ht                                                #:db{:id          389,
                                                                                                           :ident       :lang/ht,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Haitian"},
                                              :deprecated/ca                                          #:db{:id          164,
                                                                                                           :ident       :deprecated/ca,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Catalan"},
                                              :deprecated/am                                          #:db{:id          144,
                                                                                                           :ident       :deprecated/am,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Amharic"},
                                              :deprecated/ro                                          #:db{:id          272,
                                                                                                           :ident       :deprecated/ro,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Romanian"},
                                              :product/title                                          #:db{:id          520,
                                                                                                           :ident       :product/title,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A product's name"},
                                              :deprecated/ia                                          #:db{:id          202,
                                                                                                           :ident       :deprecated/ia,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Interlingua"},
                                              :deprecated/hr                                          #:db{:id          173,
                                                                                                           :ident       :deprecated/hr,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Croatian"},
                                              :person/previous-qualifications                         #:db{:id          89,
                                                                                                           :ident       :person/previous-qualifications,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :noHistory   true,
                                                                                                           :doc         "Person's previous qualifications"},
                                              :fressian/tag                                           #:db{:id          39,
                                                                                                           :ident       :fressian/tag,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Keyword-valued attribute of a value type that specifies the underlying fressian type used for serialization."},
                                              :deprecated/io                                          #:db{:id          208,
                                                                                                           :ident       :deprecated/io,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ido"},
                                              :lang/de                                                #:db{:id          385,
                                                                                                           :ident       :lang/de,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "German"},
                                              :deprecated/nn                                          #:db{:id          253,
                                                                                                           :ident       :deprecated/nn,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Norwegian Nynorsk"},
                                              :deprecated/ko                                          #:db{:id          225,
                                                                                                           :ident       :deprecated/ko,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Korean"},
                                              :deprecated/so                                          #:db{:id          286,
                                                                                                           :ident       :deprecated/so,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Somali"},
                                              :role/person                                            #:db{:id          101,
                                                                                                           :ident       :role/person,
                                                                                                           :valueType   #:db{:id 20, :ident :db.type/ref},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "A person ref"},
                                              :space/uuid                                             #:db{:id          97,
                                                                                                           :ident       :space/uuid,
                                                                                                           :valueType   #:db{:id 54, :ident :db.type/uuid},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :unique      #:db{:id 38, :ident :db.unique/identity},
                                                                                                           :doc         "space ID"},
                                              :lang/sl                                                #:db{:id          479,
                                                                                                           :ident       :lang/sl,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Slovenian"},
                                              :deprecated/te                                          #:db{:id          294,
                                                                                                           :ident       :deprecated/te,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Telugu"},
                                              :password/hash                                          #:db{:id          92,
                                                                                                           :ident       :password/hash,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Hashed password value"},
                                              :lang/af                                                #:db{:id          335,
                                                                                                           :ident       :lang/af,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Afrikaans"},
                                              :time/s                                                 #:db{:id          331,
                                                                                                           :ident       :time/s,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "second"},
                                              :deprecated/tn                                          #:db{:id          301,
                                                                                                           :ident       :deprecated/tn,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Tswana"},
                                              :deprecated/oc                                          #:db{:id          257,
                                                                                                           :ident       :deprecated/oc,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Occitan"},
                                              :current/A                                              #:db{:id          332,
                                                                                                           :ident       :current/A,
                                                                                                           :valueType   #:db{:id 62, :ident :db.type/bigdec},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Ampere"},
                                              :lang/sa                                                #:db{:id          468,
                                                                                                           :ident       :lang/sa,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Sanskrit"},
                                              :deprecated/la                                          #:db{:id          228,
                                                                                                           :ident       :deprecated/la,
                                                                                                           :valueType   #:db{:id 21, :ident :db.type/keyword},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Latin"},
                                              :lang/li                                                #:db{:id          425,
                                                                                                           :ident       :lang/li,
                                                                                                           :valueType   #:db{:id 23, :ident :db.type/string},
                                                                                                           :cardinality #:db{:id 35, :ident :db.cardinality/one},
                                                                                                           :doc         "Limburgan"}})}}))