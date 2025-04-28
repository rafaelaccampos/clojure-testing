(ns clojure-testing.begin)
(require '[clojure.test :refer :all])

;;The core of clojure.test is the is macro. is lets you make assertions of any expression.
(is (= 4 (+ 2 2)))
; true

(is (= 5 (+ 2 2)))
; FAIL in () (NO_SOURCE_FILE:1)
; expected: (= 5 (+ 2 2))
;   actual: (not (= 5 4))
; false

(is (instance? Long 256))
; true

(is (.startsWith "abcde" "ab"))
; true

;;(require '[clojure.test :refer :all])

;;To help you document your assertions is accepts a string as an optional second argument.
(is (= 5 (+ 2 2)) "Making sure + works")
; FAIL in () (NO_SOURCE_FILE:1)
; Makign sure math works
; expected: (= 5 (+ 2 2))
;   actual: (not (= 5 4))
; false

;;You can also group and nest assertions with the testing macro.
(testing "Arithmetic"
  (testing "with positive integers"
    (is (= 4 (+ 2 2)))
    (is (= 7 (+ 3 4))))
  (testing "with negative integers"
    (is (= -5 (+ -2 -2))) ; fails
    (is (= -1 (+ 3 -4)))))
; FAIL in () (NO_SOURCE_FILE:6)
; Arithmetic with negative integers
; expected: (= -5 (+ -2 -2))
;   actual: (not (= -5 -4))
; true

(testing "Arithmetic"
  (testing "with positive integers"
    (is (= 4 (+ 2 2)))
    (is (= 7 (+ 3 4))))
  (testing "with negative integers"
    (is (= -5 (+ -2 -2))) ; fails
    (is (= -1 (+ 3 -4)))))
; FAIL in () (NO_SOURCE_FILE:6)
; Arithmetic with negative integers
; expected: (= -5 (+ -2 -2))
;   actual: (not (= -5 -4))
; true

;;To write a proper test, use the deftest macro. Among other things, it will allow you to run all of your tests using the run-tests function.
;;run-tests runs all tests in the given namespaces; prints results. Defaults to current namespace if none given. Returns a map summarizing test results.
(deftest my-test
   (is (.startsWith "hello" "goodbye")) ; fails
   (is (thrown? ArithmeticException (/ 1 0))))

(run-tests) ; {:test 1, :pass 1, :fail 1, :error 0, :type :summary}