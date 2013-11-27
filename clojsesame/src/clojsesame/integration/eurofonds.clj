(ns clojsesame.integration.eurofonds
	(:require 
		[clojsesame.vocabulary :refer :all]
		[clojsesame.sesame :refer :all]))

(def eurofondsInfo {:filename "dumps/eurofondy_2007_2013-dump.csv" :type :eurofonds})

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
(def additionalFinancingURI (createURI (str basicNamespace "/additionalfinancing")))
(def beginningURI (createURI (str basicNamespace "/beginning")))
(def finishURI (createURI (str basicNamespace "/finish")))
(def updatedAtURI (createURI (str basicNamespace "/updatedat")))

(defn- eurofondsUuidURI [{ n :name}] 
	(createURI (str basicNamespace "/eurofonds/") n))

(def eurofondsMap { 
	:receiver receiver
	:receiverIco receiverIcoURI
	:projectName projectNameURI
	:itmsCode itmsCodeURI
	:operationProgram operationProgramURI
	:controlEntity controlEntityURI
	:note note
	:totalAmout totalAmount
	:euPart euPartURI
	:govPart govPartURI
	:usedTotal usedTotalURI
	:euUsed euUsedURI
	:govUsed govUsedURI
	:additionalFinancing additionalFinancingURI
	:currency currency
	:beginning beginningURI
	:finish finishURI
	:updatedAt updatedAtURI
	})

(defrecord Eurofonds [receiver receiverIco projectName itmsCode operationProgram controlEntity note totalAmout
 euPart govPart usedTotal euUsed govUsed additionalFinancing currency beginning finish updatedAt]
	SesameRepository
	 (store [x] (storeRecord eurofondsUuidURI eurofondsMap x)))

(defmethod convert :eurofonds [x type]
(let [[_ receiver receiverIco projectName itmsCode operationProgram controlEntity note totalAmout
 euPart govPart usedTotal euUsed govUsed additionalFinancing currency beginning finish updatedAt] x]
	(->Eurofonds receiver receiverIco projectName itmsCode operationProgram controlEntity note totalAmout
 euPart govPart usedTotal euUsed govUsed additionalFinancing currency beginning finish updatedAt)))
