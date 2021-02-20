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
  (loop [index -1 correct infixed]
    (println correct)
    (if (= (count correct) 1)
      (first correct)
      (if (= '* (nth correct (inc index)))
        (recur -1 (into (reverse (take index correct)) (into (list (list (nth correct (inc index))
                                                                         (nth correct index)
                                                                         (nth correct (inc (inc index))))) (drop (+ index 3) correct))))
        (if (and (not (some #{'*} correct)) (or (= '+ (nth correct (inc index))) (= '- (nth infixed (inc index)))))
          (recur -1 (into (take index correct) (into (list (list (nth correct (inc index))
                                                                 (nth correct index)
                                                                 (nth correct (inc (inc index))))) (drop (+ index 3) correct))))
          (recur (inc index) correct))))))

(infix (1 * 4 + 1 * 5))