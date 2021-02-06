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

;exercise 4
(defn mapset
  [func [& rest]]
  (set (map func rest)))

;exercise 5 & 6
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

;exercise 6

(defn matching-part
  [part name]
  {:name (clojure.string/replace (:name part) #"^left-" (str name "-"))
   :size (:size part)})

(defn matching-parts
  [part number]
  (loop [index 0 result []]
    (if (= index number)
      result
      (recur (inc index) (into result (matching-part part index))))))

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts number]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set (into (matching-parts part number) part))))))))


(symmetrize-body-parts asym-hobbit-body-parts 3)`