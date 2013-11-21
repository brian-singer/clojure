(ns clojsesame.notary
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def notaryInfo {:filename "dumps/notaries-dump.csv" :type :notary})

(defrecord Notary [name form street city zip]
	SesameRepository
	 (store [x] 
    	(let [subj (createURI (str basicNamespace "/notary/") (:name x))
    		predic fullName
    		obj (createLiteral "dooh")]
    		(insertTriple subj predic obj))))


(defmethod convert :notary [x type] 
	(apply ->Notary 
		(->> x next (take 5))))
