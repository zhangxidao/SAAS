package com.itheima.redission.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName AtomicDoubleOpertions.java
 * @Description 原子浮点型操作
 */
@Slf4j
@Component
public class AtomicDoubleOpertions {

    @Autowired
    RedissonClient redissonClient;

    public void atomicDoubleOper(){

        RAtomicDouble atomicDoubleOper = redissonClient.getAtomicDouble("AtomicDoubleOpertions");
        //添加一个从2.0开始的元素
        atomicDoubleOper.set(2.0D);
        //获得当前元素
        double flag = atomicDoubleOper.get();
        log.info("获得当前元素:{}",flag);

        //先递增1，然后返回元素
        flag = atomicDoubleOper.incrementAndGet();
        log.info("先递增1，然后返回元素:{}",flag);

        //先获得元素，再递增1
        flag = atomicDoubleOper.getAndIncrement();
        log.info("先获得元素，再递增1",flag);

        //获得当前元素
        flag = atomicDoubleOper.get();
        log.info("获得当前元素:{}",flag);

        //先递减1，然后返回元素
        flag = atomicDoubleOper.decrementAndGet();
        log.info("先递减1，然后返回元素:{}",flag);

        //先获得元素，再递增1
        flag = atomicDoubleOper.getAndDecrement();
        log.info("先获得元素，再递减1",flag);

        //获得当前元素
        flag = atomicDoubleOper.get();
        log.info("获得当前元素:{}",flag);

        //删除元素
        boolean delete = atomicDoubleOper.delete();
        log.info("删除当前元素:{}",delete);

        //添加并且获得元素
        flag = atomicDoubleOper.addAndGet(2);
        log.info("添加并且获得元素:{}",flag);

        //获得并且删除元素
        flag = atomicDoubleOper.getAndDelete();
        log.info("获得并且删除元素:{}",flag);

    }
}
