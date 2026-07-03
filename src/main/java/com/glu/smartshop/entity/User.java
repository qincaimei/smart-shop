package com.glu.smartshop.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class User implements UserDetails {

    private Integer id;
    private String username;
    private String password;
    private Integer active; // 1可用 0禁用
    // 关联角色集合
    private List<Role> roles;
    private String role;

    // ========== 权限封装关键方法 ==========
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Role实体里的role字段值已经自带 ROLE_admin、ROLE_normal
        // 禁止二次拼接ROLE_前缀，否则权限变成 ROLE_ROLE_admin 直接失效
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // 账号未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号未锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 凭证密码未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 根据active字段判断账号是否启用
    @Override
    public boolean isEnabled() {
        return this.active == 1;
    }
}