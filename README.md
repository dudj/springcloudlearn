# springcloudlearn
微服务架构学习

master主分支简介

discovery-eureka 与 discovery-eureka-ha 两者之间通过域名做了互相注册功能

discovery-eureka-authenticating进行了security的验证 使用了config配置

comsumer-movie和provider-user 两者通过用户名和密码向discovery-eureka-authenticating进行注册


----------------------------
整合ribbon的方法
一 单纯整合ribbon
引入ribbon依赖
<dependency>    
    <groupId>org.springframework.cloud</groupId>    
    <artifactId>spring-cloud-starter-ribbon</artifactId>
</dependency>
如果依赖中添加了spring-cloud-starter-eureka，就无须再次引入，里面包含

为RestTemplate添加@LoadBalanced

我已经封装过restTemplate 直接添加即可
@Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
二 使用Java代码自定义配置
    自己定义一个ribbon的配置类，使用随机分配的原则进行访问
    -----------------------------