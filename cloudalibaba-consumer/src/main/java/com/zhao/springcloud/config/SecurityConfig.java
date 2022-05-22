package com.zhao.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2020-05-19 21:33 
 **/


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    // 定义用户信息服务（查询用户信息）
    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser((UserDetails) User.withUsername("zhangsan").password("123").authorities("p1"));
//        manager.createUser((UserDetails) User.withUsername("lisi").password("123").authorities("p2"));
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 定制请求授权规则
         *  antMatchers 匹配路径
         *  permitAll   放行
         *  hasRole  判断角色
         */
        http.authorizeRequests()
                // 所有r 请求须通过认证
                .antMatchers("/r/**").authenticated()
                .antMatchers("/login").permitAll()    //不拦截，直接访问
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN");

        // 关闭CSRF跨域
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("ADMIN")
//                .and()
//                .withUser("user").password("123456").roles("USER");
    }
}