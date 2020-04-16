package com.ld.microserviceconsumermovie.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 配置RestTemplate可以在其他地方直接使用
 * 使用方法：
 * restTemplate.getForObject("http://应用名/msg",返回结果类型.class);
 */
@Component
public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
