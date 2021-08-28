FROM ghcr.io/graalvm/graalvm-ce:20.3.3

RUN curl -O https://raw.githubusercontent.com/technomancy/leiningen/stable/bin/lein && \
    chmod +x lein && \
    mv lein /usr/bin/lein && \
    lein upgrade

RUN gu install native-image

COPY . ./
RUN lein native-image

CMD target/app