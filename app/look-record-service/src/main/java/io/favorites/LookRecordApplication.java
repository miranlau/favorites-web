package io.favorites;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LookRecordApplication {

    public static void main(String[] args) {
        SpringApplication.run(LookRecordApplication.class, args);
    }
}