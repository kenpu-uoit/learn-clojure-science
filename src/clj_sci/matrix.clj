(ns clj-sci.matrix
  (:import (org.jblas 
             FloatMatrix
             Singular)))

(defn rand-matrix [M N]
  ; build a M X N matrix
  (FloatMatrix. 
    (into-array 
      (for [i (range M)] 
        (float-array (take N (repeatedly rand)))))))

(defn svd [x]
    (Singular/fullSVD x))

