package com.itheima.redission.service;

import com.itheima.redission.pojo.AnyObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBucket;
import org.redisson.api.RBuckets;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BucketOpertions.java
 * @Description 通用对象桶
 */
@Slf4j
@Component
public class BucketOpertions {

    @Autowired
    RedissonClient redissonClient;

    /***
     * @description Bucket通用对象通
     */
    public void bucketOper(){

        //获得Bucket
        RBucket<AnyObject> anyObjectRBucket = redissonClient.getBucket("BucketOpertions");
        //放入一个元素
        AnyObject anyObject = AnyObject.builder()
                .name("张三")
                .age(19)
                .address("中国上海")
                .build();
        //为BucketOperTest添加元素到redis中
        anyObjectRBucket.set(anyObject);
        // 设置key的存活时间 timeToLive： -2：key不存在   -1：永久有效
        long timeToLive = anyObjectRBucket.remainTimeToLive();
        log.info("BucketOperTest存活时间：{}",timeToLive);

        //修改BucketOperTest的存活时间为600秒
        anyObjectRBucket.set(anyObject,600, TimeUnit.SECONDS);
        timeToLive = anyObjectRBucket.remainTimeToLive();
        log.info("BucketOperTest存活时间：{}",timeToLive/1000);

        //试着为BucketOperTest添加元素到redis中,并且存活时间为600秒
        boolean trySetFlag = anyObjectRBucket.trySet(anyObject, 600, TimeUnit.SECONDS);
        log.info("试着为BucketOperTest存储元素：{}",trySetFlag);

        //在holder中获取当前元素并将其替换为新值
        AnyObject anyObjectNew = AnyObject.builder()
                .name("李四")
                .age(20)
                .address("中国北京")
                .build();
        AnyObject anyObjectResult = anyObjectRBucket.getAndSet(anyObjectNew);
        log.info("BucketOperTest原始值：{}",anyObjectResult);
        anyObjectResult = anyObjectRBucket.get();
        log.info("BucketOperTest新添值：{}",anyObjectResult);

        //移除BucketOperTest
        boolean deleteFalg = anyObjectRBucket.delete();
        log.info("BucketOperTest删除：{}",deleteFalg);
    }

    /***
     * @description Buckets批量通用对象通
     */
    public void bucketsOper(){
        //获得Bucket
        RBucket<AnyObject> bucketA = redissonClient.getBucket("BucketOpertionsTestA");
        //放入一个元素
        AnyObject anyObject = AnyObject.builder()
                .name("张三")
                .age(19)
                .address("中国上海")
                .build();
        //为BucketOperTest添加元素到redis中
        bucketA.set(anyObject);
        //获得Bucket
        RBucket<AnyObject> bucketB = redissonClient.getBucket("BucketOpertionsTestB");
        bucketB.set(anyObject);

        //获得Buckets
        RBuckets buckets = redissonClient.getBuckets();
        //这里的兼具map的属性
        Map<String, AnyObject> bucketsOperMap = buckets.get("BucketOpertionsTestA", "BucketOpertionsTestB");
        log.info("map的元素信息：{}",bucketsOperMap);
        //删除所有元素
        bucketsOperMap.clear();

    }
}
