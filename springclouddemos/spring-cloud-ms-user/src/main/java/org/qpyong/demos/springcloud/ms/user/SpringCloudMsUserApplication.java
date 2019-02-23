package org.qpyong.demos.springcloud.ms.user;

import org.apache.commons.lang.math.NumberUtils;
import org.qpyong.demos.springcloud.ms.user.domain.User;
import org.qpyong.demos.springcloud.ms.user.domain.UserRepository;
import org.qpyong.demos.springcloud.ms.user.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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


    @GetMapping(path = "/get/{userId}")
    public User getUser(@PathVariable String userId) {
        return userRepository.findOne(NumberUtils.toLong(userId));
    }

    /**
     * 使用<code>restTemplate</code>的<code>getForEntity</code>方法访问微服务，并返回多个实体
     * <p>如果需要设置多个参数，则传入第三个参数：可变列表或map
     *
     * @return
     */
    @GetMapping(path = "/movies")
    public List<Movie> listMovies() {
        String url = "http://ms-movie/movie/list";
        // 返回数组
        ResponseEntity<Movie[]> response = this.restTemplate.getForEntity(url, Movie[].class);
        return Arrays.asList(response.getBody());
    }

    /**
     * 使用<code>restTemplate</code>的<code>exchange</code>方法访问微服务
     * <pre>
     *     restTemplate.exchange()
     * </pre>
     *
     * @return
     */
    @GetMapping(path = "/movies/{id}/exchange")
    public Movie getMovieByExchange(@PathVariable String id) {
        String url = "http://ms-movie/movie/get/{1}";
        Movie movie = new Movie();
        movie.setId(NumberUtils.createLong(id));
        HttpEntity<Movie> request = new HttpEntity<>(movie);
        ResponseEntity<Movie> response = this.restTemplate.exchange(url, HttpMethod.GET, request, Movie.class);
        return response.getBody();
    }

    /**
     * 使用<code>restTemplate</code>的<code>getForEntity</code>方法访问微服务，并返回单个实体
     * <pre>
     *     restTemplate.getForEntity()
     * </pre>
     *
     * @return
     */
    @GetMapping(path = "/movies/{id}")
    public Movie getMovieByEntity(@PathVariable String id) {
        String url = "http://ms-movie/movie/get/{1}";
        ResponseEntity<Movie> movie = restTemplate.getForEntity(url, Movie.class, id);
        return movie.getBody();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMsUserApplication.class, args);
    }


    @Bean
    /**
     * 负载均衡Ribbon的启用
     */
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
