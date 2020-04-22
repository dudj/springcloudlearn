package com.ld.microserviceconsumermovie.fallback;

import com.ld.microserviceconsumermovie.openfeign.UserOpenFeignClient;
import com.ld.microserviceconsumermovie.vo.UserVo;
import org.springframework.stereotype.Component;

/**
 * 回退类有多种方式：2.直接public 方式
 */
@Component
public class FeignClientFallbackV1 {
    public UserVo findById(Integer id) {
        UserVo userVo = new UserVo();
        userVo.setName("默认用户");
        userVo.setId(-1);
        return userVo;
    }
}
