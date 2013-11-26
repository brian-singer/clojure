(ns clojsesame.integration.eurofonddirect
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def eurofondDirectInfo {:filename "dumps/eurofondy_EU_komisia-dump.csv" :type :eurofondsdirect})

(def eurodirectNamespace (str basicNamespace "/eurofonddirect/"))


(def addressURI (createURI (str basicNamespace "/addressico")))
(def cityURI (createURI (str basicNamespace "/city")))
(def objectiveURI (createURI (str basicNamespace "/objective")))
(def projNumberURI (createURI (str basicNamespace "/projnumber")))
(def amountURI (createURI (str basicNamespace "/amount")))
(def totalAmoutURI (createURI (str basicNamespace "/totalamout")))
(def participantURI (createURI (str basicNamespace "/participant")))

(def unameURI (createURI (str basicNamespace "/uname")))
(def unumberURI (createURI (str basicNamespace "/unumber")))
(def departmentURI (createURI (str basicNamespace "/department")))
(def areaURI (createURI (str basicNamespace "/area")))

(defn- eurofondsUuidURI [{ n :name}] 
	(createURI eurodirectNamespace n))

(defn- rc [{ n :receiver :as x}] 
	(vector (eurofondsUuidURI x) receiverURI (createLiteral n)))

(defn- ad [{ n :address :as x}] 
	(vector (eurofondsUuidURI x) addressURI (createLiteral n)))

(defn- ct [{ n :city :as x}] 
	(vector (eurofondsUuidURI x) cityURI (createLiteral n)))

(defn- obj [{ n :objective :as x}] 
	(vector (eurofondsUuidURI x) objectiveURI (createLiteral n)))

(defn- projNumb [{ n :projNumber :as x}] 
	(vector (eurofondsUuidURI x) projNumberURI (createLiteral n)))

(defn- amnt [{ n :amount :as x}] 
	(vector (eurofondsUuidURI x) amountURI (createLiteral n)))

(defn- ta [{ n :totalAmout :as x}] 
	(vector (eurofondsUuidURI x) totalAmoutURI (createLiteral n)))

(defn- parts [{ n :participant :as x}] 
	(vector (eurofondsUuidURI x) participantURI (createLiteral n)))

(defn- unam [{ n :uname :as x}] 
	(vector (eurofondsUuidURI x) unameURI (createLiteral n)))

(defn- unmbr [{ n :unumber :as x}] 
	(vector (eurofondsUuidURI x) unumberURI (createLiteral n)))

(defn- dprtment [{ n :department :as x}] 
	(vector (eurofondsUuidURI x) departmentURI (createLiteral n)))

(defn- ar [{ n :area :as x}] 
	(vector (eurofondsUuidURI x) areaURI (createLiteral n)))

(defn- procNote [{ n :note :as x}] 
	(vector (eurofondsUuidURI x) noteURI (createLiteral n)))

(defn- tp [{ n :type :as x}] 
	(vector (eurofondsUuidURI x) theType (createLiteral n)))




(defrecord EurofondsDirect [receiver address city objective projNumber year type amount totalAmout currency participant uname unumber department area note]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt rc ad ct obj obj projNumb amnt ta parts unam unmbr dprtment ar procNote tp) x)]
    		(insertTriple subj predic obj))))

(defmethod convert :eurofondsdirect [x type]
(let [[_ receiver address city _ objective projNumber year type amount totalAmout currency participant uname unumber department area note] x]
	(->EurofondsDirect receiver address city objective projNumber year type amount totalAmout currency participant uname unumber department area note)))
