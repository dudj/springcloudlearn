package com.ld.microserviceconsumermovie.controller;

import com.ld.microserviceconsumermovie.openfeign.UserOpenFeignClient;
//import com.ld.microserviceconsumermovie.openfeign.UserOpenFeignClientCustom;
import com.ld.microserviceconsumermovie.vo.UserVo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 注意：
 * 由于springCloud从2.1.0以上版本将不再支持FeignClient("服务名")相同 因此我将把之前的代码注释；可以对比查看
 */
@RestController
@RequestMapping("/movie")
@Slf4j
//springCloud 为Feign默认提供的配置类
@Import(FeignClientsConfiguration.class)
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private UserOpenFeignClient userOpenFeignClientLoad;
    private UserOpenFeignClient userOpenFeignClient;
    private UserOpenFeignClient adminOpenFeignClient;
//    @Autowired
//    private UserOpenFeignClientCustom userOpenFeignClientCustom;
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract){
        this.userOpenFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user","123456")).target(UserOpenFeignClient.class,"http://microservice-provider-user/");

        this.adminOpenFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","123456")).target(UserOpenFeignClient.class,"http://microservice-provider-user/");
    }
    //以下配置是在本项目上请求的方法 出现问题熔断
    /*@HystrixCommand(fallbackMethod = "findByIdUserFallback", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
        @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
    },threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
    })*/
    @GetMapping("/user-user/{id}")
    public UserVo findByIdUser(@PathVariable Integer id){
        return this.userOpenFeignClientLoad.findById(id);
    }

    public UserVo findByIdUserFallback(@PathVariable Integer id){
        UserVo userVo = new UserVo();
        userVo.setAge(10);
        userVo.setId(-1);
        userVo.setName("默认用户");
        return userVo;
    }

    @GetMapping("/user-admin/{id}")
    public UserVo findByIdAdmin(@PathVariable Integer id){
        return this.adminOpenFeignClient.findById(id);
    }

    @GetMapping("/index/{id}")
    public UserVo index(@PathVariable Integer id){
        return this.restTemplate.getForObject("http://microservice-provider-user/user/index/" + id, UserVo.class);
    }

    /**
     * 使用openfeign 来实现服务之间的通信 也可以调用外部接口
     * @param id 用户id
     * @return 用户信息
     */
//    @GetMapping("/index-open-feign/{id}")
//    public UserVo indexOpenFeign(@PathVariable Integer id){
//        log.info("openfeign实现应用之间的通信，获取id为{}的信息",id);
//        return this.userOpenFeignClient.findById(id);
//    }

    /**
     * 使用openfeign 自定义 --- 来实现服务之间的通信 也可以调用外部接口
     * @param id 用户id
     * @return 用户信息
     */
//    @GetMapping("/index-open-feign-custom/{id}")
//    public UserVo indexOpenFeignCustom(@PathVariable Integer id){
//        log.info("openfeign 自定义---实现应用之间的通信，获取id为{}的信息",id);
//        return this.userOpenFeignClientCustom.findById(id);
//    }


    /**
     * 通过自带ribbon进行负载均衡调用 我通过在idea中配置-Dserver-port=端口号启动了多个用户服务
     * 可以看到每次请求会请求不同的服务，通过log或者此方法都可以看到
     */
    @GetMapping("log-instance")
    public void logUserInstance(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
        serviceInstance.getServiceId();
        log.info("serviceId:{},host:{},port:{}",serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }
}
