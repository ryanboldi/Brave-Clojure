(ns main)

(defn -main
  [& args]
  (println "Hello world!"))

(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))


((two-comp inc +) 1 2 3 4)

;create comp function for any number of functions

(defn my-comp
  [f & others]
  (fn [& args]
    (println args)
    (f (apply (my-comp others) args))))

((my-comp +) 1 2 3 4)
((comp inc inc inc +) 1 2 3 4) ; => 13