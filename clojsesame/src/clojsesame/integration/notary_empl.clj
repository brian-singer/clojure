(ns clojsesame.integration.notary_empl
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def notaryEmployeeInfo {:filename "dumps/notary_employees-dump.csv" :type :notaryEmployee})

(def notaryEmployeeNamespace (str basicNamespace "/notaryEmpl/"))

(defn- neUuidURI [{ n :name}] 
	(createURI notaryEmployeeNamespace n))

(defn- neName [{ n :name :as x}] 
	(vector (neUuidURI x) fullName (createLiteral n)))

(defn- neProfession [{ p :profession :as x}] 
	(vector (neUuidURI x) profession (createLiteral p)))


(defrecord NotaryEmployee [name profession]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt neName neProfession) x)]
    		(insertTriple subj predic obj))))

(defmethod convert :notaryEmployee [x type]
(let [[_ firstName lastName] x]
	(->NotaryEmployee (str firstName " " lastName) "notaryEmployee")))
