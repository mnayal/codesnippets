(ns codegladp1.core
  (:require [clojure.string :as s]))

;;Problem 1
#_(defn -main [& args]
  (let [no-ing (Integer/parseInt (read-line))
        q-required (map #(Integer/parseInt %) (s/split (read-line) #" "))
        q-have (map #(Integer/parseInt %) (s/split (read-line) #" "))
        new-map (map #(quot %1 %2) q-have q-required)]
    (print (apply min new-map))))


#_(-main)


;;Problem 2
#_(defn wins [G-power opp-power count posG posOpp total]
  (if (and (< posG total) (< posOpp total))
    (if (< (opp-power posOpp) (G-power posG))
      (recur G-power opp-power (inc count) (inc posG) (inc posOpp) total)
      (recur G-power opp-power count (inc posG) posOpp total))
    (println count)))

#_(defn -main [& args]
  (let [tests-no (Integer/parseInt (read-line))]
    (dotimes [n tests-no]
      (let [team-nos (Integer/parseInt (read-line))
            G-power (vec (sort (map #(Long/parseLong %) (s/split (read-line) #" "))))
            opp-power (vec (sort (map #(Long/parseLong %) (s/split (read-line) #" "))))]
        (wins G-power opp-power 0 0 0 team-nos)))))



(defn -main [& args]
  (let [[P D] (map #(Integer/parseInt %) (s/split (read-line) #" "))]
    (print P D)))

(-main)



(range 1 5 2)

(type (* 2 4))
(type 1)
(and 1 0)

(def x 0)
(and x false)
(macroexpand '(and "test" 1 0 nil 3))
(eval abasd)


(def y)










