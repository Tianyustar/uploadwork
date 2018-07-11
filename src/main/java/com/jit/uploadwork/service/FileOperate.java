package com.jit.uploadwork.service;

import com.jit.uploadwork.global.ConstVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  文件保存类
 */
public class FileOperate {

    /**
     * 保存文件到本地

     * @param uploadUserName
     * @return  文件在服务器的url地址
     */
    public static String getFileAddress( String uploadUserName, Date date,  String fileName ) {


           DateFormat df = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
           String  strTime = df.format(date);
           String address = ConstVariable.FILE_ROOT_ADDRESS + File.separator +ConstVariable.FILE_CATALOGUE; //  根加目录
           String addedName = strTime + "_"+ uploadUserName + "_" + fileName; //  新的文件名加上 上传文件用户名称加上 时间
           String fileUrl =  address + File.separator + addedName;
        return  fileUrl;
    }

    /**
     *  将文件保存到本地
     * @param uploadUserName
     * @param date
     * @param file
     * @return
     */

    public static String  saveFileToNative( String uploadUserName, Date date, MultipartFile file) {
            String fileName = file.getOriginalFilename();
            String saveAddress = getFileAddress(uploadUserName, date, fileName);
            File saveFile = new File(saveAddress); // new 一个文件
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
        return  saveAddress;
    }

    public  static String  renameFile(String filePath) {

        File file = new File(filePath);
        String newFileName =  filePath + "_del";
        File renameFile = new File(newFileName);
        if (!file.exists()) return  null;
        if (renameFile.exists()) {
           Boolean res =   renameFile.delete(); // 删除文件
            if (!res) return  null;
        }
       if ( !file.renameTo(renameFile) )   //  重命名
       {
            return null;
       }
       return  newFileName;
    }

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        System.out.println(File.separator);
        String url = FileOperate.getFileAddress("刘运星" , new Date(), "第六章实训.txt");
        System.out.println(url);
    }
}
