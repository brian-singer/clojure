(ns clojsesame.integration.eurofonds
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def eurofondsInfo {:filename "dumps/eurofondy_2007_2013-dump.csv" :type :eurofonds})

(def eurofondsNamespace (str basicNamespace "/eurofonds/"))

(def receiverIcoURI (createURI (str basicNamespace "/receiverico")))
(def projectNameURI (createURI (str basicNamespace "/projectname")))
(def itmsCodeURI (createURI (str basicNamespace "/itmscode")))
(def operationProgramURI (createURI (str basicNamespace "/operationprogram")))
(def controlEntityURI (createURI (str basicNamespace "/controlentity")))
(def euPartURI (createURI (str basicNamespace "/euPart")))
(def govPartURI (createURI (str basicNamespace "/govPart")))
(def usedTotalURI (createURI (str basicNamespace "/usedTotal")))
(def euUsedURI (createURI (str basicNamespace "/euUsed")))
(def govUsedURI (createURI (str basicNamespace "/govUsed")))
(def additionalFinancingURI (createURI (str basicNamespace "/additionalFinancing")))
(def beginningURI (createURI (str basicNamespace "/beginning")))
(def finishURI (createURI (str basicNamespace "/finish")))
(def updatedAtURI (createURI (str basicNamespace "/updatedAt")))


(defn- eurofondsUuidURI [{ n :name}] 
	(createURI eurofondsNamespace n))

(defn- rc [{ n :receiver :as x}] 
	(vector (eurofondsUuidURI x) receiverURI (createLiteral n)))

(defn- rcico [{ n :receiverIco :as x}] 
	(vector (eurofondsUuidURI x) receiverIcoURI (createLiteral n)))

(defn- prname [{ n :projectName :as x}] 
	(vector (eurofondsUuidURI x) projectNameURI (createLiteral n)))

(defn- code [{ n :itmsCode :as x}] 
	(vector (eurofondsUuidURI x) itmsCodeURI (createLiteral n)))

(defn- opProg [{ n :operationProgram :as x}] 
	(vector (eurofondsUuidURI x) operationProgramURI (createLiteral n)))

(defn- ctrlEntity [{ n :controlEntity :as x}] 
	(vector (eurofondsUuidURI x) controlEntityURI (createLiteral n)))

(defn- euprt [{ n :euPart :as x}] 
	(vector (eurofondsUuidURI x) euPartURI (createLiteral n)))

(defn- govprt [{ n :govPart :as x}] 
	(vector (eurofondsUuidURI x) govPartURI (createLiteral n)))

(defn- usedAll [{ n :usedTotal :as x}] 
	(vector (eurofondsUuidURI x) usedTotalURI (createLiteral n)))

(defn- euused [{ n :euUsed :as x}] 
	(vector (eurofondsUuidURI x) euUsedURI (createLiteral n)))

(defn- addfinance [{ n :additionalFinancing :as x}] 
	(vector (eurofondsUuidURI x) additionalFinancingURI (createLiteral n)))

(defn- ccurrency [{ n :currency :as x}] 
	(vector (eurofondsUuidURI x) currencyURI (createLiteral n)))

(defn- begin1 [{ n :beginning :as x}] 
	(vector (eurofondsUuidURI x) beginningURI (createLiteral n)))

(defn- finish1 [{ n :finish :as x}] 
	(vector (eurofondsUuidURI x) finishURI (createLiteral n)))

(defn- updatedDate [{ n :updatedAt :as x}] 
	(vector (eurofondsUuidURI x) updatedAtURI (createLiteral n)))



(defrecord Eurofonds [receiver receiverIco projectName itmsCode operationProgram controlEntity note totalAmout
 euPart govPart usedTotal euUsed govUsed additionalFinancing currency beginning finish updatedAt]
	SesameRepository
	 (store [x]     	
    	(doseq [[subj predic obj] ((juxt rc rcico prname code opProg ctrlEntity euprt govprt usedAll euused addfinance ccurrency begin1 finish1 updatedDate) x)]
    		(insertTriple subj predic obj))))

(defmethod convert :eurofonds [x type]
(let [[_ receiver receiverIco projectName itmsCode operationProgram controlEntity note totalAmout
 euPart govPart usedTotal euUsed govUsed additionalFinancing currency beginning finish updatedAt] x]
	(->Eurofonds receiver receiverIco projectName itmsCode operationProgram controlEntity note totalAmout
 euPart govPart usedTotal euUsed govUsed additionalFinancing currency beginning finish updatedAt)))
