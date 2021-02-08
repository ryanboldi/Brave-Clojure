(ns main)

(defn -main
  [& args]
  (println "Hello world!"))

;Implement map using reduce

(defn my_map
  "implements the map function using reduce"
  [func sequence]
  (reduce (fn [done left]
            (println done left)
            (into done `(func left)))
          []
          sequence))

(my-map inc [1 2 3])