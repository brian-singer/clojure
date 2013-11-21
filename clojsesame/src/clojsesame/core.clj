(ns clojsesame.core
  (:gen-class))

(use 'clojure.java.io)

(import 'org.openrdf.repository.sail.SailRepository)
(import 'org.openrdf.sail.memory.MemoryStore)
(import 'org.openrdf.model.vocabulary.RDF) ;(RDF/TYPE)
(import 'org.openrdf.model.impl.URIImpl)
(import 'org.openrdf.model.Resource)
(import 'org.openrdf.query.QueryLanguage)

(def repository (-> (MemoryStore.) SailRepository. ))
(def valueFactory  (.getValueFactory repository ))
(def _ (-> repository .initialize))

(defprotocol SesameRepository 
	(store [x]))

(defrecord Notary [name form street city zip])

(defn createURI2 
	([uri suffix] (.createURI 
		valueFactory 
		(str uri suffix)))
	([uri] (.createURI valueFactory uri)))


(defn createWrapper [queryResult]
	(reify java.lang.Iterable
		(iterator [this] 
			(reify java.util.Iterator
				(hasNext [this] (.hasNext queryResult))
				(next [this] (.next queryResult))
				(remove [this] (.remove queryResult))))))

(defn result2vec [queryResult]
	(-> queryResult createWrapper seq vec))


(defn createLiteral2 [l]
	(.createLiteral valueFactory l))

(defn insertTriple [subj predic obj]
  (with-open [c (.getConnection repository)]
    (.add c (.createStatement valueFactory subj predic obj) (make-array Resource 0)))) 

(defn testQuery []
  (with-open [c (.getConnection repository)]
  	(-> c (.prepareTupleQuery QueryLanguage/SPARQL "SELECT ?x ?y WHERE { ?x ?p ?y } ") .evaluate result2vec))) 

(extend-protocol SesameRepository
  Notary
    (store [x] 
    	(let [subj (createURI2 "http://whatever.sk/notary/" (:name x))
			  predic (createURI2 "http://whatever.sk/name")	    			
    		  obj (createLiteral2 "dooh")]
    		  (insertTriple subj predic obj))))

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

(defn storeEm [x]
	(dorun (map store x)))



(defn -main
  [& args]
  	(storeEm 
  		(readCsvFile "notaries-dump.csv" ))
  	(let [results (testQuery)]
  		(println results)))
  		
