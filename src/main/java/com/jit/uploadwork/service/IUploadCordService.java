package com.jit.uploadwork.service;

import com.jit.uploadwork.entity.UploadCord;
import com.baomidou.mybatisplus.service.IService;
import com.jit.uploadwork.entity.User;
import com.jit.uploadwork.utils.TMessage;

import org.springframework.web.multipart.MultipartFile;
import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author libre
 * @since 2018-04-22
 */
public interface IUploadCordService extends IService<UploadCord> {

    /**
     * 存储文件到路径filePath,并向数据库中插入记录
     * @param user
     * @param file
     * @return
     */
     UploadCord insertUploadRecord(User user , Date date, MultipartFile file);

    /**
     *
     * 返回文件最后一次上传的信息 （暂时不支持再次下载的功能
     *
     * @param user
     * @return
     */
     TMessage getUploadInfo(User user);

     TMessage  delUploadInfo(User user,  int id);

}
