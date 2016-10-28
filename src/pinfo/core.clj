(ns pinfo.core
  (:require [pinfo.handler :as handler]
            [ring.server.standalone :refer [serve]]))

(defn -main [& args]
  (serve handler/app))

