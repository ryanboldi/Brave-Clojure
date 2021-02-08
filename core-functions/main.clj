(ns main)

(defn -main
  [& args]
  (println "Hello world!"))

;Implement map using reduce
(defn my_map
  "implements the map function using reduce"
  [func sequence]
  (seq (reduce (fn [done left]
                 (into done [(func left)]))
               []
               (seq sequence))))

(my_map inc [1 2 3]) ; => (2 3 4)

;implement filter using reduce
(defn my_filter
  "implements the filter function using reduce"
  [predicate_func sequence]
  (seq (reduce (fn [good left]
                 (if (predicate_func left)
                   (into good [left])
                   good))
               []
               (seq sequence))))

(my_filter #(> % 3) [1 2 9 3 10 54]) ; => (9 10 54)

;implement some using reduce
(defn my_some
  "implements some using reduce"
  [predicate_func sequence]
  (some identity (reduce (fn [good left]
                   (into good [(predicate_func left)]))
               []
               (seq sequence))))

(my_some #(> % 3) [1 2 9 3 10 54]) ; => true
(my_some #(and (> % 3) %) [1 2 9 3 10 54]) ; => 9


(def suspect_file "suspects.csv")
(slurp suspect_file)