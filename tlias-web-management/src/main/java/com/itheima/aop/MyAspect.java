package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Aspect
@Slf4j
@Order(2)
public class MyAspect {

    // 定义切入点表达式
    @Pointcut("execution(public void com.itheima.service.impl.DeptServiceImpl.delete(java.lang.Integer))")
//    @Pointcut("@annotation(com.itheima.aop.MyLog)") // 根据指定注解(自定义注解)来定义切入点
    public void pt(){};

    // 在切入方法执行之前调用
    @Before("pt()")
    public void before(JoinPoint joinPoint){
        log.info("before...");
    }

    // 在切入方法执行之前和之后调用
    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around before...");

        // 1. 获取 目标对象的类名
        String className = joinPoint.getTarget().getClass().getName();

        // 2. 获取目标方法的方法名
        String methodName = joinPoint.getSignature().getName();

        // 3. 获取目标方法运行时传入的参数
        Object[] args = joinPoint.getArgs();

        // 4. 放行目标方法执行
        Object result = joinPoint.proceed();

        // 5. 获取目标方法运行的返回值
        System.out.println(result);


        log.info("around after...");
        return result;
    }

    // 在切入方法执行之后调用
    @After("pt()")
    public void after(JoinPoint joinPoint){
        log.info("after...");
    }

    @AfterReturning("pt()")
    public void afterReturning(JoinPoint joinPoint){
        log.info("afterReturning...");
    }

    @AfterThrowing("pt()")
    public void afterThrowing(JoinPoint joinPoint){
        log.info("afterThrowing...");
    }

}
