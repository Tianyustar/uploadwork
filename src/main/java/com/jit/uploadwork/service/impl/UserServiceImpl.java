package com.jit.uploadwork.service.impl;

import com.jit.uploadwork.entity.User;
import com.jit.uploadwork.mapper.UserMapper;
import com.jit.uploadwork.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jit.uploadwork.utils.MD5Util;
import com.jit.uploadwork.utils.TMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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


    private AuthenticationService authenticationService;
    @Autowired
    public UserServiceImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Resource
    private UserRedisServiceImpl userRedisService;

    @Cacheable(value="userCache") //缓存,这里没有指定key.
    @Override
    public TMessage login(Integer studentNum, String password) {

        TMessage message ;
        User user= selectById(studentNum);
        if (user == null) {
            message = new TMessage(TMessage.CODE_FAILURE,"用户不存在，请输入正确的学号");
        } else {
            String  DBPassword = user.getPassword();
            if (password!= null && DBPassword.equals(MD5Util.getMD5(password))) {

                String token=   authenticationService.getToken(user);
                Map<String ,String> info  = new HashMap<>(); // hash map
                info.put("token",token);
                info.put("username",user.getUserName());
                info.put("role",user.getRole());
                message = new TMessage(TMessage.CODE_SUCCESS,"登陆成功",info);
            } else  {
                message = new TMessage(TMessage.CODE_FAILURE, "登陆失败，请输入正确的密码");
            }
        }
        return message;
    }
    //allEntries 清空缓存所有属性 确保更新后缓存刷新
    @CacheEvict(value="userCache", allEntries=true)
    @Override
    public TMessage modifyPwd(User user, String oldPassword, String newPassword) {

        if (user.getPassword().equals(MD5Util.getMD5(oldPassword))) {  // 匹配,可以修改密码
                if ( newPassword == null || newPassword.trim().equals("") || newPassword.length() < 6) {
                   return new TMessage(TMessage.CODE_FAILURE, "新密码不可以位空");
                }
                // 修改密码
                user.setPassword(MD5Util.getMD5(newPassword)); // 设置了新密码
                updateById(user);
                return new TMessage(TMessage.CODE_SUCCESS,"修改密码成功，请重新登陆");
        }
        return new TMessage(TMessage.CODE_FAILURE,"旧密码输入不正确");
    }
}
