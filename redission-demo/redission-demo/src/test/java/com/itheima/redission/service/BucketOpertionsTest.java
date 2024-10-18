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
class BucketOpertionsTest {

    @Autowired
    BucketOpertions bucketOpertions;

    @Test
    void bucketOper() {
        bucketOpertions.bucketOper();
    }

    @Test
    void bucketsOper() {
        bucketOpertions.bucketsOper();
    }
}
