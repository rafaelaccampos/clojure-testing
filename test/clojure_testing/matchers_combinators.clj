(ns clojure-testing.matchers-combinators
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer [match?]]
            [matcher-combinators.matchers :as m])
  )

;; example that does not use matcher combinators
(deftest test-without-matcher-combinators
    (is (= 37 (+ 29 8))))

;; example that uses matcher combinators
(deftest test-with-matcher-combinators
   (is (match? (m/equals 37) (+ 29 8))))
(run-tests)

;;For all scalar and collection types except regex and maps, matcher-combinators use the matcher-combinators.matchers/equals matcher by default.
;;We can simplify the example above removing the explicit call to m/equals

;; example that does not use matcher combinators
(deftest test-without-matcher-combinators
    (is (= 37 (+ 29 8))))

;; example that uses matcher combinators
(deftest test-with-matcher-combinators
    (is (match? 37 (+ 29 8))))

(run-tests)

;;Embed Matcher
(defn person-with-calculated-age
    "Returns a map with an added :age calculated
    based on :birth/year and current-year"
    [person current-year]
  (let [age (- current-year (:birth/year person))]
    (assoc person :age age)))

(person-with-calculated-age {:birth/year 2000} 2024)
;; {:birth/year 2000 :age 24}

;;Without matcher combinators, our test will fail if we write it like the code below.
(deftest test-without-matcher-combinators
      (let [person {:name/first  "Alfredo"
                   :name/last   "da Rocha Viana"
                   :name/suffix "Jr."
                   :birth/year 1956}]
        (is (= {:age 68}
          (person-with-calculated-age person 2024)))))

(run-test test-without-matcher-combinators)
;; Ran 1 tests containing 1 assertions.
;;   actual: (not (= {:age 68} {:name/first "Alfredo", :name/last "da Rocha Viana", :name/suffix "Jr.", :birth/year 1956, :age 68}))
;; expected: (= {:age 68} (person-with-calculated-age person 2024))

;;To solve our problem we can use matcher-combinators with the matcher-combinators.matchers/embeds matcher.
;;When used in maps, the embeds matcher ignores all the un-specified keys.
(deftest test-person-with-calculated-age
    (let [person {:name/first  "Alfredo"
                  :name/last   "da Rocha Viana"
                  :name/suffix "Jr."
                  :birth/year 1956}]
     (is (match?
           (m/embeds {:age 68}) (person-with-calculated-age person 2024)))))
(run-test test-person-with-calculated-age)

;;And because the default matcher for maps is matcher-combinators.matchers/embeds we can simplify our code like this.
(deftest test-person-with-calculated-age
    (let [person {:name/first  "Alfredo"
                  :name/last   "da Rocha Viana"
                  :name/suffix "Jr."
                  :birth/year 1956}]
     (is (match? {:age 68} (person-with-calculated-age person 2024)))))
(run-test test-person-with-calculated-age)