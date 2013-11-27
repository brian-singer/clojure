(ns clojsesame.integration.executor
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def executorInfo {:filename "dumps/executors-dump.csv" :type :executor})

(defn- executorURI [{ n :name}] 
	(createURI (str basicNamespace "/executor/") n))

(def executorMap { 
	:name fullname
	:profession profession
	:city city})

(defrecord Executor [name profession city]
	SesameRepository
	 (store [x]     	
    	(storeRecord executorURI executorMap x)))

(defmethod convert :executor [x type]
(let [[_ fullname _ _ _ _ _ city] x]
	(->Executor fullname "executor" city)))
