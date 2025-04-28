(ns repl-ide.clojure-testing.repl_ide)

(require '[clojure.test :refer :all])

;;If you look up the documentation of comment, maybe by running (doc comment) in a REPL,
;;you'll see that comment does nothing and returns nil.

;;These somewhat odd properties make comment a useful macro to use while coding.
;;It allows you to keep useful development-time code snippets in your namespaces.

(comment
    (refer 'set)
    (def xs #{{:a 11 :b 1 :c 1 :d 4}
             {:a 2 :b 12 :c 2 :d 6}
             {:a 3 :b 3 :c 3 :d 8 :f 42}})

    (def ys #{{:a 11 :b 11 :c 11 :e 5}
             {:a 12 :b 11 :c 12 :e 3}
             {:a 3 :b 3 :c 3 :e 7 }})

    (join xs ys)
;;    ...
)

(deftest my-test
   (is (.startsWith "hello" "goodbye")) ; fails
   (is (thrown? ArithmeticException (/ 1 0))))

(run-tests)

;;Exemplo do uso de comment
(deftest my-test
   (is (.startsWith "hello" "goodbye")) ; fails
   (is (thrown? ArithmeticException (/ 1 0))))

(comment
  (run-tests))