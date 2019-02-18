package org.qpyong.demos.springcloud.ms.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudMsMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMsMovieApplication.class, args);
    }

}
