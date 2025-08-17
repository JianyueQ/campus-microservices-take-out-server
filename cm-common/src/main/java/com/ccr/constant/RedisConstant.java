package com.ccr.constant;

/**
 * Redis常量
 * @author 31373
 */
public class RedisConstant {

    /**
     * 公共
     */
    public static final long LOGIN_ERROR_COUNT = 1;
    public static final long LOGIN_ERROR_COUNT_TIME = 5;
    /**
     * 管理员
     */
    public static final String REDIS_KEY_CODE = "verify:code:";
    public static final long CODE_EXPIRE_TIME = 5;
    public static final String JWT_TOKEN_KEY = "jwt:token:";
    public static final long TOKEN_TTL = 2;
    public static final Object HASH_KEY_SUPER_ADMIN = "superAdmin";
    public static final String JWT_ID_KEY = "user:info:";
    public static final String ADMIN_LOGIN_ERROR_COUNT_KEY = "admin:login:error:count:";
    public static final long ADMIN_LOGIN_ERROR_COUNT_TIME = 5;
    public static final long ADMIN_LOGIN_ERROR_COUNT = 1;
    public static final String ANNOUNCEMENT_READ_COUNT = "announcement:read:count:";
    public static final long ANNOUNCEMENT_READ_COUNT_INCREASE = 1;

    /**
     * 学生
     */
    public static final String STUDENT_LOGIN_ERROR_COUNT_KEY = "student:login:error:count:";
}
