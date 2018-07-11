package com.jit.uploadwork.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.jit.uploadwork.annotation.CurrentUser;
import com.jit.uploadwork.annotation.LoginRequired;
import com.jit.uploadwork.entity.User;
import com.jit.uploadwork.service.IUserService;
import com.jit.uploadwork.utils.MD5Util;
import com.jit.uploadwork.utils.RegexUtils;
import com.jit.uploadwork.utils.TMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author libre
 * @since 2018-04-22
 */
@RestController

@RequestMapping("/user")
public class UserController {

    IUserService iUserService;
    @Autowired
    public  UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @RequestMapping("")
    public String test()  {
        return "success test";
    }

    @RequestMapping("/login")
    @CrossOrigin
    public TMessage login( String studentNum,  String password)  {


        if (studentNum.trim().length() != 10 || !RegexUtils.checkDigit(studentNum))  {
            return  new TMessage(TMessage.CODE_FAILURE, "请输入正确的学号格式");
        }
         int studentNumInt = Integer.parseInt(studentNum);
        System.out.println(studentNum + "____" + password);
        return  iUserService.login(studentNumInt, password);
    }

    @CrossOrigin
    @RequestMapping("/modifyPwd")
    @LoginRequired
    public TMessage modifyPwd(@CurrentUser User user, String oldPassword, String newPassword) {
        return iUserService.modifyPwd(user, oldPassword, newPassword);
    }

}

