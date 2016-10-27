(ns pinfo.data
  (:require [byu-ws.oauth2 :as oauth]
            [pinfo.config :refer [env]]))

(defn get-person-info [auth-code]
  (let [post-map {:client-id (-> env :client-id)
                  :client-secret (-> env :client-secret)
                  :redirect-uri (-> env :site-url)
                  :authorization-code auth-code}]
                                        ;(json/generate-string (oauth/api-user-data post-map))
    (oauth/api-user-data post-map)
    ))

