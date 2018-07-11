package com.jit.uploadwork.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5(String str) {
        StringBuilder sb = new StringBuilder();
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
                'b', 'c', 'd', 'e', 'f'};
        byte[] b = str.getBytes();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        byte[] md5 = md.digest(b);
        for (byte m : md5) {
            sb.append(chars[(m >> 4) & 0x0f]);
            sb.append(chars[m & 0x0f]);
        }
        return sb.toString();
    }

    public static String getMD5(Object obj) {
        return getMD5(obj.toString());
    }

/*    public static String getSign(String token, String timeMillion) {
        return getMD5(token + timeMillion + Consts.SECRET);
    }*/

    public static void main(String[] args) {
        System.out.println(getMD5("liuyunxing"));
        System.out.println(getMD5("1234"));
        System.out.println(getMD5(getMD5("123456")));
        System.out.println("编码错了 ");
    }
}
