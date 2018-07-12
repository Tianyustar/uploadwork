package com.jit.uploadwork.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author libre
 * @since 2018-04-22
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false ) // 数据库中不存在此字段，忽略此字段
    private String redisKey;//redis中的key
    private String userName;
    private String password;
    private String role;
    @TableId(value = "student_num")
    private Integer studentNum;

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String keyStr) {
        this.redisKey = "user_"+keyStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }


    @Override
    public String toString() {
        return "User{" +
                "redisKey='" + redisKey + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", studentNum=" + studentNum +
                '}';
    }
}
