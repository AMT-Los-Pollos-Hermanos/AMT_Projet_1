#!/bin/bash

set -e

mvn package
cp target/OverFlow-1.0-SNAPSHOT.war docker/images/flow/OverFlow-1.0-SNAPSHOT.war
docker build --tag=overflow-wildfly docker/images/flow/
docker run -d --rm -p 8080:8080 --name overflow-wildfly overflow-wildfly
echo "SUCCESS : Go to http://localhost:8080/OverFlow-1.0-SNAPSHOT/"
