package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;


    @Around("@annotation(com.itheima.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {



        // 操作人ID -- 当前登录员工的ID
        // 获取请求头中的 jwt 令牌，解析令牌
        String jwt = request.getHeader("token");

        Claims claims = JwtUtils.parseJWT(jwt);

        Integer operatorUser = (Integer)claims.get("id");


        // 操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        // 操作类名
        String className = joinPoint.getTarget().getClass().getName();

        // 操作方法名
        String methodName = joinPoint.getSignature().getName();

        // 操作方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        // 调用原始目标方法运行
        Long beginTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        Long endTime = System.currentTimeMillis();

        // 方法返回值
        String returnValue = JSONObject.toJSONString(result);

        // 操作耗时
        Long costTime = endTime - beginTime;



        // 记录操作日志
        OperateLog operateLog = new OperateLog(null,operatorUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP 记录操作日志:{}",operateLog);


        return result;
    }
}
