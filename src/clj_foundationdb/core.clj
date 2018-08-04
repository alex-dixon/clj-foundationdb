(ns clj-foundationdb.core
  (:import [com.apple.foundationdb FDB Transaction]
           [com.apple.foundationdb.tuple Tuple]
           [java.util.function Function]))

(defn ^Function as-function
  [f]
  (reify Function
    (apply [this arg] (f arg))))

(defmacro jfn [& args]
  `(as-function (fn ~@args)))

(def db (FDB/selectAPIVersion 520))
(def fdb (.open db))

(.run fdb (as-function
            (fn [^Transaction tr]
              (.set tr
                    (.pack (Tuple/from (into-array ["hello"])))
                    (.pack (Tuple/from (into-array ["world"]))))
              nil)))

(.run fdb (as-function
            (fn [^Transaction tr]
              (let [^byte-array result
                    (.join
                      (.get tr (.pack (Tuple/from (into-array ["hello"])))))]
                (.getString (Tuple/fromBytes result) 0)))))
