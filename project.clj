(defproject clojure-testing "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.0"]]
  :profiles { :dev {
                    :source-paths   ["dev"]
                    :dependencies
                    [[nubank/matcher-combinators "3.9.1"]
                    [nubank/state-flow "5.20.2-beta.2"]
                    [state-flow-helpers "12.25.3"]]
                    }}
  :repl-options {:init-ns clojure-testing.core})
