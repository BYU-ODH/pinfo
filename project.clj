(defproject pinfo "0.1.0"
  :description "Utilize BYU basic oauth2 for the preliminary information needed to get netid and personid"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring-middleware-format "0.7.0"]
                 [cheshire "5.6.3"]
                                        ;[org.immutant/web "2.1.0"]
                 [ring-cors "0.1.8"]
                 [ring/ring-json "0.4.0"]
                 [byu-odh/byu-ws "0.1.1"]]
  :plugins [[lein-ring "0.9.7"]
            ;[lein-immutant "2.1.0"]
            ]
  :ring {:handler pinfo.handler/app}
  ;; :immutant {:war {:name "pid"
  ;;                  :main humplus-funding.core
  ;;                  :destination "target/"
  ;;                  ;:destination "/home/torysa/Sites/wildfly-10.0.0.Final/standalone/deployments"
  ;;                  :context-path "/"}}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
