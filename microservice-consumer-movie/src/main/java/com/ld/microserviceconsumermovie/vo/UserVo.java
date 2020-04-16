package com.ld.microserviceconsumermovie.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserVo {
    private Integer id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;
}
