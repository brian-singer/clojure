(ns clojsesame.integration.govmembers
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def govmemberInfo {:filename "dumps/vlady_sr-dump.csv" :type :govmember})

(defn- govmemberURI [x] 
	(createURI (str basicNamespace "/govmember/") (:name x)))

(def govmemberMap { 
	:name fullname
	:occupation occupation
	:ministry ministry
	:fromDate fromDate
	:toDate toDate})

(defrecord GovMember [name occupation ministry fromDate toDate]
	SesameRepository
	 (store [x] 	
    	(storeRecord govmemberURI govmemberMap x)))

(defmethod convert :govmember [x type]
(let [[_ firstName lastName occupation ministry fromDate toDate] x]
	(->GovMember (str firstName " " lastName) occupation ministry fromDate toDate)))
