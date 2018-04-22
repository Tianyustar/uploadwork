package com.jit.uploadwork.utils;


import java.io.IOException;

public class StringUtil {

        public static boolean isEmpty(String str) {
            return str == null || str.equals("")||str.trim().equals("");
        }

        public static void main(String[] args) throws IOException {
            System.out.println(StringUtil.isEmpty("    "));
        }
    }


