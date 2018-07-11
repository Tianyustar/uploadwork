package com.jit.uploadwork.controller;


import com.jit.uploadwork.service.IStudentListService;
import com.jit.uploadwork.utils.TMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 端控制器
 * </p>
 *
 * @author libre
 * @since 2018-05-30
 */
@RestController
@RequestMapping("/studentList")
public class StudentListController {

    IStudentListService iStudentListService;
    @Autowired

    public StudentListController(IStudentListService iStudentListService) {
        this.iStudentListService = iStudentListService;
    }

    @RequestMapping("/ClassList")
    public List<String> getClassList(){
        List<String> list = iStudentListService.getClassInfo();
        return list;
    }

    @PostMapping("/dividList")
    public TMessage dividList(  String[] question,  String stuClass){
        String erroInfo="";
        System.out.println(stuClass);
        System.out.println(question);
        List<String> questions =  new ArrayList<String>(Arrays.asList(question));
        if (questions == null || questions.size() == 0) erroInfo = "分组标准不能为空 ";
        if (stuClass == null || stuClass.equals(""))  erroInfo += " 班级不能为空";
        if (erroInfo.equals(""))
         return iStudentListService.dividGroup(questions,stuClass);
        return new TMessage(TMessage.CODE_FAILURE,erroInfo);

    }
}

