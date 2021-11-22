(ns first-test
  "These are the tests you are looking for"
  (:require [clojure.test :refer [deftest is testing use-fixtures]]
            [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as specgen]
            [clojure.repl :refer [doc]]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            ))

(defn fix-it-up [test-run]
  (def one 1)
  (test-run))

(defn small? [arg] (< arg 10))
(defn large? [arg] (> arg 10))
(defn positive? [arg] (>= arg 0))

(spec/def :num/small-odds
          (spec/and int? odd? positive? small?))

(spec/def :num/smallish
          (spec/and int? (spec/int-in 0 100)))

(spec/def :num/big-or-even
          (spec/or :even even?
                   :big  large?))


(use-fixtures :once fix-it-up)

(deftest small-odds
  (testing "Is this thing on?"
    (is (spec/valid? :num/small-odds 1))))

(deftest small-odds-too-big
  (testing "Is this thing still on?"
    (is (not (spec/valid? :num/small-odds 12)))))

(deftest small-odds-not-odd
  (testing "Is this thing still on?"
    (is (not (spec/valid? :num/small-odds 13)))))

(deftest big-or-even-big-enough
  (testing "Is this thing still on?"
    (is (spec/valid? :num/big-or-even 13))))

(deftest big-or-even-its-even
  (testing "Is this thing still on?"
    (is (spec/valid? :num/big-or-even 4))))

(deftest big-or-even-too-small-and-odd
  (testing "Is this thing still on?"
    (is (not (spec/valid? :num/big-or-even 5)))))

(deftest non-conforming
  (testing "Is this thing still on?"
    (is (spec/conform :num/big-or-even 4))))

(deftest conforming
  (testing "Is this thing still on?"
    (is (not= (spec/conform :num/big-or-even 5) :clojure.spec.alpha/invalid))))

; clojure.spec based generators
(println (specgen/sample (spec/gen :num/small-odds)))
(println (specgen/sample (spec/gen :num/smallish)))
(println (specgen/sample (spec/gen string?)))

; test.check generators
(println (gen/sample gen/string))
(println (gen/sample gen/string-alphanumeric))
(println (gen/generate gen/int 50))
(println (gen/generate gen/large-integer))

; Printing functions...
;(print (doc :num/small-odds))
;(print (doc :num/big-or-even))
;(spec/explain :num/big-or-even 5)
