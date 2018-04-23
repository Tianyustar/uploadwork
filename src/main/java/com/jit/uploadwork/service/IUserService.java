package com.jit.uploadwork.service;

import com.jit.uploadwork.entity.User;
import com.baomidou.mybatisplus.service.IService;
import com.jit.uploadwork.utils.TMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author libre
 * @since 2018-04-22
 */
public interface IUserService extends IService<User> {

        TMessage login(Integer studentNum, String password);

}
