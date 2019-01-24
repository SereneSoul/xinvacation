package com.xinvacation.bss.controller;

import com.xinvacation.base.common.Const;
import com.xinvacation.base.common.ResponseResult;
import com.xinvacation.base.service.RedisService;
import com.xinvacation.bss.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/login")
    public ResponseResult login(HttpSession httpSession, String username, String password) {
        ResponseResult result = userService.login(username, password);
        if (result.isSuccess()) {
            redisService.set(httpSession.getId(), result.getData(), Const.REDISEXTIME);
        }
        return result;
    }
}
