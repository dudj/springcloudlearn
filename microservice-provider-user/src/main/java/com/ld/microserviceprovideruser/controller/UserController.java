package com.ld.microserviceprovideruser.controller;

import com.ld.microserviceprovideruser.po.User;
import com.ld.microserviceprovideruser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/index/{id}")
    public Optional<User> index(@PathVariable Integer id){
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional;
    }
}
