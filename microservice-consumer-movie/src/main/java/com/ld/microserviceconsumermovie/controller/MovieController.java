package com.ld.microserviceconsumermovie.controller;

import com.ld.microserviceconsumermovie.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/index/{id}")
    public UserVo index(@PathVariable Integer id){
        return this.restTemplate.getForObject("http://microservice-provider-user/user/index/" + id, UserVo.class);
    }
}
