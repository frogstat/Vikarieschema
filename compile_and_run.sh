#!/bin/bash

echo "sites:"
echo "  http://localhost:8080/v1?format=json"
echo "  http://localhost:8080/v1?format=xml"
echo ""

javac -cp .:www/WEB-INF/classes/:winstone.jar:www/WEB-INF/lib/android-json-6.0.1.10.jar www/WEB-INF/classes/se/yrgo/schedule/*/*.java && java -jar winstone.jar --webroot=www
