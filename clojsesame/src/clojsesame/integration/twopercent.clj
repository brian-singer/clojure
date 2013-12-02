(ns clojsesame.integration.twopercent
        (:require
                [clojsesame.vocabulary :refer :all]
                [clojsesame.sesame :refer :all]))

(def twopercentInfo {:filename "dumps/dve_percenta-dump.csv" :type :twopercent})

(defn- twopercentURI [x]
        (createURI (str basicNamespace "/twopercent/") (:companyIco x)))

(def sidURI (createURI (str basicNamespace "/sid/")))
(def percentageURI (createURI (str basicNamespace "/percentage/")))

(def twopercentMap {
	:donorCompany donorCompany 
	:city city
	:donorIco donorIco
	:sid sidURI
	:totalAmount totalAmount
	:currency currency
	:percentage percentageURI
	:year year
	:note note})

(defrecord TwoPercent [donorCompany city donorIco sid totalAmount currency percentage year note]
        SesameRepository
         (store [x]         
            (storeRecord twopercentURI twopercentMap x)))

(defmethod convert :twopercent [x type]
(let [[ _  donorCompany _ city _ donorIco sid totalAmount currency percentage year note] x]
        (->TwoPercent donorCompany city donorIco sid totalAmount currency percentage year note)))
