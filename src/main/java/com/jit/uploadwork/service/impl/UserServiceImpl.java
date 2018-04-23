package com.jit.uploadwork.service.impl;

import com.jit.uploadwork.entity.User;
import com.jit.uploadwork.mapper.UserMapper;
import com.jit.uploadwork.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jit.uploadwork.utils.MD5Util;
import com.jit.uploadwork.utils.TMessage;
import org.springframework.beans.factory.annotation.Autowired;
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


    AuthenticationService authenticationService;
    @Autowired
    public UserServiceImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public TMessage login(Integer studentNum, String password) {

        TMessage message ;
        User user =  selectById(studentNum);
        if (user == null) {
            message = new TMessage(TMessage.CODE_FAILURE,"用户不存在，请输入正确的学号");
        } else {
            String  DBPassword = user.getPassword();
            if (DBPassword.equals(MD5Util.getMD5(password))) {

                String token=   authenticationService.getToken(user);
                message = new TMessage(TMessage.CODE_SUCCESS,"登陆成功",token);
            } else  {
                message = new TMessage(TMessage.CODE_FAILURE, "登陆失败，请输入正确的密码");
            }
        }
        return message;
    }
}
