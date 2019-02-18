package org.qpyong.demos.springcloud.ms.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudMsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMsUserApplication.class, args);
    }

}
