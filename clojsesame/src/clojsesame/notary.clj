(ns clojsesame.notary
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))


(defrecord Notary [name form street city zip]
	SesameRepository
	 (store [x] 
    	(let [subj (createURI (str basicNamespace "/notary") (:name x))
    		predic fullName
    		obj (createLiteral "dooh")]
    		(insertTriple subj predic obj))))


(defn convert [x]
	(apply ->Notary 
		(->> x next (take 5))))