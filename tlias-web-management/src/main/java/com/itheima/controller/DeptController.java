package com.itheima.controller;

import com.itheima.pojo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门管理Controller
 */
@RestController
public class DeptController {
    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result list(){
        log.info("查询部门数据");


        return Result.success();
    }
}
