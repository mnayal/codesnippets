(ns codegladp1.dependency-graph
  (:require [clojure.set :as set]))

(def dependency-graph
  {:c [:a :b]
   :f [:d :e]
   :g [:f :c]})

(comment
  (defn all-projects [graph]
    (reduce (fn [final-set [k v]]
              (apply conj final-set k v))
            #{}
            graph))

  (defn dependent-projects [graph]
    (reduce (fn [final-set [k v]]
              (when-not (empty? v)
                (conj final-set k)))
            #{} graph))

  (defn independent-projects [graph]
    (set/difference (all-projects graph) (dependent-projects graph)))
  )


(defn ind-proj-from-leaf [top-proj-set dep-prj]
  (filter #((complement contains?) top-proj-set %) dep-prj))

(defn independent-projects [graph]
  (let [top-keys-set (set (keys graph))]
    (reduce (fn [projects [parent-prj dep-projs]]
              (if (empty? dep-projs)
                (conj projects parent-prj)
                (apply conj projects (ind-proj-from-leaf top-keys-set dep-projs))))
            []
            graph)))

(defn new-graph [graph projects]
  (let [projects-set (set projects)]
    (reduce (fn [final-set [k v]]
              (if (contains? projects-set k)
                final-set
                (let [new-dep (vec (remove #(contains? projects-set %) v))]
                  (assoc final-set k new-dep))))
            {}
            graph)))

(defn build-order [graph]
  (loop [result []
         graph graph]
    (if (empty? graph)
      result
      (let [ind-projects (independent-projects graph)]
        (recur (apply conj result ind-projects)
               (new-graph graph ind-projects))))))

(build-order dependency-graph)
