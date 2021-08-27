# clj-lein-graal-example

Testing native-image compilation in a leiningen-project using the related [lein-native-image](https://github.com/taylorwood/lein-native-image).

![example workflow](https://github.com/joakimen/clj-lein-graal-example/actions/workflows/clojure.yml/badge.svg)

## Setup

### Install GraalVM using Jabba

```shell

# install Jabba
curl -sL https://github.com/shyiko/jabba/raw/master/install.sh | bash && . ~/.jabba/jabba.sh

# install and enable GraalVM using Jabba
jabba install graalvm-ce-java11@20.3
jabba use graalvm-ce-java11@20.3

# install the native-image extension for GraalVM using Graal-updater
gu install native-image

```

### Configure leiningen-plugin for native-image compilation

in `project.clj` , add the following sections:

```clojure
;; add plugin 
:plugins [[io.taylorwood/lein-native-image "0.3.1"]]

;; specify some GraalVM-flags
:native-image {:name "clj-lein-graal-example-native"
               :opts ["--report-unsupported-elements-at-runtime"
                      "--initialize-at-build-time"]}
```

### Build both jar and native-image for comparison

```sh
# build jar
lein uberjar

# build native-image
lein native-image
```

### Run jar and native-image for comparison

```sh
# Run jar
time java -jar target/uberjar/clj-lein-graal-example-0.1.0-standalone.jar
Hello World!
java -jar target/uberjar/lein-graal-example-0.1.0-SNAPSHOT-standalone.jar
1.46s user 0.18s system 149% cpu 1.091 total

# Run native-image
time ./target/default+uberjar/clj-lein-graal-example-native
Hello, World!
./target/default+uberjar/clj-lein-graal-example-native
0.00s user 0.00s system 73% cpu 0.010 total
```
