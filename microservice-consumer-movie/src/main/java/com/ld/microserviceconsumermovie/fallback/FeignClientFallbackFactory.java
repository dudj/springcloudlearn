package com.ld.microserviceconsumermovie.fallback;

import com.ld.microserviceconsumermovie.openfeign.UserOpenFeignClient;
import com.ld.microserviceconsumermovie.vo.UserVo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * UserOpenFeignClient的fallbackFactory类，该类需要实现FallbackFactory接口，并复写create方法
 * 日志放在各个fallback的方法中，这样方便定位问题。不然在应用启动中就会打印该日志
 */
@Component
@Slf4j
public class FeignClientFallbackFactory implements FallbackFactory<UserOpenFeignClient> {

    @Override
    public UserOpenFeignClient create(Throwable throwable) {
        return new UserOpenFeignClient(){
            @Override
            public UserVo findById(Integer id) {
                log.info("fallback; reason was:{}",throwable);
                UserVo userVo = new UserVo();
                userVo.setId(-1);
                userVo.setName("默认用户1");
                return userVo;
            }
        };
    }
}
