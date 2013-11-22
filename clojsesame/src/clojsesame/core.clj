(ns clojsesame.core
	(:require 
		[clojure.java.io :refer :all]
		[clojsesame.vocabulary :refer :all]
		[clojsesame.notary :refer :all]
		[clojsesame.sesame :refer :all]
		[clojsesame.lawyer :refer :all])
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
  		(readCsvFile lawyerInfo ))
  	(let [results (testQuery)]
  		(println results)))
  		
