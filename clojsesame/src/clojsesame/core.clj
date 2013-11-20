(ns clojsesame.core
  (:gen-class))

(use 'clojure.java.io)

(import 'org.openrdf.repository.sail.SailRepository)
(import 'org.openrdf.sail.memory.MemoryStore)

(def repository (-> (MemoryStore.) SailRepository. ))
(def valueFactory  (.getValueFactory repository ))
(def _ (-> repository .initialize))
(def connection  (.getConnection repository ))
	

(defprotocol SesameRepository 
	(store [x]))

(defrecord Notary [name form street city zip])

(extend-protocol SesameRepository
  Notary
    (store [x] (println "storing notary")))

(defn parseCsvLine [x] 
	(re-seq #"[^,]+" x))		

(defn convert [x]
	(apply ->Notary 
		(->> x next (take 5))))

(defn readCsvFile [filename]
	(with-open [rdr (reader filename)]
	(doall
		(for [line (line-seq rdr)]
			(-> line parseCsvLine convert)))))

(defn -main
  [& args]
  	;(readCsvFile "aaa" )
  	
  	)


