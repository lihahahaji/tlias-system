package com.itheima.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME) // 指定这个注解在运行时生效
@Target({ElementType.METHOD})
public @interface MyLog {

}
