package com.xinvacation.bss.service.impl;

import com.xinvacation.base.common.ResponseResult;
import com.xinvacation.base.util.MD5Util;
import com.xinvacation.bss.entity.User;
import com.xinvacation.bss.repository.IUserRepository;
import com.xinvacation.bss.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public ResponseResult login(String username, String password) {
        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        try {
            String realPassword = MD5Util.MD5EncodeUtf8(password);
            User user = userRepository.getUserByNameAndPassword(username, realPassword);
            if (user == null) {
                throw new RuntimeException("用户名或密码错误！");
            }
            user.setPassword(null);
            result.setSuccess(true);
            result.setData(user);
        } catch (Exception e) {
            log.error("登录错误：" + e.getMessage());
            result.setMsg("登录错误：" + e.getMessage());
        }
        return result;
    }
}
