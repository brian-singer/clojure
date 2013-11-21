(ns clojsesame.vocabulary
	(:require [clojsesame.sesame :refer :all]))

(def basicNamespace "http://whatever.sk")

;(RDF/TYPE)

(def person (createURI (str basicNamespace "/person")))
(def fullName (createURI (str basicNamespace "/fullname")))
(def profession (createURI (str basicNamespace "/fullname")))

(defprotocol SesameRepository 
	(store [x]))

