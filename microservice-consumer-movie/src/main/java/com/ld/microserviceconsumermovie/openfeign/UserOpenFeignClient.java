package com.ld.microserviceconsumermovie.openfeign;

import com.ld.microserviceconsumermovie.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * name 可以是任意的客户端名称
 */
//@FeignClient(name = "microservice-provider-user")
public interface UserOpenFeignClient {
    @RequestMapping(value = "/user/index/{id}", method = RequestMethod.GET)
    public UserVo findById(@PathVariable("id") Integer id);
}
