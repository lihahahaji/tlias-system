package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SessionController {
    // 设置cookie
    @GetMapping("/setcookie")
    public Result setCookie(HttpServletResponse response) {
        response.addCookie(new Cookie("login_username","itheima"));
        return Result.success();
    }

    // 获取cookie中的数据
    @GetMapping("/getcookie")
    public Result getCookie(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();// 获取所有的 cookie
        for(Cookie cookie : cookies)
        {
            // 输出 name 为 login_username 的 cookie
            if(cookie.getName().equals("login_username"))
            {
                log.info("login_username:{}",cookie.getValue());
                break;
            }
        }
        return Result.success();
    }

    // 设置session
    @GetMapping("/setsession")
    public Result setSession(HttpSession session){
        session.setAttribute("loginUser","tom");
        return Result.success();
    }

    // 获取session中的数据
    @GetMapping("/getsession")
    public Result getSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        return Result.success(loginUser);
    }

}
