package com.xinvacation.base.common;

public class Const {
    /**
     * redis过期时间
     */
    public static final Integer REDIS_EX_TIME = 60 * 30;

    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "token_";

    /**
     * token过期时间
     */
    public static final Integer TOKEN_EX_TIME = 60 * 10;

    /**
     * 验证码过期时间
     */
    public static final Integer VERIFY_CODE_EX_TIME = 60 * 5;
}
