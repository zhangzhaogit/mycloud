package com.zhao.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2023-03-31 16:05
 **/

@Getter
@Setter
public class MyUserDetail implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String name;

    private String password;

    private boolean status;

    private Long deptId;

    private String email;

    private String mobile;

    private String sex;

    private String avatar;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}