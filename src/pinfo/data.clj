(ns pinfo.data
  (:require [byu-ws.oauth2 :as oauth]
            [pinfo.config :refer [env]]
            [clojure.tools.logging :as log]))

(defn get-person-info [auth-code]
  (let [post-map {:client-id (-> env :client-id)
                  :client-secret (-> env :client-secret)
                  :redirect-uri (-> env :site-url)
                  :authorization-code auth-code}
        result    (oauth/api-user-data post-map) ]
    (log/info "User: " result)
    result))

