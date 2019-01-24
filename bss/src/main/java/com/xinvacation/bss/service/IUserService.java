package com.xinvacation.bss.service;

import com.xinvacation.base.common.ResponseResult;

public interface IUserService {
    ResponseResult login(String username, String password);
}
