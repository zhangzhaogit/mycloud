package com.zhao.auth.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Description: 认证配置
 * @Author: zhangzhao
 * @Date: 2023-03-31 11:15 
 **/
@Configuration
@EnableWebSecurity
@Slf4j
@AllArgsConstructor
public class SecurityConfig {

    private final ObjectPostProcessor<Object> objectPostProcessor;
    // 注入服务类
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * 构造一个认证管理器
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();

        // 自测：内存用户信息
//        log.info("初始化 authenticationManager");
//        // objectPostProcessor 是可以直接使用 spring context 的对象, 这个是参考了已废弃的 WebSecurityConfigurerAdapter 得知的
//        AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder(objectPostProcessor);
//        auth.uuserDetailsService(userDetailsServiceImpl)
//        // 创建一些内存中的用户, 用作测试
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("{bcrypt}" + passwordEncoder().encode("password"))
//                .roles("USER")
//
//                .and()
//                .withUser("admin")
//                .password("{bcrypt}" + passwordEncoder().encode("password"))
//                .roles("ADMIN", "USER");
//
//        return auth.build();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("初始化 SecurityFilterChain");
//        http.formLogin();
        return http
                // 用户是通过json请求登录的, 是无状态的, 可以把 csrf 禁用。httpbasic是非安全密码方式
                .csrf().and().httpBasic().disable()
                .antMatcher("/**")
                .authorizeRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("初始化 WebSecurityCustomizer");
        return (web) -> web.ignoring()
                // 忽略常见的静态资源路径
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                // 忽略认证路径
                .antMatchers("/auth-token")
                .antMatchers("/auth");
    }


    /**
     * 密码编码器
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        // 也是验证 passwordEncoder 是否只初始化了一次
        log.info("初始化 passwordEncoder");
        return new BCryptPasswordEncoder();
    }
}