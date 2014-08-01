(ns yuggoth.physics-util
  (:require [play-clj.g2d-physics :refer :all])
  (:import
   (com.badlogic.gdx.math Vector2
                          Vector3)
   (com.badlogic.gdx.physics.box2d Box2DDebugRenderer)))

;;;; Vector operations
;;;; TODO: Multimethod-ify these for Vector2's
(defn v2
  [x y]
  (Vector2. (float x) (float y)))

(defn v3
  [x y z]
  (Vector3. (float x) (float y) (float z)))

(defn v2-to-floats
  "Convert 2d vectors to a float array."
  [vecs]
  (float-array
   (reduce #(conj %1 (. %2 x) (. %2 y))
           [] vecs)))

(defn v+
  [& r]
  (vec (apply map + r)))

(defn pv+
  "Partial vector add."
  [& r]
  (apply partial v+ r))

(defn v-
  [& r]
  (vec (apply map - r)))

(defn pv-
  "Partial vector minus.
 ((pv- a) b) is (v- a b)"
  [& r]
  (apply partial v- r))

(defn pv-sub
  "Partial vector subtract."
  [& r]
  (fn [a]
    (apply - a r)))

(defmulti lerp
  "Interpolate a factor of f between vectors a and b."
  (fn [& r] (map class r)))

(defmethod lerp
  [Number Vector2 Vector2]
  [f a b]
  (.. (Vector2. a)
      (lerp (float f))))

(defmethod lerp
  [Number Vector3 Vector3]
  [f a b]
  (.. (Vector3. a)
      (lerp (float f))))

(defmethod lerp
  :default
  [f a b]
  (if-not (= (count a) (count b))
    (throw (Exception. "Interpolate requires equal sized vectors.")))
  (->> (mapv (comp #(* f %) -) b a)
       (mapv + a)))


(defn test-body
  [screen rad]
  (let [b (add-body! screen (body-def :dynamic))]
    (->> (circle-shape rad)
             (fixture-def :density 1 :shape)
             (body! b :create-fixture))

    {:body b}
    ))

(defn sys-render-physics
  [screen entities]

  ;; Let statement for imperative junk
  (let [dr (:debug-renderer (first (filter :debug-renderer entities)))
        s (:renderer screen)
        w (:world screen)
        c (and s (.getCamera s))
        m (and c (. c combined))
        _ (and w m (.render dr w m))]
    entities))

(defn physics-renderer
  []
  {:debug-renderer (Box2DDebugRenderer.)})
