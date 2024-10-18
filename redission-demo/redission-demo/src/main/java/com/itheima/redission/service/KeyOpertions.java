package com.itheima.redission.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName KeyOpertions.java
 * @Description 关于key的操作
 */
@Slf4j
@Component
public class KeyOpertions {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    RedisTemplate redisTemplate;


    public void addData(){
        for (int i = 0; i < 10; i++) {
            RBucket<Object> bucket = redissonClient.getBucket("name:aa"+i);
            bucket.set("itheima"+i);
        }
    }

    public void foundedKeys(){
        //获得所有keys  keys * 命令进入公司慎用
        RKeys keys = redissonClient.getKeys();
        Iterable<String> keysAll = keys.getKeys();
        for (String key : keysAll) {
            log.info("获得key:"+key);
        }
        System.out.println("---------------------------");
        //获得所有security开头的key
        Iterable<String> redisKeys = keys.getKeysByPattern("security*");
        for (String key : redisKeys) {
            log.info("获得key:"+key);
        }
    }

    public void deleteKeys(){
        //获得所有keys
        RKeys keys = redissonClient.getKeys();
        long flag = keys.delete("security:aa2","security:aa4");
        log.info("批量删除key:security:aa2,security:aa4:{}",flag);
        flag = keys.deleteByPattern("security:aa*");
        log.info("模糊删除key:security:aa*:{}",flag);
    }

}
