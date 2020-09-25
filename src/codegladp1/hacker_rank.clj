(ns codegladp1.hacker-rank)

(require '(clojure [set :as cs]))

(defn groups-count [values]
  (let [mapped (map-indexed (fn [index value]
                              [index (map #(Character/digit % 10) value)])
                            values)
        relations-set-fn (fn [v] (into #{} (keep-indexed #(if (= %2 1) % nil) v)))
        find-group-fn (fn [groups curr-group]
                        (some (fn [[k v]]
                               (if (empty? (cs/intersection v curr-group))
                                 nil
                                 k))
                              groups))]
    (count
      (reduce
        (fn [final-groups [index line]]
          (let [relations-set (relations-set-fn line)
                group-key (find-group-fn final-groups relations-set)]
            (if group-key
              (update final-groups group-key cs/union relations-set)
              (assoc final-groups index relations-set))))
        {}
        mapped))))

(def fptr (get (System/getenv) "OUTPUT_PATH"))

(def values-count (Integer/parseInt (clojure.string/trim (read-line))))

(def values [])

(doseq [_ (range values-count)]
  (def values (conj values (read-line)))
  )

(def res (groups-count values))

(println res)

#_(spit fptr (clojure.string/join "\n" res) :append true)
#_(spit fptr "\n" :append true)
