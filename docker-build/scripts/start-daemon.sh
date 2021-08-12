#!/bin/bash

cd "$(dirname "$0")"

cd ..

echo `pwd`

mkdir /opt/sh/cassandra/logs

java -Xmx128M -Xms128M -XX:MaxDirectMemorySize=256M -jar /opt/sh/cassandra/mate/cassandra-mate.jar >/opt/sh/cassandra/logs/cassandra-mate-console.log 2>/opt/sh/cassandra/logs/cassandra-mate-error.log