#
# Build stage
#
FROM ttbb/compile:jdk11-git-mvn AS build
COPY . /opt/sh/compile
WORKDIR /opt/sh/compile
RUN mvn -B clean package


FROM ttbb/cassandra:nake

LABEL maintainer="shoothzj@gmail.com"

COPY --from=build /opt/sh/compile/cassandra-mate/target/cassandra-mate-0.0.1.jar /opt/sh/cassandra/mate/cassandra-mate.jar

COPY docker-build /opt/sh/cassandra/mate

COPY config/cassandra-original.yaml /opt/sh/cassandra/conf/cassandra-original.yaml

RUN rm -f /opt/sh/cassandra/conf/cassandra.yaml

WORKDIR /opt/sh/cassandra

CMD ["/usr/local/bin/dumb-init", "bash", "-vx","/opt/sh/cassandra/mate/scripts/start.sh"]