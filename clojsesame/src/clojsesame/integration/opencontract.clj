(ns clojsesame.integration.opencontract
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def contractInfo {:filename "dumps/otvorenezmluvy-dump.csv" :type :opencontract})

(def contractNameURI (createURI (str basicNamespace "/contractname")))
(def departmentUri (createURI (str basicNamespace "/department")))
(def customerURI (createURI (str basicNamespace "/customer")))
(def supplierURI (createURI (str basicNamespace "/supplier")))
(def contractedAmountURI (createURI (str basicNamespace "/contractedAmount")))
(def publishedOnURI (createURI (str basicNamespace "/publishedon")))
(def effectiveFromUri (createURI (str basicNamespace "/effectivefrom")))
(def expiresOnUri (createURI (str basicNamespace "/expireson")))
(def pageCountURI (createURI (str basicNamespace "/pagecount")))

(defn- contractURI [{ n :name}] 
	(createURI (str basicNamespace "/opencontract/") n))

(def contractMap { 
	:contractName contractNameURI
	:type theType
	:department departmentUri
	:customer customerURI
	:supplier supplierURI
	:supplierIco supplierIco
	:contractedAmount contractedAmountURI
	:totalAmount totalAmount
	:publishedOn publishedOnURI
	:effectiveFrom effectiveFromUri
	:expiresOn expiresOnUri
	:note note
	:pageCount pageCountURI
	:sourceUrl sourceUrl })

(defrecord OpenContract [contractName type department customer supplier 
	supplierIco contractedAmount totalAmount publishedOn effectiveFrom expiresOn note pageCount sourceUrl]
	SesameRepository
	 (store [x]
	 	(storeRecord contractURI contractMap x)))

(defmethod convert :opencontract [x type]
(let [[_ contractName type department customer supplier supplierIco
 contractedAmount totalAmount publishedOn effectiveFrom expiresOn note pageCount sourceUrl] x]
	(->OpenContract contractName type department customer supplier supplierIco contractedAmount
	 totalAmount publishedOn effectiveFrom expiresOn note pageCount sourceUrl)))
