package com.xinvacation.bss.service.impl;

import com.xinvacation.base.common.ResponseResult;
import com.xinvacation.base.service.BaseService;
import com.xinvacation.base.util.MD5Util;
import com.xinvacation.bss.entity.User;
import com.xinvacation.bss.repository.IUserRepository;
import com.xinvacation.bss.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends BaseService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public ResponseResult login(String name, String password) {
        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        try {
            String realPassword = MD5Util.MD5EncodeUtf8(password);
            User user = userRepository.getUserByNameAndPassword(name, realPassword);
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

    @Override
    public ResponseResult register(User user) {
        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        try {
            if(user == null){
                throw new RuntimeException("请输入用户信息！");
            }
            String username = user.getName();
            if(StringUtils.isBlank(username)){
                throw new RuntimeException("用户名不能为空！");
            }
            int name = userRepository.countByName(username);
            if(name > 0){
                throw new RuntimeException("用户名已存在！");
            }
            String userEmail = user.getEmail();
            if(StringUtils.isNotBlank(userEmail)){
                int email = userRepository.countByEmail(userEmail);
                if (email > 0){
                    throw new RuntimeException("邮箱已存在！");
                }
            }
            String userPhone = user.getPhone();
            if(StringUtils.isNotBlank(userPhone)){
                int phone = userRepository.countByPhone(userPhone);
                if (phone > 0){
                    throw new RuntimeException("手机号码已存在！");
                }
            }
            String password = user.getPassword();
            if(StringUtils.isBlank(password)){
                throw new RuntimeException("密码不能为空！");
            }
            String realPassword = MD5Util.MD5EncodeUtf8(user.getPassword());
            user.setPassword(realPassword);
            user.setRole(1);
            User uu = userRepository.save(user);
            if(uu != null) {
                result.setSuccess(true);
                result.setMsg("注册成功！");
            }
        } catch (Exception e) {
            log.error("注册错误：" + e.getMessage());
            result.setMsg("注册错误：" + e.getMessage());
        }
        return result;
    }
}
