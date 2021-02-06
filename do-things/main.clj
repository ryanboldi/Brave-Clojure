(ns main)

(defn -main
  [& args]
  (println "Hello world!"))

;exercise 2
(def add_100 #(+ % 100))

;exercise 3
(defn dec-maker
  "creates a custom decrementor"
  [decrement]
  #(- % decrement))

(def dec9 (dec-maker 9))

(dec9 10)

;exercise 4