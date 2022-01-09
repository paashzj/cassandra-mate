package com.github.shoothzj.cassandra.mate.service;

import com.github.shoothzj.cassandra.mate.config.CassandraConfig;
import com.github.shoothzj.cassandra.mate.constant.PathConst;
import com.github.shoothzj.javatool.util.ShellUtil;
import com.github.shoothzj.javatool.util.StringUtil;
import com.github.shoothzj.sdk.net.Ipv4Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author hezhangjian
 */
@Slf4j
@Service
public class LifecycleService {

    @PostConstruct
    public void init() throws Exception {
        // change config
        genConfig();
        // start cassandra
        ShellUtil.executeCmd("bash -x /opt/sh/cassandra/mate/scripts/start-cassandra.sh");
    }

    /**
     * 关键的配置
     * listen_address
     * listen_interface
     * seed_provider
     */
    public void genConfig() throws Exception {
        try {
            Files.deleteIfExists(Paths.get(PathConst.CASSANDRA_CONFIG));
        } catch (IOException e) {
            log.error("delete file failed ", e);
        }
        FileUtils.copyFile(new File(PathConst.CASSANDRA_ORIGINAL_CONFIG), new File(PathConst.CASSANDRA_CONFIG));
        try (FileWriter fw = new FileWriter(PathConst.CASSANDRA_CONFIG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            if (StringUtil.isEmpty(CassandraConfig.cassandraInterface)) {
                // localhost
                out.println("listen_address: localhost");
                out.println("seed_provider:");
                out.println("    - class_name: org.apache.cassandra.locator.SimpleSeedProvider");
                out.println("      parameters:");
                out.println("          - seeds: \"127.0.0.1:7000\"");
            } else {
                out.println("listen_interface: " + CassandraConfig.cassandraInterface);
                out.println("seed_provider:");
                out.println("    - class_name: org.apache.cassandra.locator.SimpleSeedProvider");
                out.println("      parameters:");
                final String seedIp = Ipv4Util.getIp(CassandraConfig.cassandraInterface);
                out.println("          - seeds: \"" + seedIp + ":7000\"");
                log.info("seed ip is {}", seedIp);
            }
            out.flush();
        }
        log.info("write cassandra config over.");
    }

}
