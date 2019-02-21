package org.qpyong.demos.springcloud.ms.user;

import org.qpyong.demos.springcloud.ms.user.domain.User;
import org.qpyong.demos.springcloud.ms.user.domain.UserRepository;
import org.qpyong.demos.springcloud.ms.user.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/user")
public class SpringCloudMsUserApplication {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(path = "/list")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /**
     * 列出可观看的电影
     *
     * @return
     */
    @GetMapping(path = "/movies")
    public List<Movie> listMovies() {
        restTemplate.getForObject("http://ms-movie/movie/list",)
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMsUserApplication.class, args);
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
