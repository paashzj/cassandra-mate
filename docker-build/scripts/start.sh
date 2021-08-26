#!/bin/bash

mkdir /opt/sh/cassandra/logs

java -Xmx128M -Xms128M -XX:MaxDirectMemorySize=256M -jar /opt/sh/cassandra/mate/cassandra-mate.jar >/opt/sh/cassandra/logs/cassandra_mate.stdout.log 2>/opt/sh/cassandra/logs/cassandra_mate.stderr.log
tail -f /dev/null