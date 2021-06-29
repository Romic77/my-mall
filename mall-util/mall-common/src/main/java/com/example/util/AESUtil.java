package com.example.util;

import com.example.util.Base64Util;
import com.example.util.MD5;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

public class AESUtil {


    /****
     * AES加密/解密
     * @param buffer:密文/明文
     * @param appsecret:秘钥
     * @param mode:加密/解密模式  1 加密  2 解密
     * @return
     */
    public static byte[] encryptAndDecrypt(byte[] buffer,String appsecret,Integer mode) throws Exception{
        //1:加载加密解密算法处理对象（包含算法、秘钥管理）
        Security.addProvider(new BouncyCastleProvider());

        //2:根据不同算法创建秘钥   1）秘钥的字节数组   2）加密算法
        SecretKeySpec secretKeySpec = new SecretKeySpec(appsecret.getBytes("UTF-8"),"AES");

        //3:设置加密模式（无论是加密还是解析，模式一致）
        // 1): AES/ECB/KPS7Padding 设置算法
        // 2):指定算法库对象
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding","BC");
        //4:初始化加密配置
        cipher.init(mode,secretKeySpec);
        //5:执行加密/解密
        return cipher.doFinal(buffer);
    }

    /***
     * 加密解密测试
     * 128/192/256
     */
    public static void main_backup(String[] args) throws Exception{
        String txt = "SpringCloud Alibaba";
        String appsecret="aaaaaaaaaaaaaaaa";
        Integer mode=1;

        //加密
        byte[] bytes = encryptAndDecrypt(txt.getBytes("UTF-8"), appsecret, mode);
        String encode = Base64Util.encode(bytes);
        System.out.println(encode);

        //解密 ->解码Base64->解密
        byte[] decode = encryptAndDecrypt(Base64Util.decode(encode), appsecret, 2);
        System.out.println(new String(decode, "UTF-8"));

    }

    /***
     * 加密解密测试
     * 128/192/256
     */
    public static void main(String[] args) throws Exception{
        String txt = "SpringCloud Alibaba";
        String appsecret="aaaaaaaaaaaaaaaa";
        appsecret = MD5.md5(appsecret);
        System.out.println(appsecret);
        Integer mode=1;

        //加密
        byte[] bytes = encryptAndDecrypt(txt.getBytes("UTF-8"), appsecret, mode);
        String encode = Base64Util.encode(bytes);
        System.out.println(encode);

        //解密 ->解码Base64->解密
        byte[] decode = encryptAndDecrypt(Base64Util.decode(encode), appsecret, 2);
        System.out.println(new String(decode, "UTF-8"));

    }
}