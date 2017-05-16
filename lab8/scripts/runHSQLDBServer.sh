#!/bin/bash

#java -cp lib/hsqldb.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb
java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:/tmp/mydb --dbname.0 workdb
