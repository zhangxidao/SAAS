package com.itheima.dubbo.web;

import com.itheima.dubbo.UserFace;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    @DubboReference
    private UserFace userFace;

    @GetMapping("hello")
    public String hello(String username) {
        String s = userFace.sayHello(username);
        return s;
    }
}
