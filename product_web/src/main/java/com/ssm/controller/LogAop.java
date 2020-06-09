package com.ssm.controller;

import com.ssm.domain.SysLog;
import com.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Author: TJC
 * @Date: 2020/6/9 13:07
 * @description: TODO
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Timestamp visitTime; // 开始时间
    private Class<?> clazz;  // 访问的类
    private Method method;  // 访问的方法

    // 前置通知, 获取开始时间, 执行的类, 执行的方法
    @Before("execution(public * com.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Timestamp(new Date().getTime());
        // 获取访问的类
        clazz = jp.getTarget().getClass();
        // 获取访问的方法的名称
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args == null || args.length == 0)
            method = clazz.getMethod(methodName);
        else {
            Class<?>[] classes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                classes[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classes);
        }

    }

    // 后置通知, 获取访问时长, url, IP地址
    @After("execution(public * com.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        // 获取访问的时长
        Long executionTime = new Date().getTime() - visitTime.getTime();
        // 获取URL
        String url = null;
        String[] classValue = null;
        if (clazz != null && method != null && clazz != LogAop.class && clazz != SysLogController.class) {
            // 1, 获取类上的@RequestMapping
            RequestMapping clazzAnnotation = clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null) {
                classValue = clazzAnnotation.value();
            }else
                classValue = new String[]{""};
                // 2. 获取方法上的@RequestMapping
            if (method != null && !"findAll".equals(method.getName()) && !"toMain".equals(method.getName()) && !"initBinder".equals(method.getName())) {
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
                // 获取访问的ip
                String ip = request.getRemoteAddr();

                // 获取当前操作的用户(利用spring security获取)
                SecurityContext context = SecurityContextHolder.getContext(); // 从上下文中获取当前登录的用户
                User user = (User) context.getAuthentication().getPrincipal();
                String username = user.getUsername();
                // 也可以直接通过request获取
//                  request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");

                // 将日志相关信息封装到SysLog对象中
                SysLog sysLog = new SysLog();
                sysLog.setVisitTime(visitTime);
                sysLog.setUsername(username);
                sysLog.setIp(ip);
                sysLog.setUrl(url);
                sysLog.setExecutionTime(executionTime);
                sysLog.setMethod(clazz.getName() + "." + method.getName());

                // 调用Service完成日志记录
                sysLogService.saveLog(sysLog);
            }
        }

    }
}
