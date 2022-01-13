#!/bin/bash

mkdir -p $CASSANDRA_HOME/logs

$CASSANDRA_HOME/bin/cassandra -R >>$CASSANDRA_HOME/logs/cassandra.log 2>>$CASSANDRA_HOME/logs/cassandra_error.log