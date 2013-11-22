(ns clojsesame.integration.europolitics
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def europoliticsInfo {:filename "dumps/europolitika-dump.csv" :type :europolitics})

(def europoliticsNamespace (str basicNamespace "/europolitics/"))

(defn- europoliticsUuidURI [{ n :name}] 
	(createURI europoliticsNamespace n))

(defn- euroName [{ n :name :as x}] 
	(vector (europoliticsUuidURI x) fullName (createLiteral n)))

(defn- euroProfession [{ p :profession :as x}] 
	(vector (europoliticsUuidURI x) profession (createLiteral p)))

(defn- euroOccupation [{ c :occupation :as x}] 
	(vector (europoliticsUuidURI x) occupation (createLiteral c)))

(defn- euroParty [{ p :party :as x}] 
	(vector (europoliticsUuidURI x) party (createLiteral p)))

(defn- euroYear [{ o :year :as x}] 
	(vector (europoliticsUuidURI x) year (createLiteral o)))

(defn- euroOrder [{ o :order :as x}] 
	(vector (europoliticsUuidURI x) order (createLiteral o)))


(defrecord Europolitics [name profession party occupation order year]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt euroName euroProfession euroOccupation euroParty euroYear euroOrder) x)]
    		(insertTriple subj predic obj))))

(defmethod convert :europolitics [x type]
(let [[_ _ firstName lastName _ party _ occupation order _ year] x]
	(->Europolitics (str firstName " " lastName) "europolitics" party occupation order year)))
