(ns codegladp1.toh
  (:require [clojure.test :refer :all]))

(defn tower-of-hanoi
  [n from-pin to-pin using-pin]
  (if (= n 1)
    [{:from from-pin :to to-pin}]
    (let [step-one (tower-of-hanoi (dec n) from-pin using-pin to-pin)
          step-two {:from from-pin :to to-pin}
          step-three (tower-of-hanoi (dec n) using-pin to-pin from-pin)]
      (apply conj step-one step-two step-three))))

(deftest tower-of-hanoi-test
  (is (= [{:from :A, :to :C}]
         (tower-of-hanoi 1 :A :C :B)))
  (is (= [{:from :A, :to :B}
          {:from :A, :to :C}
          {:from :B, :to :C}]
         (tower-of-hanoi 2 :A :C :B)))
  (is (= [{:from :A, :to :C}
          {:from :A, :to :B}
          {:from :C, :to :B}
          {:from :A, :to :C}
          {:from :B, :to :A}
          {:from :B, :to :C}
          {:from :A, :to :C}]
         (tower-of-hanoi 3 :A :C :B))))
