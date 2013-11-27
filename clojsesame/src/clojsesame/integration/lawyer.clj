(ns clojsesame.integration.lawyer
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def lawyerInfo {:filename "dumps/lawyers-dump.csv" :type :lawyer})

(defn- lawyerURI [{ n :name}] 
	(createURI (str basicNamespace "/lawyer/") n))

(def lawyerMap { 
	:name fullname
	:profession profession
	:city city})

(defrecord Lawyer [name profession city ]
	SesameRepository
	 (store [x]     	
    	(storeRecord lawyerURI lawyerMap x)))

(defn- convertName [f m l]
	(if (> 3 (count m)) (str f " " l) (str f " " m " " l)))

(defmethod convert :lawyer [x type]
(let [[_ middleName _ firstName lastName profession _ _ city] x]
	(->Lawyer (convertName firstName middleName lastName) profession city)))
