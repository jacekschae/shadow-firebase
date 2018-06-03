(ns app.fb.db
  (:require ["firebase/app" :refer [database]]
            [clojure.string :as str]
            [app.state :as state]))

;; == db-ref ==================================================================
;; Represents a specific location in your Database and can be used for reading
;; or writing data to the Database location, path is a vector of strings or 
;; symbols.
;;
;; usage: (fb-db/db-ref ["counter"]))))
;;
(defn db-ref
  [path]
  (.ref (database) (str/join "/" path)))

;; == save! ===================================================================
;; Writes data to firebase path location. This will overwrite any data at this
;; location and all child locations. Passing nil for the new value is
;; equivalent to calling remove()
;;
;; usage: (fb-db/save! ["counter"] 0)))
;;
(defn save!
  [path value]
  (.set (db-ref path) value))

;; == subscribe-to-counter ====================================================
;; Listens for data changes at the "counter" location.
;;
;; usage: (db-subscribe ["counter"])
;;
(defn db-subscribe
  [path]
  (.on (db-ref path)
       "value"
       (fn [snapshot]
         (reset! state/counter (js->clj (.val snapshot) :keywordize-keys true)))))
