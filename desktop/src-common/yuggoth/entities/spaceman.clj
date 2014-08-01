(ns yuggoth.entities.spaceman
  (:require [yuggoth.sprite :refer :all]
            [yuggoth.physics-util :refer :all]
            [yuggoth.blang :refer :all]))


(def spaceman-sprite
  (sprite-spec :delay 0.5
               :filename "spaceman_sheet_hires.png"
               :cell-dimensions [64 96]
               :standing [ {:mode :loop} [0 0] ]))

(defn spaceman-controller
  []
  ;; TODO: Do a better thing with blang!
  ;; i.e. better radii for circles
  (fix {:transforms [
                     (translate (v2 0 3))
                     ]
        :shape (rect :width 64 :height 93 :align :bottom)}
       (fix {:transforms [(translate (v2 29 0))
                          (scale 3)]
             :shape (circ)})
       (fix {:transforms [(translate (v2 -29 0))
                          (scale 3)]
             :shape (circ)})
       ))


(defn test-shape []
  (fix {:transforms [(rotate 29)
                        (scale 20)]
           :shape (circ :align :bottom)}
          (fix {:transforms [(rotate -90)
                                (translate (v2 0,0))]
                   :shape (rect :width 3 :align :left)}

                  (fix {:transforms [(translate (v2 0,0.2))
                                        (rotate 45)
                                        (scale 0.5)]
                           :shape (rect :width 5 :align :left)})
                  (fix {:transforms [(translate (v2 0,-0.2))
                                        (rotate -45)
                                        (scale 0.5)]
                           :shape (rect :width 5 :align :left)})
                  (fix {:transforms [(translate (v2 3,0.2))
                                        (rotate 30)
                                        (scale 0.5)]
                           :shape (rect :width 6 :align :left)})
                  (fix {:transforms [(translate (v2 3,-0.2))
                                        (rotate -30)
                                        (scale 0.5)]
                           :shape (rect :width 6 :align :left)}))))

(comment
  (test-shape))
