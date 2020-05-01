1-spring cloud之dicovery  eureka的使用
1)启动单机eureka服务，指定参数spring.profile.active=server
或者启动高可用eureka服务，指定第1台服务参数spring.profile.active=servers1
以及指定第1台服务参数spring.profile.active=servers1servers2

1.0.2-spring cloud之RestTemplate使用
org.qpyong.demos.springcloud.ms.user.SpringCloudMsUserApplication
RestTemplate.getForObject、getForEntity、exchange、postXXX

1.0.3-Spring cloud之Ribbon负载均衡
1)在pom文件中增加ribbon依赖
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-ribbon</artifactId>
</dependency>
2)在ms-user服务中的配置对象的RestTemplate restTemplate()方法上增加 @LoadBalanced注解
3)启动两个movie服务，分别指定spring.profiles.active=server1及server2</li>
4)启动user服务，指定spring.profiles.active=server0</li>
5)请求user服务的api：http://localhost:8780/user/movies/2</li>
6)观察movie服务的日志输出</li>