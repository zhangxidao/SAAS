package com.itheima.redission.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Version: V1.0
 */
@SpringBootTest
class AtomicLongTest {

    @Autowired
    private AtomicLongOpertions longOpertions;


    @Test
    public void testAtomicLongOper() throws Exception{
        longOpertions.atomicLongOper();
    }
}
