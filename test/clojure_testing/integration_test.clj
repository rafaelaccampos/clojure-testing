(ns clojure-testing.integration-test)

;;state-flow is an open source testing framework maintained by Nubank.
;;We combine it with the closed source state-flow-helpers to write and run integrations tests.

(require
  '[state-flow.api :refer [flow]]
  '[state-flow.api :refer [flow defflow match?]]
  '[state-flow.helpers.component.servlet :as servlet])

;;A flow is a sequence of steps or bindings to be executed with some state as a reference.
(flow "do many things"
  (flow "do one thing" ,,,)
  (flow "do another thing" ,,,))

;;You can use match? in your flows.
(defflow my-flow
  (flow "using match"
    (match? 1 1)
    (match? {:a 1} {:a 1 :b 2})))

;;state-flow.helpers.servlet
;;You can use state-flow.helpers.servlet to test HTTP requests.
;;The example below tests if getting /api/hello-world returns {:status 200 :body {:greeting "Hello World!"}}.

(defflow hello-world
  (flow "route /api/hello-world returns a greeting"
    (match?
      {:status 200
       :body   {:greeting "Hello World!"}}
      (servlet/request {:method :get
                        :uri    "/api/hello-world"}))))

;;state-flow.helpers.kafka
;;You can use state-flow.helpers.kafka to test Kafka queues.
(require '[state-flow.api :refer [flow defflow match?]]
         '[state-flow.helpers.component.kafka :as helpers.kafka]
         '[state-flow.helpers.component.servlet :as servlet])

(defflow example
  (flow "consume-message on the :update-accumulator topic ... updates the accumulator"
    (helpers.kafka/consume-message {:topic :update-accumulator
                                    :message {:message "message content"}})

    (match? {:status 200
             :body [{:message "message content"}]}
            (servlet/request {:method :get
                              :uri "/api/accumulator"}))))