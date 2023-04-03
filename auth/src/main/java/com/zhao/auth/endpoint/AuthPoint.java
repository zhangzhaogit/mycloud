package com.zhao.auth.endpoint;

import com.zhao.auth.config.JwtAuthenticatioToken;
import com.zhao.auth.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: zhangzhao
 * @Date: 2023-03-31 15:50 
 **/
@RestController
@AllArgsConstructor
public class AuthPoint {

    private AuthenticationManager authenticationManager;

    /**
    * @Description:  登陆 返回token
    * @Param: [request]
    * @Return: java.lang.String
    * @Author: zhangzhao
    * @Date: 2023/4/2 9:43
    */
    @GetMapping("/auth")
    public String login (HttpServletRequest request) {
        // todo 账号密码改为参数获取
        String username = "user";
        String password = "password";
        // 系统登录认证
        JwtAuthenticatioToken authenticatioToken = SecurityUtils.login(request, username, password, authenticationManager);
        // 解析token
//        Claims claims = JwtUtil.parseJWT(authenticatioToken.getToken(), JwtTokenUtils.DEFAULT_SECRET_KEY);
        return authenticatioToken.getToken();
    }

}