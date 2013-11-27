(ns clojsesame.integration.notary
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def notaryInfo {:filename "dumps/notaries-dump.csv" :type :notary})

(defn- notaryURI [x] 
	(createURI (str basicNamespace "/notary/") (:name x)))

(def notaryMap { 
	:name fullname
	:form profession
	:city city})


(defrecord Notary [name form street city zip]
	SesameRepository
	 (store [x]     	
    	(storeRecord notaryURI notaryMap x)))


(defmethod convert :notary [x type] 
	(apply ->Notary 
		(->> x next (take 5))))
