; exercise 1

(def order-details
  {:name "Mitchard Blimmons"
   :email "mitchard.blimmonsgmail.com"})

(def order-details-validations
  {:name
   ["Please enter a name" not-empty]

   :email
   ["Please enter an email address" not-empty

    "Your email address doesn't look like an email address"
    #(or (empty? %) (re-seq #"@" %))]})


(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))


(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

(validate order-details order-details-validations)

(defmacro if-valid
  "Handle validation more concisely"
  [to-validate validations errors-name & then-else]
  `(let [~errors-name (validate ~to-validate ~validations)]
     (if (empty? ~errors-name)
       ~@then-else)))

(if-valid order-details order-details-validations my-error-name (println :success)
(println :failure my-error-name))

(defmacro when-valid
  "Handle validation more concisely"
  [to-validate validations errors-name & then-statements]
  `(let [~errors-name (validate ~to-validate ~validations)]
     (when (empty? ~errors-name)
       ~@then-statements)))

(macroexpand '(when-valid order-details order-details-validations my-error-name (println :success) (println :happy)))

; exercise 2 -> implement or using a macro

(defmacro my-or
  ([] false)
  ([x] x)
  ([x & next]
   `(let [or# ~x]
      (if or# or# (my-or ~@next)))))

; exercise 3 -> attribute getter macro

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(defmacro defattrs
  ([])
  ([name attr & rest]
  `(do (defattrs ~@rest) (def ~name (comp ~attr :attributes)))))

(defattrs c-int :intelligence 
  d-int :dexterity
  s-int :strength)

(d-int character); => 5