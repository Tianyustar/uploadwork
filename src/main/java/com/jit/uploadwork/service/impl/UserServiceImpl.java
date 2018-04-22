package com.jit.uploadwork.service.impl;

import com.jit.uploadwork.entity.User;
import com.jit.uploadwork.mapper.UserMapper;
import com.jit.uploadwork.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author libre
 * @since 2018-04-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
