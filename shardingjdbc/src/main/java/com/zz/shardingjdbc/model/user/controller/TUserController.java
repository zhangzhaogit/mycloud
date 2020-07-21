package com.zz.shardingjdbc.model.user.controller;


import com.zz.shardingjdbc.model.user.entity.TUser;
import com.zz.shardingjdbc.model.user.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangzhao
 * @since 2020-07-15
 */
@RestController
public class TUserController {


    @Autowired
    private ITUserService itUser1Service;

    @GetMapping("/user")
    public void getUser(){
        List<TUser> list = itUser1Service.list();
    }

}
