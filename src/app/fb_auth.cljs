(ns app.fb-auth
  (:require ["firebase" :as firebase :refer [auth]]
            [clojure.string :as string]
            [app.state :as state]))

;; == sign-in-with-google =====================================================
;; Authenticate with Firebase using the Google provider object. You can prompt
;; your users to sign in with their Google Accounts by opening a pop-up window
;;
(defn sign-in-with-google
  []
  (let [fb firebase
        auth (fb.auth.)
        provider (fb.auth.GoogleAuthProvider.)]
    (.catch (.signInWithPopup auth provider)
            (fn [error]
              (throw error)))))

;; == sign-out ================================================================
;; Signs out the current user and clears it from the disk cache
;;
(defn sign-out
  []
  (let [fb firebase
        auth (fb.auth.)]
    (do
      (.signOut auth)
      (reset! state/user nil))))

;; == on-auth-state-change ====================================================
;; The recommended way to get the current user is by setting an observer on the
;; Auth object. By using an observer, you ensure that the Auth object isn't in
;; an intermediate state—such as initialization—when you get the current user.
;;
(defn on-auth-state-changed
  [user-atom]
  (.onAuthStateChanged
   (auth)
   (fn auth-state-changed
     [user-obj]
     (when user-obj
       (let [uid (.-uid user-obj)
             display-name (.-displayName user-obj)
             photo-url (.-photoURL user-obj)
             email (.-email user-obj)]
         (when uid
           (reset! user-atom {:photo-url photo-url
                              :display-name display-name
                              :uid uid
                              :email email})))))

   (fn auth-error
     [error]
     (throw error))))
