(ns clojsesame.integration.lawyer_partnerships
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def lawyerPartnerInfo {:filename "dumps/lawyer_partnerships-dump.csv" :type :lawyerPartner})

(def lawyerPartnerNamespace (str basicNamespace "/lawyerPartner/"))

(defn- lpUuidURI [{ n :name}] 
	(createURI lawyerPartnerNamespace n))

(defn- lpName [{ n :name :as x}] 
	(vector (lpUuidURI x) fullName (createLiteral n)))

(defn- lpCity [{ c :city :as x}] 
	(vector (lpUuidURI x) city (createLiteral c)))

(defn- lpType [{ t :type :as x}] 
	(vector (lpUuidURI x) theType (createLiteral t)))

(defrecord LawyerPartnership [name type city]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt lpName lpCity lpType) x)]
    		(insertTriple subj predic obj))))

(defmethod convert :lawyerPartner [x type]
(let [[_ fullName type _ _ _ _ city] x]
	(->LawyerPartnership fullName type city)))
