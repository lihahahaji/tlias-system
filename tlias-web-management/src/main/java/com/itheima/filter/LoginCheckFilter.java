package com.itheima.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 获取请求URL
        String url = request.getRequestURL().toString();

        // 2. 判断请求URL中是否包含 login，如果包含说明是登录操作，直接放行
        if(url.contains("login"))
        {
            // 直接放行并且跳过接下来的操作
            System.out.println("登录请求");
            filterChain.doFilter(request, response);
            return;
        }

        // 3. 获取请求中的令牌（token）
        String jwt = request.getParameter("token");
        System.out.println(jwt);

        // 4. 判断令牌是否存在，如果不存在，返回错误结果
        if(!StringUtils.hasLength(jwt)){
            System.out.println("令牌不存在，返回登陆界面");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }

        // 5. 如果令牌存在，解析token，如果解析失败则返回错误结果（未登录）
        try{
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            // 解析失败
            System.out.println("令牌存在，解析错误，返回登录界面");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;
        }

        // 解析成功，放行
        System.out.println("令牌存在，已登录，放行");
        filterChain.doFilter(request, response);

        return;

    }
}
