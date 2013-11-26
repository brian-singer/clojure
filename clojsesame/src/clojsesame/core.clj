(ns clojsesame.core
	(:require 
		[clojure.java.io :refer :all]
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]
		[clojsesame.integration.notary :refer :all]
		[clojsesame.integration.lawyer :refer :all]
		[clojsesame.integration.executor :refer :all]
		[clojsesame.integration.europolitics :refer :all]
		[clojsesame.integration.lawyer_assoc :refer :all]
		[clojsesame.integration.lawyer_partnerships :refer :all]
		[clojsesame.integration.notary_empl :refer :all]
		[clojsesame.integration.procurements :refer :all]
		[clojsesame.integration.opencontract :refer :all]
		[clojsesame.integration.eurofonds :refer :all]
		[clojsesame.integration.eurofonddirect :refer :all])
	(:gen-class))

(import 'org.openrdf.query.QueryLanguage)

(defn testQuery []
  (with-open [c (.getConnection repository)]
  	(-> c (.prepareTupleQuery QueryLanguage/SPARQL "SELECT ?x ?p ?y WHERE { ?x ?p ?y } ") .evaluate result2vec))) 

(defn parseCsvLine [x] 
	(re-seq #"[^,]+" x))		

(defn readCsvFile [{:keys [filename type]}]
	(with-open [rdr (reader filename)]
	(doall
		(for [line (line-seq rdr)]
			(-> line parseCsvLine (convert type))))))

(defn storeEm [x]
	(dorun (map store x)))

(defn -main
  [& args]
  	(storeEm 
  		(readCsvFile eurofondDirectInfo ))
  	(let [results (testQuery)]
  		(println results)))
  		
