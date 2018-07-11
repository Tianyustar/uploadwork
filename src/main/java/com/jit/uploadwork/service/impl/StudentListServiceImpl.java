package com.jit.uploadwork.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jit.uploadwork.entity.StudentList;
import com.jit.uploadwork.mapper.StudentListMapper;
import com.jit.uploadwork.service.IStudentListService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jit.uploadwork.utils.TMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author libre
 * @since 2018-05-30
 */
@Service
public class StudentListServiceImpl extends ServiceImpl<StudentListMapper, StudentList> implements IStudentListService {

    @Override
    public List<String> getClassInfo() {
        return  baseMapper.getClassList();
    }

    @Override
    public TMessage dividGroup(List<String> group, String stuClass) {

        TMessage tMessage;
        List<StudentList> studentLists;
        Map<StudentList, String> restMap = new HashMap<>();
        String errInfo = "分组出现错误";
        int len = 0;
        studentLists = baseMapper.selectList(new EntityWrapper<StudentList>().eq("stu_class", stuClass));
        if (studentLists != null && studentLists.size() > 0 && group.size() > 0) {
            len = group.size();// 一共有多少个组
            // 混排序
            Collections.shuffle(studentLists);
            //
            for (int i = 0; i < studentLists.size(); i++) {
                int index = i % group.size();
                restMap.put(studentLists.get(i), group.get(index)); //  分配
            }
            tMessage = new TMessage(TMessage.CODE_SUCCESS,"分组成功",restMap);
            return  tMessage;

        }
        if (studentLists == null || studentLists.size() == 0 ) errInfo = "所查询班级没有学生";
        if (group == null) errInfo= "分组数目需要大于0";
        tMessage = new TMessage(TMessage.CODE_FAILURE, errInfo);
        return tMessage;
    }


}
