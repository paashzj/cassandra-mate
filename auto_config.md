### listen
```
# Address or interface to bind to and tell other Cassandra nodes to connect to.
# You _must_ change this if you want multiple nodes to be able to communicate!
#
# Set listen_address OR listen_interface, not both.
#
# Leaving it blank leaves it up to InetAddress.getLocalHost(). This
# will always do the Right Thing _if_ the node is properly configured
# (hostname, name resolution, etc), and the Right Thing is to use the
# address associated with the hostname (it might not be). If unresolvable
# it will fall back to InetAddress.getLoopbackAddress(), which is wrong for production systems.
#
# Setting listen_address to 0.0.0.0 is always wrong.
#
listen_address: localhost

# Set listen_address OR listen_interface, not both. Interfaces must correspond
# to a single address, IP aliasing is not supported.
# listen_interface: eth0
```

### seed_provider
```
# any class that implements the SeedProvider interface and has a
# constructor that takes a Map<String, String> of parameters will do.
seed_provider:
  # Addresses of hosts that are deemed contact points.
  # Cassandra nodes use this list of hosts to find each other and learn
  # the topology of the ring.  You must change this if you are running
  # multiple nodes!
  - class_name: org.apache.cassandra.locator.SimpleSeedProvider
    parameters:
      # seeds is actually a comma-delimited list of addresses.
      # Ex: "<ip1>,<ip2>,<ip3>"
      - seeds: "127.0.0.1:7000"
```

### replica_filtering_protection
```
# Filtering and secondary index queries at read consistency levels above ONE/LOCAL_ONE use a
# mechanism called replica filtering protection to ensure that results from stale replicas do
# not violate consistency. (See CASSANDRA-8272 and CASSANDRA-15907 for more details.) This
# mechanism materializes replica results by partition on-heap at the coordinator. The more possibly
# stale results returned by the replicas, the more rows materialized during the query.
replica_filtering_protection:
  # These thresholds exist to limit the damage severely out-of-date replicas can cause during these
  # queries. They limit the number of rows from all replicas individual index and filtering queries
  # can materialize on-heap to return correct results at the desired read consistency level.
  #
  # "cached_replica_rows_warn_threshold" is the per-query threshold at which a warning will be logged.
  # "cached_replica_rows_fail_threshold" is the per-query threshold at which the query will fail.
  #
  # These thresholds may also be adjusted at runtime using the StorageService mbean.
  #
  # If the failure threshold is breached, it is likely that either the current page/fetch size
  # is too large or one or more replicas is severely out-of-sync and in need of repair.
  cached_rows_warn_threshold: 2000
  cached_rows_fail_threshold: 32000
```

### enable_drop_compact_storage
```
# Enables the used of 'ALTER ... DROP COMPACT STORAGE' statements on this node.
# 'ALTER ... DROP COMPACT STORAGE' is considered experimental and is not recommended for production use.
enable_drop_compact_storage: false
```

### snapshot_links_per_second
```
# The act of creating or clearing a snapshot involves creating or removing
# potentially tens of thousands of links, which can cause significant performance
# impact, especially on consumer grade SSDs. A non-zero value here can
# be used to throttle these links to avoid negative performance impact of
# taking and clearing snapshots
snapshot_links_per_second: 0
```
