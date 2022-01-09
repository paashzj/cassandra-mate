#!/bin/bash

mkdir $CASSANDRA_HOME/logs

java -Xmx128M -Xms128M -XX:MaxDirectMemorySize=256M -jar $CASSANDRA_HOME/mate/cassandra-mate.jar >>$CASSANDRA_HOME/logs/cassandra_mate.stdout.log 2>>$CASSANDRA_HOME/logs/cassandra_mate.stderr.log
