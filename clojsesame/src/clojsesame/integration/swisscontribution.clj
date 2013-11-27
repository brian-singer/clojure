(ns clojsesame.integration.swisscontribution
        (:require
                [clojsesame.vocabulary :refer :all]
                [clojsesame.sesame :refer :all]))

(def swisscontribInfo {:filename "dumps/eeanorwaygrants_swissenlargementcontribution-dump.csv" :type :swisscontrib})

(defn- swisscontribURI [x]
        (createURI (str basicNamespace "/swisscontrib/") (:applicant x)))

(def applicantURI (createURI (str basicNamespace "/applicant/")))
(def partnerURI (createURI (str basicNamespace "/partner/")))
(def grantApprovedURI (createURI (str basicNamespace "/grantApproved/")))
(def providerURI (createURI (str basicNamespace "/provider/")))
(def projectStatusURI (createURI (str basicNamespace "/projectStatus/")))
(def projectNumberURI (createURI (str basicNamespace "/projectNumber/")))
(def priorityAreaURI (createURI (str basicNamespace "/priorityArea/")))
(def programSectorURI (createURI (str basicNamespace "/programSector/")))
(def approvalDateURI (createURI (str basicNamespace "/approvalDate/")))

(def swisscontribMap {
        :applicant applicantURI
        :partner partnerURI
        :grantApproved grantApprovedURI
        :currency currency
        :provider providerURI
        :projectTitle fullname 
        :projectStatus projectStatusURI
        :projectNumber projectNumberURI
        :priorityArea priorityAreaURI
        :programSector programSectorURI
        :totalAmount totalAmount
        :note note
        :year year
        :approvalDate approvalDateURI})

(defrecord SwissContrib [applicant partner grantApproved currency provider projectTitle projectStatus
         projectNumber priorityArea programSector totalAmount note year approvalDate]
        SesameRepository
         (store [x]         
            (storeRecord swisscontribURI swisscontribMap x)))

(defmethod convert :swisscontrib [x type]
(let [[_ applicant partner grantApproved currency provider projectTitle 
	projectStatus projectNumber priorityArea programSector totalAmount note year approvalDate] x]
        (->SwissContrib applicant partner grantApproved currency provider projectTitle projectStatus
         projectNumber priorityArea programSector totalAmount note year approvalDate)))
