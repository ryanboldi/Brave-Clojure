;create comp function for any number of functions

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


