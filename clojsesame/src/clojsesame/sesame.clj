(ns clojsesame.sesame)

(import 'org.openrdf.sail.memory.MemoryStore
	'org.openrdf.repository.sail.SailRepository
	'org.openrdf.repository.http.HTTPRepository
	'org.openrdf.model.vocabulary.RDF 
	'org.openrdf.model.Resource
	'org.openrdf.query.QueryLanguage)

; (def repository (-> (MemoryStore.) SailRepository. ))
; (def valueFactory  (.getValueFactory repository ))
; (def _ (-> repository .initialize))

(def repository (HTTPRepository. "http://localhost:8080/openrdf-sesame/" "datasetX"))
(def valueFactory  (.getValueFactory repository ))
(def _ (-> repository .initialize))

(defprotocol SesameRepository 
	(store [x]))

(defn createLiteral [l]
	(.createLiteral valueFactory l))

(defn createURI 
	([uri suffix] (.createURI 
		valueFactory 
		(str uri suffix)))
	([uri] (.createURI valueFactory uri)))

(defn insertTriple [subj predic obj]
  (with-open [c (.getConnection repository)]
    (.add c (.createStatement valueFactory subj predic obj) (make-array Resource 0)))) 

(defn- createWrapper [queryResult]
	(reify java.lang.Iterable
		(iterator [this] 
			(reify java.util.Iterator
				(hasNext [this] (.hasNext queryResult))
				(next [this] (.next queryResult))
				(remove [this] (.remove queryResult))))))

(defn result2vec [queryResult]
	(-> queryResult createWrapper seq vec))

