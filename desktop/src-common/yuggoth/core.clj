(ns yuggoth.core
  (:require [play-clj.core :refer :all]
            [play-clj.ui :refer :all]
            [play-clj.g2d :refer :all]
            [yuggoth.sprite :refer :all]))

(defonce yuggoth-asset-manager (asset-manager))
(set-asset-manager! yuggoth-asset-manager)

(declare yuggoth main-screen)

(defn on-show
  [screen entities]
  (update! screen :renderer (stage))
  (let [spritesheet (load-spritesheet "spaceman_sheet_hires.png" :cell-width 64 :cell-height 96)
        ;_ spritesheet
        ]
    [(label "Hello world!" (color :white))

     ;(assoc (spritesheet [0 1]) :x 0 :y 0)
     (assoc(spritesheet [0 2]) :x 0 :y 0)
     ]))

(defscreen main-screen
  :on-show
  on-show

  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen entities)))

(defgame yuggoth
  :on-create
  (fn [this]
    (set-screen! this main-screen)))

(comment
  (app! :post-runnable #(set-screen! yuggoth main-screen)))
