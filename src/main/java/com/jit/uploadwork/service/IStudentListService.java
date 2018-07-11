package com.jit.uploadwork.service;

import com.jit.uploadwork.entity.StudentList;
import com.baomidou.mybatisplus.service.IService;
import com.jit.uploadwork.utils.TMessage;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author libre
 * @since 2018-05-30
 */
public interface IStudentListService extends IService<StudentList> {
      List<String> getClassInfo();
     TMessage dividGroup(List<String> group, String stuClass);
}
