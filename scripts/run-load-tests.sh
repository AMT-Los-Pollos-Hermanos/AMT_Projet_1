#!/bin/bash

ROOT=$(dirname $(dirname $(realpath $0)))
jmeter -n -t $ROOT/jmeter/overflow_test2.jmx -l $ROOT/jmeter/log.jtl -Jjmeter.reportgenerator.overall_granularity=2000 -f -e -o  $ROOT/jmeter/report

URL="file://$ROOT/jmeter/report/index.html"

xdg-open $URL
