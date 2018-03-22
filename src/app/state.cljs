(ns app.state
  (:require [reagent.core :refer [atom]]))

(defonce counter (atom 0))

(defonce user (atom {}))
