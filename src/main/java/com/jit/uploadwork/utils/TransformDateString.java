package com.jit.uploadwork.utils;

import freemarker.template.SimpleDate;
import org.junit.Test;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformDateString {

    @Test
    public  static String transForm(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return  df.format(date);
    }
}
