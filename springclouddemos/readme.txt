1.0.2-spring cloud之RestTemplate使用
org.qpyong.demos.springcloud.ms.user.SpringCloudMsUserApplication
RestTemplate.getForObject、getForEntity、exchange、postXXX

1.0.3-Spring cloud之Ribbon负载均衡
1)启动两个movie服务，分别指定spring.profiles.active=server1及server2</li>
2)启动user服务，指定spring.profiles.active=server0</li>
3)请求user服务的api：http://localhost:8780/user/movies/2</li>
4)观察movie服务的日志输出</li>