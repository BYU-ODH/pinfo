(ns pinfo.handler
  (:require [compojure
             [core :refer :all]
             [route :as route]]
            [pinfo
             [data :as d]
             [middleware :as middleware]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.util.response :refer [response]]))

(defroutes app-routes
  (GET "/" req (response (d/get-person-info (-> req :session :byu-api-auth-code))))
  (route/not-found "Not Found"))

(def app
  (middleware/wrap-base #'app-routes))
