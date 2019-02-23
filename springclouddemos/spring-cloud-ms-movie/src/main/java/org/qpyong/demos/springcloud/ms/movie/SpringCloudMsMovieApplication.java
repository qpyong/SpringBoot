package org.qpyong.demos.springcloud.ms.movie;

import com.netflix.discovery.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.qpyong.demos.springcloud.ms.movie.domain.Movie;
import org.qpyong.demos.springcloud.ms.movie.domain.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/movie")
@EnableDiscoveryClient
public class SpringCloudMsMovieApplication {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping(path = "/list")
    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    @GetMapping(path = "/get/{id}")
    public Movie getMovie(@PathVariable String id) {
        return movieRepository.findOne(NumberUtils.toLong(id));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudMsMovieApplication.class, args);
    }

}
