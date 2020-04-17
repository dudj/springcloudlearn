package com.ld.microserviceprovideruser.controller;

import com.ld.microserviceprovideruser.po.User;
import com.ld.microserviceprovideruser.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/user")
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
            for(GrantedAuthority c : collection){
                log.info("当前用户是,角色是{}",c.getAuthority());
            }
        }else{

        }
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional;
    }
}
