package com.itheima.redission.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Version: V1.0
 */
@SpringBootTest
class RMapOpertionsTest {

    @Autowired
    RMapOpertions rMapOpertions;

    @Autowired
    RMapCacheOpertions mapCacheOpertions;

    @Test
    void rMapOper() {
        rMapOpertions.rMapOper();
    }

    @Test
    void rMapCacheOper() throws InterruptedException {
        mapCacheOpertions.rMapCache();

        Thread.sleep(99999);
    }
}
