package com.jit.uploadwork.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author libre
 * @since 2018-04-26
 */
public class UploadCord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer studentNum;
    private Date time;
    private String fileName;
    private String urlAddress;

    public UploadCord(Integer studentNum, Date time, String fileName, String urlAddress) {
        this.studentNum = studentNum;
        this.time = time;
        this.fileName = fileName;
        this.urlAddress = urlAddress;
    }

    public UploadCord() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrlAddress() {
        return urlAddress;
    }

    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    @Override
    public String toString() {
        return "UploadCord{" +
        ", id=" + id +
        ", studentNum=" + studentNum +
        ", time=" + time +
        ", fileName=" + fileName +
        ", urlAddress=" + urlAddress +
        "}";
    }
}
