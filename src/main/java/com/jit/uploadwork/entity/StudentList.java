package com.jit.uploadwork.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author libre
 * @since 2018-05-30
 */
public class StudentList implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer stuNo;
    private String stuName;
    private String stuClass;


    public Integer getStuNo() {
        return stuNo;
    }

    public void setStuNo(Integer stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }


    @Override
    public String toString() {
        return "StudentList{" +
        ", stuNo=" + stuNo +
        ", stuName=" + stuName +
        ", stuClass=" + stuClass +
        "}";
    }
}
