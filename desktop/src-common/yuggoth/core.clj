(ns yuggoth.core
  (:require [play-clj.core :refer :all]
            [play-clj.ui :refer :all]
            [play-clj.g2d :refer :all]
            [play-clj.g2d-physics :refer :all]
            [yuggoth.sprite :refer :all]
            [yuggoth.physics-util :refer :all]
            [yuggoth.blang :as bl]
            [yuggoth.entities.spaceman :as ents]))

(def ^:dynamic *yuggoth-debug* true)

;; First things first, the asset manager must be set up
(defonce manager (asset-manager))
(set-asset-manager! manager)

;; Forward declarations
(declare yuggoth main-screen)

(defn on-show
  [screen entities]

  (let [screen (update! screen :renderer (stage)
                        :world (box-2d 0 0))
        sprite (load-sprite ents/spaceman-sprite )
        _ (println sprite)
        bdy (test-body screen 15)
        bdy2 (add-body! screen (body-def :dynamic))
        _ (. bdy2 (setFixedRotation true))
        _ (body-x! bdy2 320)
        _ (body-y! bdy2 480)
        _ (bl/create-fixtures bdy2 (ents/spaceman-controller))
        ]

    [(physics-renderer)
     (label "Hello world!" (color :white))
     sprite
     bdy
     {:body bdy2}
     ]))

(defscreen main-screen
  :on-show
  on-show

  :on-render
  (fn [screen entities]
    (step! screen)
    (clear!)

    (render! screen entities)
    (sys-render-physics screen entities))

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
