package com.itheima.redission.service;

import com.itheima.redission.pojo.AnyObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.api.map.event.EntryEvent;
import org.redisson.api.map.event.EntryExpiredListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RMapCacheOpertions.java
 * @Description 带淘汰机制的hash表操作
 */
@Slf4j
@Component
public class RMapCacheOpertions {

    @Autowired
    RedissonClient redissonClient;

    public void rMapCache(){

        //获得hash表，这里RMapOpertions为主key
        RMapCache<String, AnyObject> userInfo = redissonClient.getMapCache("userInfo");
        AnyObject anyObjectA = AnyObject.builder().id("1").name("嬴政").age(22).address("秦朝").build();
        AnyObject anyObjectB = AnyObject.builder().id("2").name("李斯").age(22).address("秦朝").build();
        AnyObject anyObjectC = AnyObject.builder().id("3").name("孙悟空").age(22).address("唐").build();
        //添加元素，返回的值为之前hash表中的值，并且为每个子元素添加过期时间
        userInfo.put(anyObjectA.getId(),anyObjectA,20, TimeUnit.SECONDS);
        userInfo.put(anyObjectB.getId(),anyObjectB,40, TimeUnit.SECONDS);
        userInfo.put(anyObjectC.getId(),anyObjectC,60, TimeUnit.SECONDS);

        //获得userInfo中所有的key
        Set<String> keySet = userInfo.readAllKeySet();
        log.info("获得userInfo中所有的key:{}",keySet.toString());

        //获得userInfo中所有的values
        Collection<AnyObject> anyObjects = userInfo.readAllValues();
        log.info("获得userInfo中所有的值:{}",anyObjects.toString());

        //获得userInfo中所有的元素对象
        Set<Map.Entry<String, AnyObject>> entries = userInfo.readAllEntrySet();
        log.info("获得userInfo中所有的元素对象:{}",entries.toString());
        //userInfo.clear();

        //其他操作与RMap类似，这里就不再操作

        //*****过期事件监听*****
        userInfo.addListener(new EntryExpiredListener<String, AnyObject>() {
            @Override
            public void onExpired(EntryEvent<String, AnyObject> event) {
                System.out.println("getKey: "+  event.getKey());
                System.out.println("getValue: "+  event.getValue());
                System.out.println("getOldValue: "+  event.getOldValue());
                System.out.println("getSource: "+  event.getSource());
                System.out.println("getType: "+  event.getType());
            }
        });
    }
}
