package com.xinvacation.bss.service;

import com.xinvacation.base.common.ResponseResult;
import com.xinvacation.bss.entity.User;

public interface IUserService {
    ResponseResult login(String name, String password);
    ResponseResult register(User user);
}
