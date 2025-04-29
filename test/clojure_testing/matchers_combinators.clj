(ns clojure-testing.matchers-combinators
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer [match?]]
            [matcher-combinators.matchers :as m])
  )

;;Matchers Combinators
;; example that does not use matcher combinators
(deftest test-without-matcher-combinators
    (is (= 37 (+ 29 8))))

;; example that uses matcher combinators
(deftest test-with-matcher-combinators
   (is (match? (m/equals 37) (+ 29 8))))
(run-tests)