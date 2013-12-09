(ns clojure-fasttrack.cards
  (:require [clojure.string :as str]))

(def ranks (concat ["Ace"] (map #(+ 2 %) (range 9)) ["Jack" "Queen" "King"]))

(def deck (for [suit [:Hearts :Clubs :Diamonds :Spades] rank ranks] {:rank rank :suit suit})) 

;(defn concise [deck]
;  (let [suit-chars {:Hearts \u2665 :Diamonds \u2666 :Spades \u2660 :Clubs \u2663}]
;    (map #(str (subs (str (:rank %1)) 0 1) (suit-chars (:suit %1))) deck)))

;(take 5 (map #(str (:rank %1) " of " (-> %1 :suit name)) (shuffle deck)))

(defn pairs [hand] (map val (filter #(= 2 (count (val %1))) (group-by :rank hand))))

(defn one-pair [hand]
  (let [p (pairs hand)]
    (if (= 1 (count p)) (first p) nil)))

(defn two-pair [hand]
  (let [p (pairs hand)]
    (if (= 2 (count p)) p nil)))

(defn full-house [hand]
  (let [ranked (group-by :rank hand)
        two-cards (filter #(= 2 (count (val %1))) ranked)
        three-cards (filter #(= 3 (count (val %1))) ranked)]
    (if (and (seq two-cards) (seq three-cards)) (concat two-cards three-cards) nil)))

(defn five-card-straight [hand]
  (let [runs (partition 5 1 (conj (vec ranks) (first ranks)))]
    runs)
  )
;
;(defn card-name [card] (str (:rank card) " of " (-> card :suit name)))

(into {} [:ace :hearts])

(defn report [hand]
  (let [hand-fns {:one-pair one-pair :two-pair two-pair :full-house full-house}]
    (into 
      {:hand hand}
        (apply merge (map #(if-let [result ((val %1) hand)] {(key %1) result} {}) hand-fns)))))


(def hand (take 7 (shuffle deck)))

;(def hand [{:suit :Hearts :rank 9} {:rank 9 :suit :Clubs} {:rank 9 :suit :Diamonds } {:rank 7 :suit :Clubs} {:suit :Hearts :rank 7}])
(five-card-straight hand)
