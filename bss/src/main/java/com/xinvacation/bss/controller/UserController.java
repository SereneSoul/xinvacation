package com.xinvacation.bss.controller;

import com.alibaba.fastjson.JSON;
import com.xinvacation.base.common.Const;
import com.xinvacation.base.common.ResponseResult;
import com.xinvacation.base.controller.BaseController;
import com.xinvacation.base.util.CookieUtil;
import com.xinvacation.base.util.JsonUtil;
import com.xinvacation.bss.entity.User;
import com.xinvacation.bss.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public ResponseResult login(HttpSession httpSession, String name, String password, HttpServletResponse httpServletResponse) {
        ResponseResult result = userService.login(name, password);
        if (result.isSuccess()) {
            CookieUtil.writeLoginToken(httpServletResponse,httpSession.getId());
            redis.set(httpSession.getId(), JsonUtil.objToString(result.getData()), Const.REDISEXTIME);
        }
        return result;
    }

    @RequestMapping("/register")
    public ResponseResult register(User user) {
        return userService.register(user);
    }
    
    @RequestMapping("/logout")
    public ResponseResult logout(HttpSession httpSession, HttpServletRequest httpServletRequest){
        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        try {
            String cookieValue = CookieUtil.readLoginToken(httpServletRequest);
            if(StringUtils.isBlank(cookieValue)){
                result.setMsg("当前用户未登录,或登录已过期！");
                return result;
            }
            User user = JsonUtil.stringToObj((String)redis.get(cookieValue),User.class);
            if(user == null) {
                result.setMsg("当前用户未登录,或登录已过期！");
                return result;
            }
            redis.del(httpSession.getId());
            result.setSuccess(true);
            result.setMsg("用户注销成功！");
        }catch (Exception e){
            result.setMsg("用户注销失败：" + e.getMessage());
        }
        return result;
    }
}
