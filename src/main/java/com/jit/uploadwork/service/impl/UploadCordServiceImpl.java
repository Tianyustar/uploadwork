package com.jit.uploadwork.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jit.uploadwork.entity.UploadCord;
import com.jit.uploadwork.entity.User;
import com.jit.uploadwork.mapper.UploadCordMapper;
import com.jit.uploadwork.service.FileOperate;
import com.jit.uploadwork.service.IUploadCordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jit.uploadwork.utils.TMessage;
import com.jit.uploadwork.utils.TransformDateString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.text.resources.FormatData;

import javax.swing.event.TableModelEvent;
import javax.xml.crypto.Data;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author libre
 * @since 2018-04-22
 */
@Service
public class UploadCordServiceImpl extends ServiceImpl<UploadCordMapper, UploadCord> implements IUploadCordService {


    @Override
    public UploadCord insertUploadRecord(User user, Date date, MultipartFile file) {

        /// 上传文件
        String saveAddress ;
        if ( (saveAddress = FileOperate.saveFileToNative(user.getUserName(),date,file))!= null ) {
             // 存储记录插入数据库
            UploadCord uploadCord  = new UploadCord(user.getStudentNum(),date ,file.getOriginalFilename(),saveAddress) ; // 记录文件上传位置
            insert(uploadCord);
            return uploadCord;
        }
        return  null;

    }

    @Override
    public TMessage getUploadInfo(User user) {
       // UploadCord uploadCord = selectOne();
        List<UploadCord> uploadCords = selectList(new EntityWrapper<UploadCord>().orderBy("time",false).eq("student_num",user.getStudentNum())); // 降序
        if (uploadCords == null || uploadCords.size() == 0) {
            return  new TMessage(TMessage.CODE_FAILURE,"该学生没有上传记录");
        }
        HashMap<String , String > hashMap = new HashMap<>();
        hashMap.put("id",uploadCords.get(0).getId()+"");
        hashMap.put("fileName",uploadCords.get(0).getFileName());
        hashMap.put("time", TransformDateString.transForm(uploadCords.get(0).getTime(),"yyyy-MM-dd hh:mm:ss"));
        return  new TMessage(TMessage.CODE_SUCCESS,"查询记录成功",hashMap);// 获取记录
    }

    @Override
    public TMessage delUploadInfo(User user, int id) {
       // 根据id选择记录
        UploadCord uploadCord =  selectById(id);
        if (uploadCord == null) {
            return  new TMessage(TMessage.CODE_FAILURE, "没有查到所需要的文件");
        }
        if (!uploadCord.getStudentNum().equals(user.getStudentNum())) {
            return new TMessage(TMessage.CODE_FAILURE,"文件删除异常");
        }
        // 改变文件名
        String newUrl;
        String url = FileOperate.renameFile(uploadCord.getUrlAddress());
        if (url == null) {
            return  new TMessage(TMessage.CODE_FAILURE,"文件删除失败");
        }
        deleteById(id);// 删除
        return new TMessage(TMessage.CODE_SUCCESS,"文件删除成功");
    }


    /**
     * // 将文件保存到特定的路径
     * @param filePath  // 文件路径
     * @param file  // 文件
     * @return 返回文件的上传路径
     */

}
