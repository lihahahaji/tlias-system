package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
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

}
