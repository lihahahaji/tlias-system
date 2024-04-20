package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {


    @Autowired
    EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize,
                         String name,
                         Short gender,
                         LocalDate begin,
                         LocalDate end) {

        // 1. 获取总记录数
        Long count = empMapper.count();
        // 2. 获取分页查询的结果列表
        Integer start = (page-1)*pageSize;
        List<Emp> empList = empMapper.page(start, pageSize,name,gender,begin,end);
        // 3. 返回 PageBean 对象
        return new PageBean(count,empList);

    }

    @Override
    public void delete(List<Integer> ids) {

        empMapper.delete(ids);
    }

    @Override
    public void addEmp(Emp emp) {

        empMapper.addEmp(emp);
    }


}
