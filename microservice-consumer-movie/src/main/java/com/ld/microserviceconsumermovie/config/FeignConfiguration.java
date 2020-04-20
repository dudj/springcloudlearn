package com.ld.microserviceconsumermovie.config;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign的配置类
 * 不应该在主应用程序上下文的@ComponentScan中
 */
@Configuration
public class FeignConfiguration {
    /**
     * 将契约改为feign原生的默认契约，这样可以使用feign自带的注解
     * @return
     */
    /*@Bean
    public Contract feignContract(){
        return new feign.Contract.Default();
    }*/
    //为FeignConfiguration添加链接eureka的权限
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "password123");
    }

}
