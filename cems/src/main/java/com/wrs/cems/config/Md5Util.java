package com.wrs.cems.config;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5Util {

    // salt
    // 循环队列，手型函数
    public static String md5Hash(String password){
        Md5Hash md5Hash = new Md5Hash(password);
        String s = md5Hash.toString();
        return s;
    }
}
