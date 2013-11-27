(ns clojsesame.integration.lawyer_assoc
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def lawyerAssocInfo {:filename "dumps/lawyer_associates-dump.csv" :type :lawyerAssoc})

(defn- lawyerAssocURI [{ n :name}] 
	(createURI (str basicNamespace "/lawyerAssoc/") n))

(def lawyerAssocMap { 
	:name fullname
	:profession profession
	:city city})

(defrecord LawyerAssociates [name profession]
	SesameRepository
	 (store [x]     	
    	(storeRecord lawyerAssocURI lawyerAssocMap x)))

(defmethod convert :lawyerAssoc [x type]
(let [[_ _ firstName lastName] x]
	(->LawyerAssociates (str firstName " " lastName) "lawyerAssociate")))
