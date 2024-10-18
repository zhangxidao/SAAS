package com.itheima.redission.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description:
 * @Version: V1.0
 */
@SpringBootTest
class RLockOpertionsTest {

    @Autowired
    RLockOpertions rLockOpertions;

    @Test
    void lock() throws InterruptedException {
        rLockOpertions.lock();
    }

    @Test
    void lockLaseTime() throws InterruptedException {
        rLockOpertions.lockLaseTime();
    }

    @Test
    void tryLock() throws InterruptedException {
        rLockOpertions.tryLock();
    }

    @Test
    void tryLockWaitTime() throws InterruptedException {
        rLockOpertions.tryLockWaitTime();
    }

    @Test
    void tryLockleasTime() throws InterruptedException {
        rLockOpertions.tryLockleasTime();
    }
}
