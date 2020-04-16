package com.ld.microserviceprovideruser.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class User {
    @Id
    private Integer id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;
}
