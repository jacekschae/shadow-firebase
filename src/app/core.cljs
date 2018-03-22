(ns app.core
  (:require [reagent.core :as reagent :refer [atom]]
            [app.views :as views]
            [app.fb-auth :as fb-auth]
            [app.fb-db :as fb-db]
            [app.state :as state]
            ["firebase" :as firebase :refer [initializeApp apps app]]))

;; == firebase-init ===========================================================
;; Initialize default app. Retrieve your own options values by adding a web app
;; on https://console.firebase.google.com
;;
;; usage: (firebase-init))
;;
(defn firebase-init
  []
  (if-not (and apps (< 0 (alength apps))) ;; https://github.com/zeit/next.js/issues/1999
    (initializeApp
     #js {:apiKey ""               ;; Auth / General
          :authDomain ""           ;; Auth with popup
          :databaseURL ""          ;; Realtime Database
          :projectId ""            ;; Unique ProjectID
          :storageBucket ""        ;; Storage
          :messagingSenderId ""})  ;; Cloud Messaging
    (app))
  (fb-auth/on-auth-state-changed state/user))

(defn ^:export main []
  (reagent/render-component [views/app]
                            (.getElementById js/document "app"))
  (firebase-init)
  (fb-db/subscribe-to-counter))
