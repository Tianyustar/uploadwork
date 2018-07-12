package com.jit.uploadwork.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: yunxi
 * Date: 2018/7/12
 * Time: 10:02
 * Description: No Description
 */
/*
 * 说明：个人总结为redisTemplate可以处理redis中的所有数据类型。
 * 但对于具体数据类型的处理，各种类型的接口处理起来更加方便，
 * 方法比较多
 * */

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private HashOperations<String,String,Object> hashOperations;

    @Autowired
    private ListOperations<String,Object> listOperations;

    /*---------------------------------------------公共区域---------------------------------------------------*/

    //判断key是否存在
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    //根据key删除key和value
    public void deleteByKey(String key){
        redisTemplate.delete(key);
    }

    /*
     * 设置key的有效期
     * @param key,time(有效时间),unit(hours,days,minutes)
     * */
    public void setExpire(String key,long time,TimeUnit unit){
        if (time != -1){
            redisTemplate.expire(key,time,unit);
        }
    }

    /*----------------------------------------------String-------------------------------------------------*/

    //根据key获取value值
    public String getValue(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    //插入数据
    public void setValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }
    /*-----------------------------------------------hash--------------------------------------------------*/

    //存入hash类型的值
    public void addAllForHash(String key, Map<String,Object> map){
        hashOperations.putAll(key,map);
    }

    /*
     * 根据key查询该key对应map下的所有value
     * @param key
     * @return  List<AllValue>
     * */
    public List<Object> getAllValueForHash(String key){
        return hashOperations.values(key);
    }

    //获取Hash中的单个value
    public Object getValueForHash(String key, String hashKey){
        return hashOperations.get(key,hashKey);
    }

    //获取Hash数量
    public Long getCount(String key){
        return hashOperations.size(key);
    }

    /*
     * 根据key和hashKey删除value
     * @param key,hashKey
     * @return void
     * */
    public void deleteValueByKeyAndHashKey(String key,String hashKey){
        hashOperations.delete(key,hashKey);
    }

    /*
     * 判断当前key中hashKey是否存在
     * @param  key,hashKey
     * @return true / false
     * */
    public boolean hasHashKey(String key,String hashKey){
        return hashOperations.hasKey(key,hashKey);
    }

    /*
     * 查询key下所有Hash值
     * @param key
     * @return Map<key,value>
     * */
    public Object getAllHashByKey(String key){
        return hashOperations.entries(key);
    }

    /*-----------------------------------------------hash----------------------------------------------------*/

    /*-----------------------------------------------list----------------------------------------------------*/

    /*
     * 从list队列尾部存数据
     * @param key,List
     * @return null
     * */
    public void rightPushAll(String key, List<String> values){
        listOperations.rightPushAll(key,values);
    }

    /*
     * 从list队列尾部存数据
     * @param key,value
     * @return null
     * */
    public void rightPush(String key, String value){
        listOperations.rightPush(key,value);
    }

    /*
     *从list队列中取出全部数据(0,listSize)
     * @param key
     * @return List
     * */
    public List<Object> getListAll(String key){
        return listOperations.range(key,0,listOperations.size(key));
    }


    /*-----------------------------------------------list---------------------------------------------------*/

}
