package com.example.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5 {

    /**
     * MD5方法
     * @param text 明文
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text) throws Exception {
        //加密后的字符串
        String encode= DigestUtils.md5Hex(text);
        return encode;
    }

    /**
     * MD5方法
     * @param text 明文
     * @param key 盐
     * @return 密文
     * @throws Exception
     */
    public static String md5(String text, String key) throws Exception {
        //加密后的字符串
        String encode= DigestUtils.md5Hex(text + key);
        return encode;
    }

    /**
     * MD5验证方法
     * @param text 明文
     * @param key 密钥/盐
     * @param md5 密文
     * @return true/false
     * @throws Exception
     */
    public static boolean verify(String text, String key, String md5) throws Exception {
        //根据传入的密钥进行验证
        String md5Text = md5(text, key);
        return md5Text.equalsIgnoreCase(md5);
    }
}
