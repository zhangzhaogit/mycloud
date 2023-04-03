package com.zhao.auth.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2023-03-31 16:04
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
    * @Description:  用户认证
    * @Param: [username]
    * @Return: org.springframework.security.core.userdetails.UserDetails
    * @Author: zhangzhao
    * @Date: 2023/4/3 19:27
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
            todo
             密码验证 1.注入service获取db数据 或 2.feign获取数据
             密码错误次数过多限制提示
         */
        MyUserDetail userDetail = new MyUserDetail();
        String dbUsername = "6";
        if (!username.equals(dbUsername)) {
            throw new BadCredentialsException("密码错误");

        }
        // 注意数据库中保存的密码，要是加密过的，就是在配置类中设置的加密方法
        if (!userDetail.isEnabled()) {
            throw new DisabledException("账号状态异常！");
        } else if (!userDetail.isCredentialsNonExpired()) {
            throw new LockedException("密码过期！");
        }

        // 模拟一点角色权限信息，角色前面要加 ROLE_ 前缀
//        userDetail.setAuthorities(userDetail.authorities("/test/t1", "/test/t2", "ROLE_ADMIN", "ROLE_ROOT"));

        return userDetail;
    }

}