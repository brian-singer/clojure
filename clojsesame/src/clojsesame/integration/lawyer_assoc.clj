(ns clojsesame.integration.lawyer_assoc
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def lawyerAssocInfo {:filename "dumps/lawyer_associates-dump.csv" :type :lawyerAssoc})

(def lawyerAssocNamespace (str basicNamespace "/lawyerAssoc/"))

(defn- laUuidURI [{ n :name}] 
	(createURI lawyerAssocNamespace n))

(defn- laName [{ n :name :as x}] 
	(vector (laUuidURI x) fullName (createLiteral n)))

(defn- laProfession [{ p :profession :as x}] 
	(vector (laUuidURI x) profession (createLiteral p)))


(defrecord LawyerAssociates [name profession]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt laName laProfession) x)]
    		(insertTriple subj predic obj))))

(defmethod convert :lawyerAssoc [x type]
(let [[_ _ firstName lastName] x]
	(->LawyerAssociates (str firstName " " lastName) "lawyerAssociate")))
