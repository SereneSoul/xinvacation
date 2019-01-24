package com.xinvacation.base.controller;

import com.xinvacation.base.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BaseController {
    @Autowired
    protected RedisService redis;
}
