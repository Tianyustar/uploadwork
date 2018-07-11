package com.jit.uploadwork.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jit.uploadwork.entity.User;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;

/*
 获得token
 token 是个长字符串，客户端向服务器申请。在需要登录的请求中每次都携带上申请到的token，服务器端验证 token 的有效性，只有验证通过了才允许访问。
 */
@Service
public class AuthenticationService {

    public String getToken(User user){
        String token = "";
        try {
            token = JWT.create().withAudience(user.getStudentNum() +"").withIssuedAt(new Date())// 将userid保存到token里面
                    .sign(Algorithm.HMAC256(user.getPassword()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  token;
    }
}