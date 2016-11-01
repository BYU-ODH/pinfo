(ns pinfo.core
  (:require [pinfo
             [config :refer [env]]
             [handler :as handler]]
            [ring.server.standalone :refer [serve]])
  (:gen-class))

(defn -main [& args]
  (serve handler/app {:open-browser? false}))

