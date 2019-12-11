(ns archo.ui.navbar)

(defn main []
  (fn []
    [:nav.navbar.bg-dark.navbar-dark ;.navbar-toggleable-md ;.navbar-inverse.fixed-top.bg-inverse
     [:div.container
      #_[:button.navbar-toggler.navbar-toggler-right.align-self-center.my-2 {:type "button" :data-toggle "collapse" :data-target "#navbarCollapse"}
       [:span.navbar-toggler-icon]]
      ;[:h1.pb-2.ml-lg-2.mx-3 [:a {:href "#"} [:i.fa.fa-envelope-o.fa-lg.mt-2 {:aria-hidden "true"}]]]
      [:div.flex-column ;.ml-lg-0.ml-3 ;.#navbarCollapse.collapse.navbar-collapse.
       [:ul.navbar-nav
        [:li.nav-item.active
         [:a.nav-link {:href "#"} "Home"]]
        [:li.nav-item
         [:a.nav-link {:href "#"} "Product"]]
        [:li.nav-item
         [:a.nav-link {:href "#"} "Shop"]]
        [:li.nav-item
         [:a.nav-link {:href "#"} "About"]]
        [:li.nav-item
         [:a.nav-link {:href "#"} "Events"]]]
       [:ul.navbar-nav.flex-row.mb-2
        [:li.nav-item
         [:a.nav-link.py-0.pr-3 {:href "#"} [:i.fa.fa-facebook]]]
        [:li.nav-item
         [:a.nav-link.py-0.pr-3 {:href "#"} [:i.fa.fa-instagram]]]
        [:li.nav-item
         [:a.nav-link.py-0.pr-3 {:href "#"} [:i.fa.fa-twitter]]]]]]]))




(defn main2 []
  (fn []
    [:<>
     [:nav.navbar.navbar-expand-md.bg-dark.navbar-dark
      [:div.container
       [:h4.display-title.d-flex.align-items-center
        [:i.fas.fa-home-lg-alt]
        [:i.fal.fa-chevron-right.mx-2]
        "The Course Thanks"]
       ]]
     [:nav.navbar.navbar-expand-md.bg-dark.navbar-dark.shadow
      [:div.container
       [:button.navbar-toggler {:type "button" :data-toggle "collapse" :data-target "#collapsibleNavbar"}
        [:span.navbar-toggler-icon]]
       [:div#collapsibleNavbar.collapse.navbar-collapse
        [:ul.navbar-nav
         [:li.nav-item
          [:a.nav-link {:href "#"} [:i.fas.fa-archive.mr-2] "Files"]]]]]]]))