(ns archo.ui.navbar)

(defn main []
  (fn []
    [:nav.navbar.navbar-expand-md.navbar-dark.bg-dark.shadow ;.fixed-top
     [:div.container.d-flex
      [:span.pb-2.ml-md-0.ml-sm-3.ml-0.mr-3.display-title [:i.fa.fa-envelope-o.fa-lg {:aria-hidden "true"}]]
      [:button.navbar-toggler.my-2 {:type "button" :data-toggle "collapse" :data-target "#navbarCollapse"}
       [:span.navbar-toggler-icon]]

      [:div#navbarCollapse.collapse.navbar-collapse.flex-column.align-items-start.ml-lg-2.ml-0
       #_[:ul.navbar-nav
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
       #_[:ul.navbar-nav
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
       [:ul.navbar-nav ;.flex-row.mt-4 ;.mb-md-1.mt-md-0.mb-3.mt-2
        [:li.nav-item
         [:a.nav-link.py-0.pr-3 {:href "#"} [:i.fa.fa-chart-pie-alt.mr-2] [:span "Coverage"]]]
        [:li.nav-item
         [:a.nav-link.py-0.pr-3 {:href "#"} [:i.fa.fa-archive.mr-2] [:span "Files"]]]
        [:li.nav-item
         [:a.nav-link.py-0.pr-3 {:href "#"} ]]]]]]))




(defn main2 []
  (fn []
    [:nav.navbar.navbar-expand-md.navbar-dark.bg-dark.shadow ;.fixed-top
     [:div.container
      [:h1.pb-2.ml-md-0.ml-sm-3.ml-0.mr-3 [:a {:href "#"} [:i.fa.fa-envelope-o.fa-lg.mt-3 {:aria-hidden "true"}]]]
      [:button.navbar-toggler.my-2 {:type "button" :data-toggle "collapse" :data-target "#navbarCollapse"}
       [:span.navbar-toggler-icon]]
      [:div#navbarCollapse.collapse.navbar-collapse.flex-column.align-items-start.ml-lg-2.ml-0
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
       [:ul.navbar-nav.flex-row.mb-md-1.mt-md-0.mb-3.mt-2
        [:li.nav-item
         [:a.nav-link.py-0.pr-3 {:href "#"} [:i.fa.fa-facebook]]]
        [:li.nav-item
         [:a.nav-link.py-0.pr-3 {:href "#"} [:i.fa.fa-instagram]]]
        [:li.nav-item
         [:a.nav-link.py-0.pr-3 {:href "#"} [:i.fa.fa-twitter]]]]]]]))