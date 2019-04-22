package com.ww.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "auth_user")
/**
 * @Program: cloud-study
 * @Description: AuthUser 实体类
 * @Author: Sun
 * @Create: 2019-04-19 12:16
 * @Version: 1.0
 */
public class AuthUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String account;
    @Column(length = 64)
    private String pwd;
}