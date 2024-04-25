package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    @Select("select count(*) from emp")
    public Long count();

//    @Select("select * from emp limit #{start},#{size}")
    public List<Emp> page(Integer start,Integer size,
                          String name,
                          Short gender,
                          LocalDate begin,
                          LocalDate end);

    void delete(List<Integer> ids);

    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, now(), now())")
    void addEmp(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);
}
