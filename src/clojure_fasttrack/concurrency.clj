(ns clojure-fasttrack.concurrency)

;(def l (atom []))
;(swap! l conj 1)
;(deref l)

(def cashier (agent nil))
(def table (ref [] :validator #(<= (count %) 2)))
(def game-account {:balance (ref 1000)})
(def player1 {:name "John" :balance (ref 1000)})
(def player2 {:name "Sue" :balance (ref 1000)})
(def player3 {:name "Bill" :balance (ref 1000)})

(defn join-table [player]
  (let [fee 100]
    (dosync
      (alter table conj (:name player))
      (alter (player :balance) - fee)
      (alter (game-account :balance) + fee)
      (send-off cashier println (str "Debit " (:name player) " with $" fee)))))

; Malcolm uses swap! all the time, but rarely reset!

(defn players [] (deref table))

(join-table player1)
(join-table player2)
(join-table player3)

; Use :.Eval to eval current line
