(ns yuggoth.core
  (:require [play-clj.core :refer :all]
            [play-clj.ui :refer :all]
            [play-clj.g2d :refer :all]
            [yuggoth.sprite :refer :all]))

(def ^:dynamic *yuggoth-debug* true)

;; First things first, the asset manager must be set up
(defonce manager (asset-manager))
(set-asset-manager! manager)

;; Forward declarations
(declare yuggoth main-screen)

(defscreen main-screen
  :on-show
  (fn [screen entities]
    (let [ss (load-spritesheet "spaceman_sheet_hires.png" :cell-width 64 :cell-height 96)]

      (update! screen :renderer (stage))
      [(label "Hello yuggoth!" (color :white))

       (assoc (ss [0 0])
        :something-else 0)]))

  :on-render
  (fn [screen entities]
    (clear!)
    (render! screen entities))

  :on-key-down
  (fn [screen entities]
    (if (and *yuggoth-debug* (= (:key screen) (key-code :F5)))
      (app! :post-runnable #(set-screen! yuggoth main-screen)))
      entities)
  )

(defgame yuggoth
  :on-create
  (fn [this]
    (set-screen! this main-screen)))


(comment
  (key-code :F5))

