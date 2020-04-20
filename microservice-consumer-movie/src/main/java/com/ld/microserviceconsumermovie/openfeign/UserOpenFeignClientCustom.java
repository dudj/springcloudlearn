package com.ld.microserviceconsumermovie.openfeign;

import com.ld.microserviceconsumermovie.config.FeignConfiguration;
import com.ld.microserviceconsumermovie.vo.UserVo;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * feign自定义配置 使用@FeignClient的configuration属性，指定feign的配置类
 */
//@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserOpenFeignClientCustom {
    /**
     * 使用feign自带的注解@RequestLine
     * @param id 用户id
     * @return 用户信息
     */
    @RequestLine("GET /user/index/{id}")
    public UserVo findById(@Param("id") Integer id);
}
