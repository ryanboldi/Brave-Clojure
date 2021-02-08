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

(my_map inc [1 2 3])