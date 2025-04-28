(ns clojure-testing.clojure-test)
(require '[clojure.test :refer :all])

;;Clojure test

;;are
;;Checks multiple assertions.
;;Usage: (are argv expr & args)
(deftest my-are-test
  (are [x y] (= x y)
    2 (+ 1 1)
    4 (* 2 2)
    8 (* 4 2)))
(run-test my-are-test)
; {:test 1, :pass 3, :fail 0, :error 0, :type :summary}

;;is
;;Checks one assertion.
;;Usage: (are argv expr & args)
(deftest my-is-test
  (is (= 4 (+ 2 2)) "Two plus two should be 4"))
(run-test my-is-test)
; {:test 1, :pass 1, :fail 0, :error 0, :type :summary}

;;is thrown?
;;Usage: (is (thrown? c body))
;;Checks that an instance of c is thrown from body, fails if not; then returns the thing thrown.
(deftest my-is-thrown-test
  (is (thrown? ArithmeticException (/ 1 0))))
(run-test my-is-thrown-test)
; {:test 1, :pass 1, :fail 0, :error 0, :type :summary}

;;is thrown-with-msg?
;;Usage: (is (thrown-with-msg? c re body))
;;Checks that an instance of c is thrown from body and that the message on the exception matches the regular expression re
  (deftest my-is-thrown-test
    (is (thrown? ArithmeticException (/ 1 0))))
  (run-test my-is-thrown-test)
  ; {:test 1, :pass 1, :fail 0, :error 0, :type :summary}

;;run-test
;;Usage: (run-test test-symbol)
;;Runs a single test.
(deftest my-is-test
  (is (= 4 (+ 2 2)) "Two plus two should be 4"))
(run-test my-is-test)

;;run-tests
;;Usages: (run-tests) (run-tests & namespaces)
;;Runs all tests in the given namespaces; prints results. Defaults to current namespace if none given.
;;Returns a map summarizing test results.
(require '[clojure.test :refer :all])

(deftest my-is-test
  (is (= 4 (+ 2 2)) "Two plus two should be 4"))

(deftest my-are-test
  (are [x y] (= x y)
  2 (+ 1 1)
  4 (* 2 2)
  8 (* 4 2)))

(run-tests)
; {:test 2, :pass 4, :fail 0, :error 0, :type :summary}

;;successful?
;;Usage: (successful? summary)
;;Returns true if the given test summary indicates all tests were successful, false otherwise.
(def summary {:test 2, :pass 4, :fail 0, :error 0, :type :summary})
(println (successful? summary)) ; true

(def summary {:test 2, :pass 3, :fail 1, :error 0, :type :summary})
(println (successful? summary)) ;false

;;testing
;;Usage: (testing string & body)
;;Adds a new string to the list of testing contexts. May be nested, but must occur inside a test function (deftest).
(deftest my-test
  (testing "Arithmetic"
    (testing "with positive integers"
      (is (= 4 (+ 2 2)))
      (is (= 7 (+ 3 4))))
    (testing "with negative integers"
      (is (= -5 (+ -2 -2))) ; fails
      (is (= -1 (+ 3 -4))))))
(run-test my-test)
; {:test 1, :pass 3, :fail 1, :error 0, :type :summary}

;;use-fixtures
;;Wrap test runs in a fixture function to perform setup and tear-down.
;;Using a fixture-type of :each wraps every test individually, while :once wraps the whole run in a single function.
;;Usage: (use-fixtures :fixture-type fixture-fn1 fixture-fn2 ... fixture-fnN)
;;Using a fixture-type of :each wraps every test individually, while :once wraps the whole run in a single function.
(defn my-fixture [f]
  (println "@TODO Setup")
  (f)
  (println "@TODO Tear-down"))

(deftest test-a
  (is (= 4 (+ 2 2))))

(deftest test-b
  (is (= 8 (+ 2 6))))

(use-fixtures :each my-fixture)

(run-tests)
; Testing user
; @TODO Setup
; @TODO Tear-down
; @TODO Setup
; @TODO Tear-down
;
; Ran 2 tests containing 2 assertions.
; 0 failures, 0 errors.
; {:test 2, :pass 2, :fail 0, :error 0, :type :summary}

;;once fixture type
;;Fixtures run once around all tests.
(defn my-fixture [f]
  (println "@TODO Setup")
  (f)
  (println "@TODO Tear-down"))

(deftest test-a
  (is (= 4 (+ 2 2))))

(deftest test-b
  (is (= 8 (+ 2 6))))

(use-fixtures :once my-fixture)

(run-tests)
; Testing user
; @TODO Setup
; @TODO Tear-down
;
; Ran 2 tests containing 2 assertions.
; 0 failures, 0 errors.
; {:test 2, :pass 2, :fail 0, :error 0, :type :summary}

