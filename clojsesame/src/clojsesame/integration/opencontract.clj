(ns clojsesame.integration.opencontract
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def ocInfo {:filename "dumps/otvorenezmluvy-dump.csv" :type :opencontract})

(def ocNamespace (str basicNamespace "/opencontract/"))


(def contractNameURI (createURI (str basicNamespace "/contractname")))
(def departmentUri (createURI (str basicNamespace "/department")))
(def customerURI (createURI (str basicNamespace "/customer")))
(def supplierURI (createURI (str basicNamespace "/supplier")))
(def contractedAmountURI (createURI (str basicNamespace "/contractedAmount")))
(def totalAmountURI (createURI (str basicNamespace "/totalamount")))
(def publishedOnURI (createURI (str basicNamespace "/publishedon")))
(def effectiveFromUri (createURI (str basicNamespace "/effectivefrom")))
(def expiresOnUri (createURI (str basicNamespace "/expireson")))
(def pageCountURI (createURI (str basicNamespace "/pagecount")))

(defn- ocUuidURI [{ n :name}] 
	(createURI ocNamespace n))

(defn- cName1 [{ n :contractName :as x}] 
	(vector (ocUuidURI x) contractNameURI (createLiteral n)))

(defn- cDepartment [{ n :department :as x}] 
	(vector (ocUuidURI x) departmentUri (createLiteral n)))

(defn- customer [{ n :customer :as x}] 
	(vector (ocUuidURI x) customerURI (createLiteral n)))

(defn- supplier [{ n :supplier :as x}] 
	(vector (ocUuidURI x) supplierURI (createLiteral n)))

(defn- sIco [{ n :supplierIco :as x}] 
	(vector (ocUuidURI x) supplierIcoURI (createLiteral n)))

(defn- contractedAmount [{ n :contractedAmount :as x}] 
	(vector (ocUuidURI x) contractedAmountURI (createLiteral n)))

(defn- totalAmount [{ n :totalAmount :as x}] 
	(vector (ocUuidURI x) totalAmountURI (createLiteral n)))

(defn- publishedOn [{ n :publishedOn :as x}] 
	(vector (ocUuidURI x) publishedOnURI (createLiteral n)))

(defn- effectiveFrom [{ n :effectiveFrom :as x}] 
	(vector (ocUuidURI x) effectiveFromUri (createLiteral n)))

(defn- expiresOn [{ n :expiresOn :as x}] 
	(vector (ocUuidURI x) expiresOnUri (createLiteral n)))

(defn- procSourceUrl [{ n :sourceUrl :as x}] 
	(vector (ocUuidURI x) sourceUrlURI (createLiteral n)))

(defn- ocNote [{ n :note :as x}] 
	(vector (ocUuidURI x) noteURI (createLiteral n)))

(defn- pageCount [{ n :pageCount :as x}] 
	(vector (ocUuidURI x) pageCountURI (createLiteral n)))


(defrecord OpenContract [contractName type department customer supplier supplierIco contractedAmount totalAmount publishedOn effectiveFrom expiresOn note pageCount sourceUrl]
	SesameRepository
	 (store [x] 
	 (println x)    	
    	(doseq [[subj predic obj] ((cName1 cDepartment customer supplier sIco contractedAmount totalAmount publishedOn effectiveFrom
    	 expiresOn procSourceUrl ocNote pageCount) x)]
    		(insertTriple subj predic obj))))

(defmethod convert :opencontract [x type]
(let [[_ contractName type department customer supplier supplierIco
 contractedAmount totalAmount publishedOn effectiveFrom expiresOn note pageCount sourceUrl] x]
	(->OpenContract contractName type department customer supplier supplierIco contractedAmount totalAmount publishedOn effectiveFrom expiresOn note pageCount sourceUrl)))
