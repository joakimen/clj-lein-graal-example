(defproject clj-lein-graal-example "0.1.0"
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :plugins [[io.taylorwood/lein-native-image "0.3.1"]]
  :main ^:skip-aot clj-lein-graal-example.core
  :native-image {:name "app"
                 :opts ["--report-unsupported-elements-at-runtime"
                        "--initialize-at-build-time"]}
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
