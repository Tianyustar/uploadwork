package com.jit.uploadwork.controller;


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

    @RequestMapping("")
    public String test()  {
        return "success test";
    }


}

