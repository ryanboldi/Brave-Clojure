(ns main)

(defn -main
  [& args]
  (println "Hello world!"))

(defn index-of [item coll]
  (count (take-while (partial not= item) coll)))

;Exercise 1: list, read-string 

(def ryan-list (read-string "(do (println \"Ryan\") (println \"Transcendence\"))"))
(eval ryan-list)

; 2: infix to lisp notation following operator precedence rules
; Mult -> add -> subtract
(defmacro infix
  [infixed]
  (loop [index -1 correct '()]
    (println index (nth infixed (inc index)) correct)
    (if (= (inc index) (count infixed))
      correct
      (if (= '* (nth infixed (inc index)))
        (do (println "NICE") (into correct (list (nth infixed (inc (inc index)))
                                                 (nth infixed index)
                                                 (nth infixed (inc index)))))
        (if (or (= '+ (nth infixed (inc index))) (= '- (nth infixed (inc index))))
          (into correct (list (nth infixed (inc (inc index)))
                              (nth infixed (index))
                              (nth infixed (inc index))))
          (recur (inc index) correct))))))


(infix (1 * 2 + 4))

(nth '(1 + 2) 1)

