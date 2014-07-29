(ns yuggoth.sprite
  (:require [play-clj.core :refer :all]
            [play-clj.g2d :refer :all]))

(def ^:dynamic *global-texture-cache*
  "This variable holds the global cache of all textures that havebeen loaded.
  Indexed by filename."
  (atom {}))

(def ^:dynamic *global-spritesheet-cache*
  "This variable holds all loaded spritesheets.
  Indexed by [filename [cell-width cell-height]]"
  (atom {}))

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
  ;; TODO: replace this with cleaner exception?
  {:pre [filename cell-width cell-height]}

  ;; Returns the global var OR something we construct
  (or (@*global-spritesheet-cache* [filename [cell-width cell-height]])
      (let [ss (let [tex (or (@*global-texture-cache* filename)
                        (let [t (texture filename)
                              _ (swap! *global-texture-cache* assoc filename t)]
                          t))

                [tw th] [(texture! tex :getRegionWidth)
                         (texture! tex :getRegionHeight)]
                [cw ch] [cell-width cell-height]
                [nw nh] [(int (/ tw cw)) (int (/ th ch))]
                cells (doall (for [x (range nw)
                                   y (range nh)]
                               [[y x]
                                (texture tex
                                         :set-region
                                         (* cw x) (* ch y)
                                         cw ch)]))]
            (into {:dimensions [nw nh]
                   :width nw
                   :height nh
                   :cell-dimensions [cw ch]
                   :cell-width cw
                   :cell-height ch
                   :texture tex}
                  cells))
          _ (swap! *global-spritesheet-cache* assoc [filename [cell-width cell-height]] ss)]
        ss)))


(defn sprite-spec
  [&{:keys [delay
            filename]
     [cell-width cell-height :as cell-dimensions] :cell-dimensions
     ;; Default values
     :or {delay 0.1}
     :as m}]
  ;; Preconditions require cell dimensions
  {:pre [delay, filename, cell-dimensions, cell-width, cell-height]}

  (let [states (dissoc m :delay :cell-dimensions)]
    {}
    ))

;; Note: to create a renderable entity, something like {:object #Texture} needs to be present
(defn load-sprite
  "Loads the textures associated with the sprite entity."
  [spec]
  )

