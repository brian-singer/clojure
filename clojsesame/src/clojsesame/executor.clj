(ns clojsesame.executor
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def executorInfo {:filename "dumps/executors-dump.csv" :type :executor})

(def executorNamespace (str basicNamespace "/executor/"))

(defn- executorUuidURI [{ n :name}] 
	(createURI executorNamespace n))

(defn- executorName [{ n :name :as x}] 
	(vector (executorUuidURI x) fullName (createLiteral n)))

(defn- executorProfession [{ p :profession :as x}] 
	(vector (executorUuidURI x) profession (createLiteral p)))

(defn- executorCity [{ c :city :as x}] 
	(vector (executorUuidURI x) city (createLiteral c)))

(defrecord Executor [name profession city]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt executorName executorProfession executorCity) x)]
    		(insertTriple subj predic obj))))

(defmethod convert :executor [x type]
(let [[_ fullname _ _ _ _ _ city] x]
	(->Executor fullname "executor" city)))
