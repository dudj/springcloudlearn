package com.ld.microserviceconsumermovie.controller;

import com.ld.microserviceconsumermovie.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 使用ribbonClient，为特定name的Ribbon Client自定义配置
 * 使用@RibbonClient的configuration属性，指定Ribbon的配置类
 */
@Configuration
@RibbonClient(name = "microservice-provider-user", configuration = RibbonConfiguration.class)
public class TestRibbonController {

}
