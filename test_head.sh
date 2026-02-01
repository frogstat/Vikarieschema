#!/bin/bash

fail() {
    echo $1
    exit 1
}

check_status(){
    if [ $1 -eq "000" ]; then
        fail "Could not connect. Is the server up?"
    elif [ ! $1 -eq $2 ]; then
        fail "Error. Got $1 but expected $2"
    fi
}


echo "Testing headers..."
echo ""
echo "Testing valid URL. Should return 200"
my_status=$(curl -o /dev/null --silent --head --write-out '%{http_code}\n' "http://localhost:8080/v1?format=json&substitute_id=1")
check_status $my_status "200"
echo $my_status

echo "Testing valid URL, but invalid substitute_id. Should return 404."
my_status=$(curl -o /dev/null --silent --head --write-out '%{http_code}\n' "http://localhost:8080/v1?format=json&substitute_id=122")
check_status $my_status "404"
echo $my_status

echo "Testing invalid format. Should return 400."
my_status=$(curl -o /dev/null --silent --head --write-out '%{http_code}\n' "http://localhost:8080/v1?format=idunno")
check_status $my_status "400"
echo $my_status
exit 0
