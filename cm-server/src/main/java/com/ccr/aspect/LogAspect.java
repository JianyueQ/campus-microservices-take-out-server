package com.ccr.aspect;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.ccr.annotations.Log;
import com.ccr.constant.StatusConstant;
import com.ccr.context.BaseContext;
import com.ccr.entity.OperationLog;
import com.ccr.manager.AsyncManager;
import com.ccr.manager.factory.AsyncFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * @author 31373
 */

@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    /**
     * 处理请求前执行
     */
    @Before(value = "@annotation(controllerLog)")
    public void before(Log controllerLog) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理请求后执行
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint,Log controllerLog ,Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    /**
     * 日志记录
     * @param joinPoint 切点
     * @param controllerLog 日志注解
     * @param e 异常
     * @param jsonResult 返回结果
     */
    protected void handleLog(JoinPoint joinPoint, Log controllerLog, Exception e, Object jsonResult){
        try {
            // 获取当前请求信息
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            //获取用户id
            Long currentId = BaseContext.getCurrentId();
            //*=====数据库日志======*//
            OperationLog operationLog = new OperationLog();
            //获取操作名称
            operationLog.setModule(controllerLog.title());
            //获取操作类型
            operationLog.setOperation(controllerLog.businessType().toString());
            //获取操作用户id
            operationLog.setOperatorId(currentId);
            // 获取请求相关信息
            operationLog.setRequestMethod(request.getMethod());
            operationLog.setRequestUrl(request.getRequestURL().toString());
            operationLog.setIpAddress(getClientIpAddress(request));
            // 获取请求参数
            String params = Arrays.toString(joinPoint.getArgs());
            operationLog.setRequestParam(params);
            // 设置执行时间和状态
            long startTime = TIME_THREADLOCAL.get();
            long endTime = System.currentTimeMillis();
            operationLog.setExecutionTime(endTime - startTime);

            // 设置操作状态和结果
            if (e != null) {
                // 失败
                operationLog.setStatus(StatusConstant.STATUS_DISABLE);
                operationLog.setErrorMessage(e.getClass().getName() + ": " + e.getMessage());
            } else {
                // 成功
                operationLog.setStatus(StatusConstant.STATUS_NORMAL);
                // 设置返回参数
                operationLog.setResponseResult(jsonResult != null ? jsonResult.toString() : "");
            }
            //存储到数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(operationLog));
        } catch (Exception ex) {
            log.info("操作日志记录异常：{}", ex.getMessage());
            ex.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }


    /**
     * 获取客户端IP地址
     * @param request HttpServletRequest
     * @return  String
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
