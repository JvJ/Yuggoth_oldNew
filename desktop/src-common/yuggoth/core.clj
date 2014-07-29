(ns yuggoth.core
  (:require [play-clj.core :refer :all]
            [play-clj.ui :refer :all]
            [play-clj.g2d :refer :all]
            [yuggoth.sprite :refer :all]
            [yuggoth.entities.spaceman :as ents]))

(defonce yuggoth-asset-manager (asset-manager))
(set-asset-manager! yuggoth-asset-manager)

(declare yuggoth main-screen)

(defn on-show
  [screen entities]
  (update! screen :renderer (stage))
  (let [sprite (load-sprite ents/spaceman-sprite )
        _ (println sprite)]
    [(label "Hello world!" (color :white))
     sprite
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
