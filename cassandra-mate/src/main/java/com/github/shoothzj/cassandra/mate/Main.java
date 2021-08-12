package com.github.shoothzj.cassandra.mate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hezhangjian
 */
@EnableScheduling
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

}
