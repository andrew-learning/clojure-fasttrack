(ns clojure-fasttrack.bruce)

(defn create-hand [n-cards]
  (->> (for [suit [:clubs :diamonds :hearts :spades]
             rank [:ace :two :three :four :five
                   :six :seven :eight :nine :ten
                   :jack :queen :king :ace]]
         {:suit suit :rank rank})
       shuffle
       (take n-cards)))


(defn of-a-kind? [num hand]
  (->> hand
       (map :rank)
       frequencies
       vals
       (some #(= num %))))

(def h (create-hand 5))

(of-a-kind? 2 [{:rank :two :suit :diamonds}
               {:rank :two :suit :spades}
               {:rank :three :suit :diamonds}
               {:rank :queen :suit :diamonds}
               {:rank :jack :suit :diamonds}])

(of-a-kind? 2 h)
