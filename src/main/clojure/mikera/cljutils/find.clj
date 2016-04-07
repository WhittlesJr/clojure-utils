(ns mikera.cljutils.find
  "Namespace for utility functions that find values in collections")

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(defn indexed?
  ([coll]
    (instance? clojure.lang.Indexed coll))) 

(defn find-first 
   "Searches a collection and returns the first item for which pred is true, or nil if not found.
   
   Like 'clojure.core/some', except it returns the value from the collection (rather than the result of 
   applying the predicate to the value). This is often more useful.
   
   Note that it is possible to find and return a nil value if the collection contains nils."
  ([pred coll]
    (if (indexed? coll)
      (let [c (count coll)
            ^clojure.lang.Indexed icoll coll]
        (loop [i 0]
          (if (< i c) 
            (let [v (.nth icoll i)]
              (if (pred v) v (recur (inc i))))
            nil)))
      (loop [s (seq coll)] 
        (when s  
          (let [v (first s)]
            (if (pred v)
              v
              (recur (next s)))))))))

(defn find-index
  "Searches a collection and returns the index of the first item for which pred is true.
   
   Returns nil if not found"
  ([pred coll]
    (if (indexed? coll)
      (let [c (count coll)]
        (loop [i 0]
          (if (< i c) 
            (if (pred (nth coll i)) i (recur (inc i)))
            nil)))
      (loop [i 0 s (seq coll)]
        (if s
          (if (pred (first s)) i (recur (inc i) (next s)))
          nil))))) 

(defn find-position 
  "Searches a collection and returns the (long) index of the item's position.
   Optionally starts counting from i. 

   Returns nil if not found"
  ([item coll] 
    (find-position item coll 0))
  ([item coll ^long i] 
    (if-let [coll (seq coll)] 
      (let [v (first coll)]
	      (if (= item v)
	        i
	        (recur item (rest coll) (inc i))))
      nil)))

(defn find-position-in-vector
  "Searches an indexed data structure for an item and returns the index, or nil if not found."
  [item vector]
  (let [c (count vector)]
    (loop [i (int 0)]
      (if (>= i c)
        nil
        (if (= item (nth vector i)) i (recur (inc i)))))))
