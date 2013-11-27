(ns clojsesame.integration.notary_empl
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def notaryEmployeeInfo {:filename "dumps/notary_employees-dump.csv" :type :notaryEmployee})

(defn- notaryEmployeeURI [{ n :name}] 
	(createURI (str basicNamespace "/notaryEmpl/") n))

(def notaryEmployeeMap {:name fullname :profession profession})

(defrecord NotaryEmployee [name profession]
	SesameRepository
	 (store [x] (storeRecord notaryEmployeeURI notaryEmployeeMap x)))

(defmethod convert :notaryEmployee [x type]
(let [[_ firstName lastName] x]
	(->NotaryEmployee (str firstName " " lastName) "notaryEmployee")))
