## boot
### boot as eth0
docker run -e CASSANDRA_INTERFACE=eth0 -p 9042:9042 -p 7000:7000 ttbb/cassandra:mate