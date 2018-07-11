package com.jit.uploadwork.mapper;

import com.jit.uploadwork.entity.StudentList;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author libre
 * @since 2018-05-30
 */
public interface StudentListMapper extends BaseMapper<StudentList> {

    @Select("select distinct stu_class from student_list")
    List<String> getClassList();

}
