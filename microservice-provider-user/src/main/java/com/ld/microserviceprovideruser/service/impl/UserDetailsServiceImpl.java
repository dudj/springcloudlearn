package com.ld.microserviceprovideruser.service.impl;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public User loadUserByUsername(String username) {
        if("user".equals(username)){
            return new User("user","123456", AuthorityUtils.createAuthorityList("NORMAL"));
        }else if("admin".equals(username)){
            return new User("admin","123456", AuthorityUtils.createAuthorityList("ADMIN"));
        }
        return null;
    }
}
