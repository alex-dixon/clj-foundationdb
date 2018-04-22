(defproject clj-foundationdb "0.0.0-SNAPSHOT"
  :repositories {"local" ~(str (.toURI (java.io.File. "maven_repository")))}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [com.apple.foundationdb/fdb-java "5.1.5"]])
