(ns app.fb.auth
  (:require ["firebase/app" :as firebase]
            [app.state :as state]))

;; == sign-in-with-google =====================================================
;; Authenticate with Firebase using the Google provider object. You can prompt
;; your users to sign in with their Google Accounts by opening a pop-up window
;;
(defn sign-in-with-google
  []
  (let [provider (firebase/auth.GoogleAuthProvider.)]
    (.signInWithPopup (firebase/auth) provider)))

;; == sign-out ================================================================
;; Signs out the current user and clears it from the disk cache
;;
(defn sign-out
  []
  (.signOut (firebase/auth)))

;; == on-auth-state-change ====================================================
;; The recommended way to get the current user is by setting an observer on the
;; Auth object. By using an observer, you ensure that the Auth object isn't in
;; an intermediate state—such as initialization—when you get the current user.
;;
(defn on-auth-state-changed
  []
  (.onAuthStateChanged
   (firebase/auth)
   (fn
     [user]
     (if user
       (let [uid (.-uid user)
             display-name (.-displayName user)
             photo-url (.-photoURL user)
             email (.-email user)]
         (reset! state/user {:photo-url photo-url
                             :display-name display-name
                             :email email
                             :uid uid}))
       (reset! state/user nil)))))
