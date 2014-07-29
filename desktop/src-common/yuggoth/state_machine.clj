(ns ^{:author "JvJ"
      :doc "Build state machines for games.
      State machines are maps of states to update functions.
      Update functions take parameters: [self screen entities].
      The functions should return updated entities."}
  yuggoth.state-machine
  (:require [yuggoth.sprite :refer :all]))

(defmacro def-state-machine
  "dffd"
  [])
