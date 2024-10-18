package com.itheima.redission.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName AtomicLongOpertions.java
 * @Description 原子整长型操作
 */
@Slf4j
@Component
public class AtomicLongOpertions {

    @Autowired
    RedissonClient redissonClient;

    public void atomicLongOper(){
        RAtomicLong atomicLongOper = redissonClient.getAtomicLong("AtomicLongOpertions");
        //添加一个从0开始的元素
        atomicLongOper.set(0);
        //获得当前元素
        long flag = atomicLongOper.get();
        log.info("获得当前元素:{}",flag);

        //先递增1，然后返回元素 ++i i++
        flag = atomicLongOper.incrementAndGet();
        log.info("先递增1，然后返回元素:{}",flag);

        //先获得元素，再递增1
        flag = atomicLongOper.getAndIncrement();
        log.info("先获得元素，再递增1",flag);

        //获得当前元素
        flag = atomicLongOper.get();
        log.info("获得当前元素:{}",flag);

        //先递减1，然后返回元素
        flag = atomicLongOper.decrementAndGet();
        log.info("先递减1，然后返回元素:{}",flag);

        //先获得元素，再递增1
        flag = atomicLongOper.getAndDecrement();
        log.info("先获得元素，再递减1",flag);

        //获得当前元素
        flag = atomicLongOper.get();
        log.info("获得当前元素:{}",flag);

        //删除元素
        boolean delete = atomicLongOper.delete();
        log.info("删除当前元素:{}",delete);

        //添加并且获得元素
        flag = atomicLongOper.addAndGet(2);
        log.info("添加并且获得元素:{}",flag);

        //获得并且删除元素
        flag = atomicLongOper.getAndDelete();
        log.info("获得并且删除元素:{}",flag);


    }

}
