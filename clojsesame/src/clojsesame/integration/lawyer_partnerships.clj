(ns clojsesame.integration.lawyer_partnerships
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def lawyerPartnerInfo {:filename "dumps/lawyer_partnerships-dump.csv" :type :lawyerPartner})

(defn- lawyerPartnerURI [{ n :name}] 
	(createURI (str basicNamespace "/lawyerPartner/") n))

(def lawyerPartnerMap { 
	:name fullname
	:type type
	:city city})

(defrecord LawyerPartnership [name type city]
	SesameRepository
	 (store [x]     	
    	(storeRecord lawyerPartnerURI lawyerPartnerMap x)))

(defmethod convert :lawyerPartner [x type]
(let [[_ fullName type _ _ _ _ city] x]
	(->LawyerPartnership fullName type city)))
