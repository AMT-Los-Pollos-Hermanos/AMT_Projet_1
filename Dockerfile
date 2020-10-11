FROM openliberty/open-liberty:kernel-java8-openj9-ubi

ARG VERSION=1.0
ARG REVISION=SNAPSHOT

LABEL \
  org.opencontainers.image.authors="Los Pollos Hermanos" \
  org.opencontainers.image.vendor="HEIG" \
  org.opencontainers.image.source="https://github.com/AMT-Los-Pollos-Hermanos/AMT_Projet_1" \
  org.opencontainers.image.version="$VERSION" \
  org.opencontainers.image.revision="$REVISION"

COPY --chown=1001:0 target/*.war /config/dropins/
COPY --chown=1001:0 src/main/liberty/config/server.xml /config/
COPY --chown=1001:0 src/main/liberty/config/mysql-connector-java-8.0.21.jar /config/

RUN configure.sh
