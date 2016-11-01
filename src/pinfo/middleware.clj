(ns pinfo.middleware
  (:require [byu-ws.oauth2 :as oauth]
            [pinfo.config :refer [env]]
            [ring.middleware
             [cors :refer [wrap-cors]]
             [defaults :refer [site-defaults wrap-defaults]]
             [json :as json]
             [reload :refer [wrap-reload]]]
            [ring.util.response :refer [redirect]]))

(defn wrap-byu-api-call [handler]
  (let [api-address (oauth/authkey-GET-url
                     (-> env :client-id)
                     (-> env :site-url))]
    (fn [req]
      (if (get-in req [:session :byu-api-auth-code]) 
        (handler req)
        (if-let [code (get-in req [:params :code])]
          (handler (assoc-in req [:session :byu-api-auth-code] code))
          (redirect api-address))))))

(defn wrap-base [handler]
  (-> handler
      wrap-reload
      wrap-byu-api-call
      (wrap-cors :access-control-allow-origin [#".*"]
                 :access-control-allow-methods [:get :put :post :delete])
      json/wrap-json-response
      (wrap-defaults
        (-> site-defaults
            (assoc-in [:security :anti-forgery] false)
            (dissoc :session)))))
