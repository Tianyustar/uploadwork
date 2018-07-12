package com.jit.uploadwork.service.impl;

import com.jit.uploadwork.entity.User;
import com.jit.uploadwork.service.RedisService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yunxi]
 * Date: 2018/7/12
 * Time: 10:12
 * Description: No Description
 * 用户redis service继承类
 */
@Service("userRedisService")
public class UserRedisServiceImpl extends RedisService<User> {

    //自定义redis key作为Hash表的key名称
    private static final String REDIS_KEY = "USER_KEY";

    protected String getRedisKey() {
        // TODO Auto-generated method stub
        return REDIS_KEY;
    }

}