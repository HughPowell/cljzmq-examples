(ns cljzmq-examples.serialization.clojure
  (:require [clojure.edn :as edn]
            [zeromq.zmq :as zmq]))

(set! *warn-on-reflection* true)

(defn send-clj
  ([socket clj-data]
     (send-clj socket clj-data 0))
  ([socket clj-data flags]
     (let [data (-> clj-data
                    (pr-str)
                    (.getBytes))]
       (zmq/send socket data flags))))

(defn receive-clj
  ([socket]
     (receive-clj socket 0))
  ([socket flags]
     (-> (zmq/receive socket flags)
         (String.)
         (edn/read-string))))
