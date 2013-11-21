(ns clojsesame.vocabulary
	(:require [clojsesame.sesame :refer :all]))

(def basicNamespace "http://whatever.sk")

;(RDF/TYPE)

(def person (createURI (str basicNamespace "/person/")))
(def fullName (createURI (str basicNamespace "/fullname")))
(def profession (createURI (str basicNamespace "/profession")))
;(def street (createURI (str basicNamespace "/street")))
(def city (createURI (str basicNamespace "/city")))
;(def zip (createURI (str basicNamespace "/zip")))

(defmulti convert 
	(fn[x type] type))