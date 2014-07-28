(ns yuggoth.sprite
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]))


(defn load-spritesheet
  "Splits a texture into multiple rectangular cells.
  Returns a spritesheet indexed by [row, column] pairs,
  with extra keys including:
  :dimensions [width, height]
  :width
  :height
  :cell-dimensions [cell-width, cell-height]
  :cell-width
  :cell-height
  :texture
  "
  [filename &{:keys [cell-width cell-height]}]
  {:pre [filename cell-width cell-height]}

  (let [tex (texture filename)
        [tw th] [(texture! tex :getRegionWidth)
                 (texture! tex :getRegionHeight)]
        [cw ch] [cell-width cell-height]
        [nw nh] [(int (/ tw cw)) (int (/ th ch))]
        cells (doall (for [x (range nw)
                           y (range nh)]
                       [[y x]
                        (texture tex
                                 :set-region
                                 (* cw x) (* cw y)
                                 (* cw (inc x)) (* cw (inc y)))]))]
    (into {:dimensions [nw nh]
           :width nw
           :height nh
           :cell-dimensions [cw ch]
           :cell-width cw
           :cell-height ch
           :texture tex}
          cells)))

;; Note: to create a renderable entity, something like {:object #Texture} needs to be present

(defn load-sprite
  "Loads the textures associated with the sprite entity."
  [spec]
  )


