(ns app.fb-db
  (:require ["firebase" :as firebase :refer [database]]
            [clojure.string :as string]
            [app.state :as state]))

;; == db-ref ==================================================================
;; Represents a specific location in your Database and can be used for reading
;; or writing data to the Database location, path is a vector of strings
;;
;; usage: (fb-db/db-ref ["counter"]))))
;;
(defn db-ref
  [path]
  (.ref (database) (string/join "/" path)))

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
;; usage: (subscribe-to-counter)
;;
(defn subscribe-to-counter
  []
  (.on (db-ref ["counter"]) "value"
       (fn [snapshot]
         (when-let [d (.val snapshot)]
           (reset! state/counter d)))))
