(defproject clojsesame "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
  				[org.openrdf.sesame/sesame-runtime "2.7.8"]
  				[org.slf4j/slf4j-api "1.5.6"]
  				[org.slf4j/slf4j-simple "1.5.6"]
  				[commons-logging/commons-logging "1.1.1"]]

  :main ^:skip-aot clojsesame.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
