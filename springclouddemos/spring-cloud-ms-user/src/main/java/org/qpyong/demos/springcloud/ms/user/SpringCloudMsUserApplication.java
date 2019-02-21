package org.qpyong.demos.springcloud.ms.user;

import org.qpyong.demos.springcloud.ms.user.domain.User;
import org.qpyong.demos.springcloud.ms.user.domain.UserRepository;
import org.qpyong.demos.springcloud.ms.user.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/user")
public class SpringCloudMsUserApplication {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/list")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @GetMapping(path = "/watch")
    public List<Movie> hasWatched() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("ms-movie");
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMsUserApplication.class, args);
    }

}
