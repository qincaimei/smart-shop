package com.glu.smartshop.entity;

import lombok.Data;

@Data
public class Role {
    private Integer id;
    private String role;  // ROLE_admin 或 ROLE_normal
}