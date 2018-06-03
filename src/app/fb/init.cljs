(ns app.fb.init
  (:require ["firebase/app" :as firebase]
            ["firebase/database"]
            ["firebase/auth"]
            [app.fb.auth :as fb-auth]))

;; == firebase-init ===========================================================
;; Initialize default app. Retrieve your own options values by adding a web app
;; on https://console.firebase.google.com
;;
;; usage: (firebase-init))
;;
(defn firebase-init
  []
  (if (zero? (alength firebase/apps))
    (firebase/initializeApp
     #js {:apiKey "your-api-key",
          :authDomain "your-auth-domain",
          :databaseURL "your-database-url",
          :projectId "your-project-id",
          :storageBucket "your-storage-bucket",
          :messagingSenderId "your-messaging-sender-id"})
    (firebase/app))
  (fb-auth/on-auth-state-changed))