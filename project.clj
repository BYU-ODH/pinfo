(defproject byu-odh/pinfo "0.1.0"
  :description "service to return BYU preliminary ws info for a user"
  :url "https://github.com/BYU-ODH/pinfo"
  :min-lein-version "2.0.0"
  :target-path "target/"
  :main pinfo.core
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.apache.logging.log4j/log4j-core "2.7"]
                 [org.apache.logging.log4j/log4j-slf4j-impl "2.7"] ;; TODO learn how to use this better
                 [ring/ring-defaults "0.2.1"]
                 [ring-middleware-format "0.7.0"]
                 ;[org.immutant/web "2.1.0"]
                 [ring-server "0.4.0"]
                 [ring-cors "0.1.8"]
                 [ring/ring-json "0.4.0"]
                 [byu-odh/byu-ws "0.1.1"]]
  :plugins [[lein-ring "0.9.7"]
            ;[lein-immutant "2.1.0"]
            ]
  :ring {:handler pinfo.handler/app}
  :immutant {:war {:name "pid"
                   :main pinfo.core
                   :destination "target/"
                   ;:context-path "/"
                   }}
  :profiles
  {:uberjar {:omit-source true
             :aot :all
             :uberjar-name "pinfo.jar"
             :source-paths ["env/prod/clj"]
             :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/test :profiles/test]

   :project/dev  {:dependencies [[prone "1.1.1"]
                                 [javax.servlet/servlet-api "2.5"]
                                 [ring/ring-mock "0.3.0"]
                                 [ring/ring-devel "1.5.0"]
                                 ;[pjstadig/humane-test-output "0.8.0"]
                                 ;[doo "0.1.6"]
                                 ;[binaryage/devtools "0.7.0"]
                                 ]
                  :plugins      [[com.jakemccrary/lein-test-refresh "0.14.0"]
                                 ;[lein-doo "0.1.6"]
                                 ]
                  
                  :doo {:build "test"}
                  :source-paths ["env/dev/clj" "test/clj"]
                  :resource-paths ["env/dev/resources"]
                  :repl-options {:init-ns user}
                  ;; :injections [(require 'pjstadig.humane-test-output)
                  ;;              (pjstadig.humane-test-output/activate!)]
                  }
   :project/test {:resource-paths ["env/dev/resources" "env/test/resources"]}
   :profiles/dev {}
   :profiles/test {}})
