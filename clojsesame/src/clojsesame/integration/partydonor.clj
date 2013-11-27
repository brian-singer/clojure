(ns clojsesame.integration.partydonor
        (:require
                [clojsesame.vocabulary :refer :all]
                [clojsesame.sesame :refer :all]))

(def partydonorInfo {:filename "dumps/sponzori_stran-dump.csv" :type :partydonor})

(defn- partydonorURI [x]
        (createURI (str basicNamespace "/partydonor/") (:name x)))

(def partydonorMap {
        :name fullname
        :donorCompany donorCompany
        :donorIco donorIco
        :amount amount
        :currency currency
        :address address
        :city city
        :party party
        :year year
        :acceptDate acceptDate
        :note note })

(defrecord PartyDonor [name donorCompany donorIco amount currency address city party year acceptDate note]
        SesameRepository
         (store [x]         
            (storeRecord partydonorURI partydonorMap x)))

(defmethod convert :partydonor [x type]
(let [[_ firstName lastName _ donorCompany donorIco amount currency address _ city party year acceptDate note] x]
        (->PartyDonor (str firstName " " lastName) donorCompany donorIco amount currency address city party year acceptDate note)))