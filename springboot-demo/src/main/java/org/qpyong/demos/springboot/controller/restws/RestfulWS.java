package org.qpyong.demos.springboot.controller.restws;

import org.qpyong.demos.springboot.domain.Quote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class RestfulWS {

    @RequestMapping("/consumers/quote")
    public Quote getQuote() {
        /**
         * 使用RestTemplate来请求restful-webservice。
         */
        RestTemplate restTemplate = new RestTemplate();
        // 将返回结果转化为Quote
        return restTemplate.getForObject(URI.create("http://gturnquist-quoters.cfapps.io/api/random"), Quote.class);
    }

}
