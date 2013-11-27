(ns clojsesame.integration.procurements
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def procurementsInfo {:filename "dumps/procurements-dump.csv" :type :procurements})

(defn- procurementsUuidURI [{ n :name}] 
	(createURI (str basicNamespace "/procurement/") n))

(def customerAddressURI (createURI (str basicNamespace "/customeraddress")))
(def customerCityURI (createURI (str basicNamespace "/customercity")))
(def supplierAddressURI (createURI (str basicNamespace "/supplieraddress")))
(def supplierCityURI (createURI (str basicNamespace "/suppliercity")))
(def procurementSubjectURI (createURI (str basicNamespace "/procurementsubject")))
(def priceURI (createURI (str basicNamespace "/price")))
(def VAT_includedURI (createURI (str basicNamespace "/vatincluded")))
(def customerFullnameURI (createURI (str basicNamespace "/customerfullname")))
(def customerIcoURI (createURI (str basicNamespace "/customerico")))
(def supplierFullnameURI (createURI (str basicNamespace "/supplierfullname")))
(def supplierRegionURI (createURI (str basicNamespace "/supplierregion")))

(def procurementMap {
 :customerAddress customerAddressURI
 :customerCity customerCityURI
 :supplierAddress supplierAddressURI
 :supplierCity supplierCityURI
 :note note
 :year year
 :procurementSubject procurementSubjectURI
 :price  priceURI
 :currency currency
 :VAT_included VAT_includedURI 
 :sourceUrl sourceUrl
 :customerFullname customerFullnameURI
 :customerIco customerIcoURI 
 :supplierFullname supplierFullnameURI 
 :supplierIco supplierIco
 :supplierRegion supplierRegionURI})

(defrecord Procurement [customerAddress customerCity supplierAddress supplierCity note year
 procurementSubject price currency VAT_included sourceUrl customerFullname
 customerIco supplierFullname supplierIco supplierRegion]
	SesameRepository
	 (store [x] (storeRecord procurementsUuidURI procurementMap x)))


(defmethod convert :procurements [x type]
(let [[_ customerAddress customerCity supplierAddress supplierCity
 note year _ _ procurementSubject price currency VAT_included sourceUrl customerFullname
 customerIco supplierFullname supplierIco supplierRegion] x]
	(->Procurement customerAddress customerCity supplierAddress supplierCity note year procurementSubject price
		currency VAT_included sourceUrl customerFullname customerIco supplierFullname supplierIco supplierRegion)))
