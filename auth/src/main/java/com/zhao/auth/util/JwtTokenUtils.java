package com.zhao.auth.util;

import com.zz.jwt.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2023-03-31 19:08 
 **/
public class JwtTokenUtils {


    /**
     * 用户名称
     */
    private static final String USERNAME = "username";
    /**
     * 创建时间
     */
    private static final String CREATED = "created";
    /**
     * 权限列表
     */
    private static final String AUTHORITIES = "authorities";
    /**
     * 有效期12小时
     */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;
    /**
     * 密钥
     */
    public static final String DEFAULT_SECRET_KEY = "zzxisapowerfulmicroservicearchitectureupgradedandoptimizedfromacommercialproject";

    /**
     * 生成令牌
     *
     * @return 令牌
     */
    public static String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put(USERNAME, SecurityUtils.getUsername(authentication));
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES, authentication.getAuthorities());
        return generateToken(claims);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims) {
        return JwtUtil.generateToken(claims,EXPIRE_TIME,DEFAULT_SECRET_KEY);
    }


    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUsernameFromToken(String token,String secret) {
        String username;
        try {
            Claims claims = JwtUtil.parseJWT(token, secret);
            username = (String) claims.get(USERNAME);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

}