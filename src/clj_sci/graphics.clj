(ns clj-sci.graphics
  "Playin with simple 2D graphics"
  (:import (javax.swing JFrame JLabel)
           (java.awt 
             Color
             Graphics 
             Graphics2D 
             BorderLayout 
             Dimension 
             RenderingHints)))


(defn line-frame [f]
  (proxy [JFrame] []
    (paint [g]
      (let [g2 (cast Graphics2D g)
            rh (RenderingHints.
                 RenderingHints/KEY_ANTIALIASING
                 RenderingHints/VALUE_ANTIALIAS_ON)]
        (.setRenderingHints g2 rh)
        (f g2)))))

(defn build-frame [fun]
  (let [f (line-frame fun)]
    (doto f
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize 400 400))
      f))
(defn rand-paint []
  (let [r (float (+ 0.5 (rand 0.5)))
        g (float (+ 0.5 (rand 0.5)))
        b (float (rand))
        a (float (rand 0.4))]
    (Color. r g b a)))

(defn main 
  []
  (let [f (fn [g]
            (dorun (for [i (range 1000)]
              (let [x (rand-int 400)
                    y (rand-int 400)
                    r (rand-int 50)
                    p (rand-paint)]
                (doto g
                  (.setPaint p)
                  (.fillOval x y r r))))))]
    (.setVisible (build-frame f) true)))

(defn -main [& args]
  (time (main)))

