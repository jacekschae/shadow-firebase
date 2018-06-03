(ns app.views
  (:require [app.state :as state]
            [app.events :refer [increment decrement reset]]
            [app.fb.auth :as fb-auth]))

(defn header
  []
  [:div.banner
   [:h1 "shadow-cljs + firebase"]
   (if (empty? @state/user)
     [:p "First you need to log-in ..."]
     [:p "... so taht you can change the state."])])

(defn counter
  []
  [:div.jumbotron
   (if (empty? @state/user)
     [:div.banner
      [:p
       [:button.btn {:on-click #(fb-auth/sign-in-with-google)} "Login"]]]
     [:div.banner
      [:p
       [:button.btn {:on-click #(fb-auth/sign-out)} "Logout"]]
      [:button.btn {:on-click #(decrement)} "\u2212"]
      [:button.btn {:on-click #(reset)} @state/counter]
      [:button.btn {:on-click #(increment)} "\u002B"]
      (when-not (= @state/counter 0)
        [:p [:button.btn {:on-click #(reset)} "reset"]])])])

(defn app []
  [:div
   [header]
   [counter]])
