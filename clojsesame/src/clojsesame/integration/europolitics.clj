(ns clojsesame.integration.europolitics
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def europoliticsInfo {:filename "dumps/europolitika-dump.csv" :type :europolitics})

(defn- europoliticsURI [{ n :name}] 
	(createURI (str basicNamespace "/europolitics/") n))

(def europoliticsMap { 
	:name fullname
	:profession profession
	:party party
	:occupation occupation
	:order order
	:year year})

(defrecord Europolitics [name profession party occupation order year]
	SesameRepository
	 (store [x] (storeRecord europoliticsURI europoliticsMap x)))

(defmethod convert :europolitics [x type]
(let [[_ _ firstName lastName _ party _ occupation order _ year] x]
	(->Europolitics (str firstName " " lastName) "europolitics" party occupation order year)))
