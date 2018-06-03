(ns app.events
  (:require [app.state :as state]
            [app.fb.db :as fb-db]))

(defn increment
  []
  (fb-db/save! ["counter"] (swap! state/counter inc)))

(defn decrement
  []
  (fb-db/save! ["counter"] (swap! state/counter dec)))

(defn reset
  []
  (fb-db/save! ["counter"] (reset! state/counter 0)))
