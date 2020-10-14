#!/bin/bash

mvn clean package
mvn liberty:create liberty:install-feature liberty:deploy
mvn liberty:configure-arquillian

mvn failsafe:integration-test

mvn liberty:stop
