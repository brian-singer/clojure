(ns clojsesame.integration.secretary
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def secretaryInfo {:filename "dumps/statni_tajomnici-dump.csv" :type :secretary})

(defn- secretaryURI [x] 
	(createURI (str basicNamespace "/secretary/") (:name x)))

(def secretaryMap { 
	:name fullname
	:occupation occupation
	:ministry ministry
	:fromDate fromDate
	:toDate toDate
	:note note})

(defrecord Secretary [name occupation ministry fromDate toDate note]
	SesameRepository
	 (store [x]     	
    	(storeRecord secretaryURI secretaryMap x)))

(defmethod convert :secretary [x type]
(let [[_ _ firstName lastName occupation ministry fromDate toDate note] x]
	(->Secretary (str firstName " " lastName) occupation ministry fromDate toDate note)))
