package com.itheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 表示该注解只在运行时生效
@Target(ElementType.METHOD) // 表示该注解应用于方法上
public @interface Log {
}
