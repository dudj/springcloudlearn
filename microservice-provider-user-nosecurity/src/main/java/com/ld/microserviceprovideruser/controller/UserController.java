package com.ld.microserviceprovideruser.controller;

import com.ld.microserviceprovideruser.po.User;
import com.ld.microserviceprovideruser.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user-nosecurity")
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * 加入 安全认证 用户需要登录
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/index/{id}")
    public Optional<User> index(@PathVariable Integer id){
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional;
    }
}
