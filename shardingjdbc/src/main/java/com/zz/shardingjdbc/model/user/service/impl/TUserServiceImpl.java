package com.zz.shardingjdbc.model.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zz.shardingjdbc.model.user.entity.TUser;
import com.zz.shardingjdbc.model.user.mapper.TUserMapper;
import com.zz.shardingjdbc.model.user.service.ITUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangzhao
 * @since 2020-07-15
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
