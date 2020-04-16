package com.ld.microservicediscoveryeurekaauthenticating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@SpringBootApplication
@EnableEurekaServer
public class EurekaAuthenticatingApplication{

    public static void main(String[] args) {
        SpringApplication.run(EurekaAuthenticatingApplication.class, args);
    }
}
