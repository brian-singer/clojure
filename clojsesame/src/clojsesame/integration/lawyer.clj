(ns clojsesame.integration.lawyer
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def lawyerInfo {:filename "dumps/lawyers-dump.csv" :type :lawyer})

(def lawyerNamespace (str basicNamespace "/lawyer/"))

(defn- lawyerUuidURI [{ n :name}] 
	(createURI lawyerNamespace n))

(defn- lawyerName [{ n :name :as x}] 
	(vector (lawyerUuidURI x) fullName (createLiteral n)))

(defn- lawyerProfession [{ p :profession :as x}] 
	(vector (lawyerUuidURI x) profession (createLiteral p)))

(defn- lawyerCity [{ c :city :as x}] 
	(vector (lawyerUuidURI x) city (createLiteral c)))

(defrecord Lawyer [name profession city ]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt lawyerName lawyerProfession lawyerCity) x)]
    		(insertTriple subj predic obj))))

(defn- convertName [f m l]
	(if (> 3 (count m)) (str f " " l) (str f " " m " " l)))

(defmethod convert :lawyer [x type]
(let [[_ middleName _ firstName lastName profession _ _ city] x]
	(->Lawyer (convertName firstName middleName lastName) profession city)))
