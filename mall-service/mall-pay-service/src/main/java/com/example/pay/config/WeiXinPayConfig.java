package com.example.pay.config;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-01 13:09
 */
@Component
public class WeiXinPayConfig extends WXPayConfig {

    //微信支付信息
    @Value("${payconfig.weixin.appId}")
    private String appId;       //应用ID
    @Value("${payconfig.weixin.mchID}")
    private String mchID;       //商户号
    @Value("${payconfig.weixin.key}")
    private String key;         //秘钥
    @Value("${payconfig.weixin.notifyUrl}")
    private String notifyUrl;   //回调地址
    @Value("${payconfig.weixin.certPath}")
    private String certPath;    //证书路径
    //证书字节数组
    private byte[] certData;

    @Override
    public String getAppID() {
        return this.appId;
    }

    @Override
    public String getMchID() {
        return this.mchID;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    /***
     * 获取商户证书内容
     * @return
     */
    @Override
    public InputStream getCertStream() {
        /****
         * 加载证书
         */
        if(certData==null){
            synchronized (this){
                try {
                    if(certData==null) {
                        File file = new File(certPath);
                        InputStream certStream = new FileInputStream(file);
                        this.certData = new byte[(int) file.length()];
                        certStream.read(this.certData);
                        certStream.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    /***
     * 获取WXPayDomain, 用于多域名容灾自动切换
     * @return
     */
    @Override
    public IWXPayDomain getWXPayDomain() {
        // 这个方法需要这样实现, 否则无法正常初始化WXPay
        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }
            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;
    }
}
