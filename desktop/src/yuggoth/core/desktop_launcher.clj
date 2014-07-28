(ns yuggoth.core.desktop-launcher
  (:require [yuggoth.core :refer :all])
  (:import [com.badlogic.gdx.backends.lwjgl LwjglApplication]
           [org.lwjgl.input Keyboard])
  (:gen-class))

(defn -main
  []
  (LwjglApplication. yuggoth "yuggoth" 800 600)
  (Keyboard/enableRepeatEvents true))
