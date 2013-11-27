(ns clojsesame.vocabulary
	(:require [clojsesame.sesame :refer :all]))

(def basicNamespace "http://whatever.sk")

;(RDF/TYPE)

(def person (createURI (str basicNamespace "/person/")))
(def fullname (createURI (str basicNamespace "/fullname")))
(def profession (createURI (str basicNamespace "/profession")))
(def city (createURI (str basicNamespace "/city")))
(def party (createURI (str basicNamespace "/party")))
(def occupation (createURI (str basicNamespace "/occupation")))
(def order (createURI (str basicNamespace "/order")))
(def year (createURI (str basicNamespace "/year")))
(def theType (createURI (str basicNamespace "/theType")))
(def note (createURI (str basicNamespace "/note")))
(def sourceUrl (createURI (str basicNamespace "/sourceurl")))
(def supplierIco (createURI (str basicNamespace "/supplierico")))
(def receiver (createURI (str basicNamespace "/receiver")))
(def address (createURI (str basicNamespace "/address")))
(def totalAmount (createURI (str basicNamespace "/totalamount")))
(def currency (createURI (str basicNamespace "/currency")))

(defn triplify [urif property->predicate x property]
	(vector (urif x) (property property->predicate) (-> x property createLiteral)))

(defn storeRecord [urif mapping x]
	(let [triplify (partial triplify urif mapping x)] 
	 		(doseq [[subj predic obj] (map triplify (keys x))]
	 			(insertTriple subj predic obj))))

(defmulti convert 
	(fn[x type] type))