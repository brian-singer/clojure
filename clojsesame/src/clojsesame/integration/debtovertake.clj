(ns clojsesame.integration.debtovertake
        (:require
                [clojsesame.vocabulary :refer :all]
                [clojsesame.sesame :refer :all]))

(def debtovertakeInfo {:filename "dumps/konsolidacna-dump.csv" :type :debtovertake})

(defn- debtovertakeURI [x]
        (createURI (str basicNamespace "/debtovertake/") (:companyIco x)))

(def updateDateURI (createURI (str basicNamespace "/updatedate/")))
(def formURI (createURI (str basicNamespace "/form/")))
(def debtNumberURI (createURI (str basicNamespace "/debtnumber/")))

(def debtovertakeMap {
       :companyName companyName
       :companyIco companyIco
       :debtor1 fullname
       :debtor2 fullname
       :debtor3 fullname
       :city city
       :totalAmount totalAmount 
       :currency currency
       :note note
       :updateDate  updateDateURI
       :form formURI
       :debtNumber debtNumberURI})

(defrecord DebtOvertake [companyName companyIco debtor1 debtor2 debtor3 city totalAmount currency note updateDate form debtNumber]
        SesameRepository
         (store [x]         
            (storeRecord debtovertakeURI debtovertakeMap x)))

(defmethod convert :debtovertake [x type]
(let [[_ companyName companyIco _ firstName1 lastName1 city firstName2 totalAmount currency note updateDate form debtNumber _  lastName2 firstName3 lastName3] x]
        (->DebtOvertake companyName companyIco (str firstName1 " " lastName1) (str firstName2 " " lastName2) (str firstName3 " " lastName3) city totalAmount currency note updateDate form debtNumber)))

