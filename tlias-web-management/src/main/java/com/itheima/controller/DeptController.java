package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@RestController
@RequestMapping("/depts")
public class DeptController {
    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;


    /**
     * 查询部门数据
     * @return
     */
    @GetMapping
    public Result list(){
        log.info("查询部门数据");

        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

    /**
     * 删除部门数据
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id)
    {
        log.info("根据 id 来删除部门：{}",id);
        deptService.delete(id);

        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody  Dept dept)
    {
        log.info("新增部门：{}",dept);

        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result find(@PathVariable Integer id){
        log.info("查找 id 为 {} 的部门",id);

        Dept dept = deptService.find(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){

        log.info("更新部门：{}",dept.getId());
        deptService.update(dept);
        return Result.success();
    }



}
