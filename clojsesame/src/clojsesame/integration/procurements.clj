(ns clojsesame.integration.procurements
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def procurementsInfo {:filename "dumps/procurements-dump.csv" :type :procurements})

(def procurementsNamespace (str basicNamespace "/procurement/"))

(def customerAddressURI (createURI (str basicNamespace "/customeraddress")))
(def customerCityURI (createURI (str basicNamespace "/customercity")))
(def supplierAddressURI (createURI (str basicNamespace "/supplieraddress")))
(def supplierCityURI (createURI (str basicNamespace "/suppliercity")))
(def yearURI (createURI (str basicNamespace "/year")))
(def procurementSubjectURI (createURI (str basicNamespace "/procurementsubject")))
(def priceURI (createURI (str basicNamespace "/price")))
(def VAT_includedURI (createURI (str basicNamespace "/vatincluded")))
(def customerFullnameURI (createURI (str basicNamespace "/customerfullname")))
(def customerIcoURI (createURI (str basicNamespace "/customerico")))
(def supplierFullnameURI (createURI (str basicNamespace "/supplierfullname")))
(def supplierRegionURI (createURI (str basicNamespace "/supplierregion")))

(defn- procurementsUuidURI [{ n :name}] 
	(createURI procurementsNamespace n))

(defn- cAddress [{ n :customerAddress :as x}] 
	(vector (procurementsUuidURI x) customerAddressURI (createLiteral n)))

(defn- cCity [{ n :customerCity :as x}] 
	(vector (procurementsUuidURI x) customerCityURI (createLiteral n)))

(defn- sAddress [{ n :supplierAddress :as x}] 
	(vector (procurementsUuidURI x) supplierAddressURI (createLiteral n)))

(defn- sCity [{ n :supplierCity :as x}] 
	(vector (procurementsUuidURI x) supplierCityURI (createLiteral n)))

(defn- procNote [{ n :note :as x}] 
	(vector (procurementsUuidURI x) noteURI (createLiteral n)))

(defn- procYear [{ n :year :as x}] 
	(vector (procurementsUuidURI x) yearURI (createLiteral n)))

(defn- pSubject [{ n :procurementSubject :as x}] 
	(vector (procurementsUuidURI x) procurementSubjectURI (createLiteral n)))

(defn- procPrice [{ n :price :as x}] 
	(vector (procurementsUuidURI x) priceURI (createLiteral n)))

(defn- procCurrency [{ n :currency :as x}] 
	(vector (procurementsUuidURI x) currencyURI (createLiteral n)))

(defn- procVAT_included [{ n :VAT_included :as x}] 
	(vector (procurementsUuidURI x) VAT_includedURI (createLiteral n)))

(defn- procSourceUrl [{ n :sourceUrl :as x}] 
	(vector (procurementsUuidURI x) sourceUrlURI (createLiteral n)))

(defn- cFullname [{ n :customerFullname :as x}] 
	(vector (procurementsUuidURI x) customerFullnameURI (createLiteral n)))

(defn- cIco [{ n :customerIco :as x}] 
	(vector (procurementsUuidURI x) customerIcoURI (createLiteral n)))

(defn- sFullname [{ n :supplierFullname :as x}] 
	(vector (procurementsUuidURI x) supplierFullnameURI (createLiteral n)))

(defn- sIco [{ n :supplierIco :as x}] 
	(vector (procurementsUuidURI x) supplierIcoURI (createLiteral n)))

(defn- sRegion [{ n :supplierRegion :as x}] 
	(vector (procurementsUuidURI x) supplierRegionURI (createLiteral n)))

(defrecord Procurement [customerAddress customerCity supplierAddress supplierCity note year
 procurementSubject price currency VAT_included sourceUrl customerFullname
 customerIco supplierFullname supplierIco supplierRegion]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt sRegion sIco sFullname cIco cFullname procSourceUrl procVAT_included 
    		procCurrency procPrice pSubject procYear procNote sCity sAddress cCity cAddress) x)]
    		(insertTriple subj predic obj))))

(defmethod convert :procurements [x type]
(let [[_ customerAddress customerCity supplierAddress supplierCity
 note year _ _ procurementSubject price currency VAT_included sourceUrl customerFullname
 customerIco supplierFullname supplierIco supplierRegion] x]
	(->Procurement customerAddress customerCity supplierAddress supplierCity note year procurementSubject price
		currency VAT_included sourceUrl customerFullname customerIco supplierFullname supplierIco supplierRegion)))
