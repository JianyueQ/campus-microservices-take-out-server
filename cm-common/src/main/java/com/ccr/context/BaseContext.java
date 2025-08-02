package com.ccr.context;

/**
 * 基于ThreadLocal封装工具类，用于保存和获取当前登录用户id
 * @author 31373
 */
public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }


}
