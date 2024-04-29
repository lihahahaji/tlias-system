package com.itheima;

import com.itheima.controller.DeptController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasWebManagementApplicationTests {



    @Test
    void contextLoads() {
    }

    // 生成 jwt 测试
    @Test
    public void testGenJwt()
    {
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itheima")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))
                .compact();

        System.out.println(jwt);
    }

    // 解析Jwt 测试
    @Test
    public void testParseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxNDE0MzI3MH0.NZDGxGGvnc1F3shMfvzmFncNqNHq6rayJQF3jp8h7XI")
                .getBody();
 
        System.out.println("res: "+claims.toString());
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void TestGetBean(){
        // 根据 bean 的名称来获取
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println(bean1);
        // 根据bean 的类型来获取
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println(bean2);

        // 根据 bean 的名称和类型来获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);

    }


}
