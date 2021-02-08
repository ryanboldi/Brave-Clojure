(ns main)

(defn -main
  [& args]
  (println "Hello world!"))

;Implement map using reduce

(defn my_map
  "implements the map function using reduce"
  [func sequence]
  (reduce (fn [done left]
            (into done (func (take 1 left))))
          []
          (seq sequence)))

(my_map inc [1 2 3 4 5])