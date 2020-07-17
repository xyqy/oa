package com.qyy.oa.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: qiyayu
 * @date: 2020-07-07 13:40
 * @description: md5工具类
 */
public class Md5Util {
    public static String md5(String buffer) {
        String string = null;
        char[] hexDigist = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(buffer.getBytes());
            byte[] datas = md.digest();
            char[] str = new char[2 * 16];
            int k = 0;
            int len = 16;
            for (int i = 0; i < len; i++) {
                byte b = datas[i];
                str[k++] = hexDigist[b >>> 4 & 0xf];
                str[k++] = hexDigist[b & 0xf];
            }
            string = new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return string;
    }
}
