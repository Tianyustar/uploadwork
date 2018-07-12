package com.jit.uploadwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: yunxi
 * Date: 2018/7/12
 * Time: 10:09
 * Description: No Description
 */
public abstract class RedisService<T> {

    @Autowired
    protected RedisTemplate<String, String> redisTemplate;
    /**
     * 定义hash结构，操作存储实体对象
     */
    @Resource
    protected HashOperations<String, String, T> hashOperations;

  /*  定义hash表的redis key名称*/
  protected abstract String getRedisKey();

    /**
     * 在相应Hash表中添加键值对 key:Object(doamin)
     *
     * @param key    key
     * @param doamin 对象
     * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
     */
    public void put(String key, T doamin, long expire) {
        hashOperations.put(getRedisKey(), key, doamin);
        if (expire != -1) {
            redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }
    /**
     * 在相应Hash表中删除key名称的元素
     *
     * @param key 传入key的名称
     */
    public void remove(String key) {
        hashOperations.delete(getRedisKey(), key);
    }

    /**
     * 在相应Hash表中查询key名称的元素
     *
     * @param key 查询的key
     * @return
     */
    public T get(String key) {
        return hashOperations.get(getRedisKey(), key);
    }

    /**
     * 获取在相应Hash表下的所有实体对象
     *
     * @return
     */
    public List<T> getAll() {
        return hashOperations.values(getRedisKey());
    }

    /**
     * 查询在相应Hash表下的所有key名称
     *
     * @return
     */
    public Set<String> getKeys() {
        return hashOperations.keys(getRedisKey());
    }

    /**
     * 判断在相应Hash表下key是否存在
     *
     * @param key 传入key的名称
     * @return
     */
    public boolean isKeyExists(String key) {
        return hashOperations.hasKey(getRedisKey(), key);
    }

    /**
     * 查询相应Hash表的缓存数量
     *
     * @return
     */
    public long count() {
        return hashOperations.size(getRedisKey());
    }

    /**
     * 清空相应Hash表的所有缓存
     */
    public void empty() {
        Set<String> set = hashOperations.keys(getRedisKey());
        set.stream().forEach(key -> hashOperations.delete(getRedisKey(), key));
    }

}
