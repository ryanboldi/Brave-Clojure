(ns main)

;EXERCISES
; 1: attr function that does the same as (comp :intelligence :attributes)

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(def attr #(comp % :attributes))

((attr :intelligence) character) ; => 10

;create comp function for any number of functions
; 2
(defn my-comp
  ([f]
   (fn [& args]
     (apply f args)))
  ([f & others]
   (fn [& args]
     (println args f others)
     (f (apply (apply my-comp others) args)))))

((my-comp inc inc inc +) 1 2 3 4) ; => 13
((comp inc inc inc +) 1 2 3 4) ; => 13


;3: implement the assoc-in function
(defn my-assoc-in
  [m [k & ks] v]
  (if (empty? ks)
    (assoc m k v)
    (assoc {} k (my-assoc-in m ks v))))

(assoc-in {} [:teams :team1 :leader :intelligence] 10) ; => {:teams {:team1 {:leader {:intelligence 10}}}}
(my-assoc-in {} [:teams :team1 :leader :intelligence] 10) ; => {:teams {:team1 {:leader {:intelligence 10}}}}

; 4: use update-in function

(def classroom {:p1 {:name "ryan" :age 18} :p2 {:name "adam" :age 21}})
(update-in classroom [:p1 :age] inc); => {:p1 {:name "ryan", :age 19}, :p2 {:name "adam", :age 21}}

; 5: implement update-in function
(defn my-update-in
  [m ks f]
  (assoc-in m ks (f (get-in m ks))))

(my-update-in classroom [:p1 :age] inc); => {:p1 {:name "ryan", :age 19}, :p2 {:name "adam", :age 21}}
