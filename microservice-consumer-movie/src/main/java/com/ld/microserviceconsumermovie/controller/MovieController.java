package com.ld.microserviceconsumermovie.controller;

import com.ld.microserviceconsumermovie.openfeign.UserOpenFeignClient;
import com.ld.microserviceconsumermovie.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
@Slf4j
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private UserOpenFeignClient userOpenFeignClient;
    @GetMapping("/index/{id}")
    public UserVo index(@PathVariable Integer id){
        return this.restTemplate.getForObject("http://microservice-provider-user/user/index/" + id, UserVo.class);
    }

    /**
     * 使用openfeign 来实现服务之间的通信 也可以调用外部接口
     * @param id
     * @return
     */
    @GetMapping("/index-open-feign/{id}")
    public UserVo indexOpenFeign(@PathVariable Integer id){
        log.info("openfeign实现应用之间的通信，获取id为{}的信息",id);
        return this.userOpenFeignClient.findById(id);
    }
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
