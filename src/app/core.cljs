(ns app.core
  (:require [reagent.core :as reagent :refer [atom]]
            [app.views :as views]
            [app.fb.init :refer [firebase-init]]
            [app.fb.db :as fb-db]))

(defn ^:export main []
  (reagent/render-component [views/app]
                            (.getElementById js/document "app"))
  (firebase-init)
  (fb-db/db-subscribe ["counter"]))
