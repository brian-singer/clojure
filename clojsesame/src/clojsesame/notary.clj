(ns clojsesame.notary
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def notaryInfo {:filename "dumps/notaries-dump.csv" :type :notary})

(def notaryNamespace (str basicNamespace "/notary/"))

(defn notaryUuidURI [x] 
	(createURI notaryNamespace (:name x)))

(defn notaryName [x] 
	(vector (notaryUuidURI x) fullName (createLiteral (:name x))))

(defn notaryProfession [x] 
	(vector (notaryUuidURI x) profession (createLiteral (:form x))))

(defn notaryCity [x] 
	(vector (notaryUuidURI x) city (createLiteral (:city x))))

(defrecord Notary [name form street city zip]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt notaryName notaryProfession notaryCity) x)]
    		(insertTriple subj predic obj))))


(defmethod convert :notary [x type] 
	(apply ->Notary 
		(->> x next (take 5))))
