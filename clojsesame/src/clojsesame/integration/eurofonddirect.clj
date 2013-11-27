(ns clojsesame.integration.eurofonddirect
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def eurofondDirectInfo {:filename "dumps/eurofondy_EU_komisia-dump.csv" :type :eurofondsdirect})


(def objectiveURI (createURI (str basicNamespace "/objective")))
(def projNumberURI (createURI (str basicNamespace "/projnumber")))
(def totalURI (createURI (str basicNamespace "/totalamount")))
(def participantURI (createURI (str basicNamespace "/participant")))
(def unameURI (createURI (str basicNamespace "/uname")))
(def unumberURI (createURI (str basicNamespace "/unumber")))
(def departmentURI (createURI (str basicNamespace "/department")))
(def areaURI (createURI (str basicNamespace "/area")))

(defn- eurofondsUuidURI [{ n :name}] 
	(createURI (str basicNamespace "/eurofonddirect/") n))

(def eurodirectMap { 
	:receiver receiver
	:address address
	:city city
	:objective objectiveURI
	:projNumber projNumberURI 
	:year year
	:type theType
	:amount amount
	:totalAmout totalURI
	:currency currency
	:participant participantURI
	:uname unameURI
	:unumber unumberURI
	:department departmentURI
	:area areaURI
	:note note})


(defrecord EurofondsDirect [receiver address city objective projNumber year type amount totalAmout currency participant uname unumber department area note]
	SesameRepository
	 (store [x] (storeRecord eurofondsUuidURI eurodirectMap x)))

(defmethod convert :eurofondsdirect [x type]
(let [[_ receiver address city _ objective projNumber year type amount totalAmout currency participant uname unumber department area note] x]
	(->EurofondsDirect receiver address city objective projNumber year type amount totalAmout currency participant uname unumber department area note)))
