package com.hc.controller;

import com.hc.domain.User;
import com.hc.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRespository userRespository;
    private static  AtomicInteger ag = new AtomicInteger();
    @Autowired
    public UserController(UserRespository userRespository){
        this.userRespository = userRespository;
    }

    @PostMapping("/save")
    public User save(User user){

        userRespository.save(user);
        System.out.printf("保存%s成功",user);
        return user;
    }

}
