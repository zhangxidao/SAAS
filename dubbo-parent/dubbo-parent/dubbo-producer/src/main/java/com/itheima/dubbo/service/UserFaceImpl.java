package com.itheima.dubbo.service;

import com.itheima.dubbo.UserFace;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class UserFaceImpl implements UserFace {


    @Override
    public String sayHello(String username) {
        return "hello dubbo username:" + username;
    }
}
