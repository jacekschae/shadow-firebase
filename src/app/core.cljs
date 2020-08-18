(ns app.core
  (:require [reagent.dom :as rdom]
            [app.views :as views]
            [app.fb.init :refer [firebase-init]]
            [app.fb.db :as fb-db]))

(defn ^:export main []
  (rdom/render [views/app] (.getElementById js/document "app"))
  (firebase-init)
  (fb-db/db-subscribe ["counter"]))
