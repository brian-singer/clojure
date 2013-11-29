(ns clojsesame.integration.customs
        (:require
                [clojsesame.vocabulary :refer :all]
                [clojsesame.sesame :refer :all]))

(def customsInfo {:filename "dumps/odpustene_clo-dump.csv" :type :customs})

(defn- customsURI [x]
        (createURI (str basicNamespace "/customs/") (:companyIco x)))

(def customsOfficeURI (createURI (str basicNamespace "/customsoffice/")))
(def paragraphURI (createURI (str basicNamespace "/paragraph/")))

(def customsMap {
        :declaringCompany companyName
        :name fullname
        :companyIco companyIco
        :city city
        :paragraph paragraphURI
        :totalAmount totalAmount 
        :currency currency
        :year year
        :customsOffice customsOfficeURI
        :note note})

(defrecord Customs [declaringCompany name companyIco city paragraph totalAmount currency year customsOffice note]
        SesameRepository
         (store [x]         
            (storeRecord customsURI customsMap x)))

(defmethod convert :customs [x type]
(let [[_ declaringCompany _ firstName lastName companyIco _ _ city  paragraph totalAmount currency year customsOffice note1 note2] x]
        (->Customs declaringCompany (str firstName " " lastName) companyIco city paragraph totalAmount currency year customsOffice (str note1 " ; " note2) )))
