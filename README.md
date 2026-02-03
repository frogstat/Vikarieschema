# Subsitute assignment

This is a simple program for an assignment that hosts a website with information from a database file.

# Explanation

When the program is compiled and running, a locally hosted server will be started. This server will host the contents of the database vikare.db.

You can find this database in www/WEB-INF/resources/vikarie.db

This program uses port 8080.

supported formats are: json, xml.

supported types to search by are substitute_id and day, both individually and together.

example URL: http://localhost:8080/v1?format=json&substitute_id=1&day=2018-01-15


# Instructions

run compile_and_run.sh to start the server.

test_status_codes.sh tests status codes for the website to make sure they're right.

Usage on bash:

```
git clone https://github.com/frogstat/Vikarieschema.git

cd Vikarieschema

chmod u+x *.sh

./compile_and_run.sh

******
Optional tests:

In seperate terminal:

lwp-request -m HEAD http://localhost:8080/v1?format=json

./test_status_codes.sh

```
If using Powershell, please don't.
