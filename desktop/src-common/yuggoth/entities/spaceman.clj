(ns yuggoth.entities.spaceman
  (:require [yuggoth.sprite :refer :all]))


(def spaceman-sprite
  (sprite-spec :delay 0.5
               :filename "spaceman_sheet_hires.png"
               :cell-dimensions [64 96]
               :standing [ {:mode :loop} [0 0] ]))
