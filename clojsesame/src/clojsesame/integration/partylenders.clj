(ns clojsesame.integration.partylenders
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def partylenderInfo {:filename "dumps/pozicky_stranam-dump.csv" :type :partylender})

(defn- partylenderURI [x] 
	(createURI (str basicNamespace "/partylender/") (:name x)))

(def closedLicenseURI (createURI (str basicNamespace "/closedlicense")))
(def returnDateURI (createURI (str basicNamespace "/returnDate")))
(def dueDateURI (createURI (str basicNamespace "/dueDate")))
(def closedLicenseURI (createURI (str basicNamespace "/closedLicense")))

(def partylenderMap { 
	:name fullname
	:closedLicense closedLicenseURI
	:donorCompany donorCompany 
	:donorIco donorIco
	:city city
	:amount amount
	:currency currency
	:party party
	:year year
	:acceptDate acceptDate
	:returnDate returnDateURI
	:dueDate dueDateURI
	:note note})

(defrecord PartyLender [name closedLicense donorCompany donorIco city amount currency party year acceptDate returnDate dueDate note]
	SesameRepository
	 (store [x] 	
    	(storeRecord partylenderURI partylenderMap x)))

(defmethod convert :partylender [x type]
(let [[_ closedLicense _ firstName lastName donorCompany donorIco _ city amount currency party year acceptDate returnDate dueDate note] x]
	(->PartyLender (str firstName " " lastName) closedLicense donorCompany donorIco city amount currency party year acceptDate returnDate dueDate note)))
