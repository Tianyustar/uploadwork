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

        /**
         * loging
         * @param studentNum
         * @param password
         * @return
         */
        TMessage login(Integer studentNum, String password);

        /**
         * 修改密码功能， 要求提供旧密码进行验证
         * @param user
         * @param oldPassword
         * @param newPassword
         * @return
         */
        TMessage modifyPwd(User user ,String oldPassword, String newPassword);

}
