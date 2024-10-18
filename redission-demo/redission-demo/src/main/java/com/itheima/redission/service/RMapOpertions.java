package com.itheima.redission.service;

import com.itheima.redission.pojo.AnyObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName RMapOpertions.java
 * @Description hash表操作
 */
@Slf4j
@Component
public class RMapOpertions {

    @Autowired
    RedissonClient redissonClient;

    public void rMapOper(){

        //获得hash表，这里RMapOpertions为主key
        RMap<String, AnyObject> userInfo = redissonClient.getMap("userInfo");
        AnyObject anyObjectA = AnyObject.builder().id("1").name("嬴政").age(22).address("秦朝").build();
        AnyObject anyObjectB = AnyObject.builder().id("2").name("李斯").age(22).address("秦朝").build();
        AnyObject anyObjectC = AnyObject.builder().id("3").name("孙悟空").age(22).address("唐").build();
        //添加元素，返回的值为之前hash表中的值
        userInfo.put(anyObjectA.getId(),anyObjectA);
        userInfo.put(anyObjectB.getId(),anyObjectB);
        userInfo.put(anyObjectC.getId(),anyObjectC);

        //获得userInfo中所有的key
        Set<String> keySet = userInfo.readAllKeySet();
        log.info("获得userInfo中所有的key:{}",keySet.toString());

        //获得userInfo中所有的values
        Collection<AnyObject> anyObjects = userInfo.readAllValues();
        log.info("获得userInfo中所有的值:{}",anyObjects.toString());

        //获得userInfo中所有的元素对象
        Set<Map.Entry<String, AnyObject>> entries = userInfo.readAllEntrySet();
        log.info("获得userInfo中所有的元素对象:{}",entries.toString());
        userInfo.clear();

        //快速添加元素，与put的不同是不返回值，且添加速度快
        userInfo.fastPut(anyObjectA.getId(),anyObjectA);
        userInfo.fastPut(anyObjectB.getId(),anyObjectB);
        userInfo.fastPut(anyObjectC.getId(),anyObjectC);
        userInfo.clear();
        //批量添加
        Map<String,AnyObject> map = new HashMap<>();
        map.put(anyObjectA.getId(),anyObjectA);
        map.put(anyObjectB.getId(),anyObjectB);
        map.put(anyObjectC.getId(),anyObjectC);
        userInfo.putAll(map);

        //根据辅key获得元素，【和map中获得元素一样】
        AnyObject anyObjectResult = userInfo.get(anyObjectA.getId());
        log.info("根据辅key获得元素对象:{}",anyObjectResult.toString());

        //试着添加元素，如果元素key存在则不做任何修改，,如果元素key不存在则做修改，
        //返回结果为之前值【如果返回null,表明之前每页存储过元素】
        AnyObject anyObjectD = AnyObject.builder().id("4").name("如来佛").age(1000000).address("上古").build();
        AnyObject anyObject = userInfo.putIfAbsent(anyObjectD.getId(), anyObjectD);
        //清除所有元素
//        userInfo.clear();


    }
}
