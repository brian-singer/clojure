(defproject zugradar "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-http-lite "0.2.0"]
                 [compojure "1.1.6"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [overtone/at-at "1.2.0"]]
  :main ^:skip-aot zugradar.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
