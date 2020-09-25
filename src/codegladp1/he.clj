(ns codegladp1.he)

(def deps-data {:a {:b {:c nil,
                        :d nil}
                    :e {:a nil}}
                :f {:g {:e nil}
                    :i {:j nil}}})


#_(defn get-all-deps
  [final deps-data]
  (reduce (fn [final-set [k v]]
            (if-let v
              (get-all-deps final-set [k v])
              (conj final-set k)))
          final deps-data))


#_(defn resolve-deps [final-set [k v]]
  (if (contains? final-set k)
    (do
      (println "contains: " final-set)
      final-set)
    (if (nil? v)
      (do
        (println "nil: " final-set k)
        (conj final-set k))
      (do
        (println "call again: " final-set v)
        (reduce resolve-deps (conj final-set k) v)))))

(defn get-all-deps [deps-data]
  (reduce (fn resolve-deps [final-set [k v]]
            (if (contains? final-set k)
              (do
                (println "contains: " final-set)
                final-set)
              (if (nil? v)
                (do
                  (println "nil: " final-set k)
                  (conj final-set k))
                (do
                  (println "call again: " final-set v)
                  (reduce resolve-deps (conj final-set k) v))))) #{} deps-data))
(get-all-deps deps-data)

(->>
  (group-by first '([:a 1] [:b 2] [:c 3] [:a 4] [:c 5]))
  (vals)
  (map #(apply max (map second %)))
  #_(map #(apply max-key second %)))

