# Subsitute assignment

This is a simple program for an assignment that hosts a website with information from a database file.

Supports only JSON and XML.

# Instructions

run compile_and_run.sh to start the server.

test_head.sh tests status codes for the website to make sure they're right.

Usage:
```
git clone https://github.com/frogstat/Vikarieschema.git

cd Vikarieschema

chmod u+x *.sh

./compile_and_run.sh

******
Optional tests:

In seperate terminal:

lwp-request -m HEAD http://localhost:8080/v1?format=json

./test_head.sh

```
