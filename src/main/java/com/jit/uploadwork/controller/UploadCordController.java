package com.jit.uploadwork.controller;


import com.jit.uploadwork.annotation.CurrentUser;
import com.jit.uploadwork.annotation.LoginRequired;
import com.jit.uploadwork.entity.UploadCord;
import com.jit.uploadwork.entity.User;
import com.jit.uploadwork.service.IUploadCordService;
import com.jit.uploadwork.utils.TMessage;
import com.jit.uploadwork.utils.TransformDateString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author libre
 * @since 2018-04-22
 */
@RestController
@CrossOrigin
@RequestMapping("/uploadCord")
public class UploadCordController {


    private IUploadCordService iUploadCordService;

    @Autowired
    public UploadCordController(IUploadCordService iUploadCordService) {
        this.iUploadCordService = iUploadCordService;
    }



    @RequestMapping("/upload")
    @LoginRequired
    public TMessage uploadFile(@CurrentUser User user, @RequestBody MultipartFile file) {

        if( file == null) {
            return  new TMessage(TMessage.CODE_FAILURE, "上传文件不可以为空");
        }
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        Date nowDate = new Date();
        UploadCord uploadCord = iUploadCordService.insertUploadRecord(user,nowDate,file);
        Map<String, String> hashMap = new HashMap<>();
        if (uploadCord != null) {
            hashMap.put("id",uploadCord.getId()+"");
            hashMap.put("time", TransformDateString.transForm(nowDate,"yyyy-MM-dd hh:mm:ss"));
            hashMap.put("filename",uploadCord.getFileName());
            return  new TMessage(TMessage.CODE_SUCCESS,"上传成功",hashMap);
        }
        return new TMessage(TMessage.CODE_FAILURE,"上传文件失败");
    }


    @RequestMapping("/uploadInfo")
    @LoginRequired
    public TMessage getUploadInfo(@CurrentUser User user) {
        return iUploadCordService.getUploadInfo(user);
    }

    @RequestMapping("/delfile")
    @LoginRequired
    public TMessage  delFile(@CurrentUser User user, int id) {
        return iUploadCordService.delUploadInfo(user, id);
    }

}

