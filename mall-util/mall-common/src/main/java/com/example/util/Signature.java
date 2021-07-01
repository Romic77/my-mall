package com.example.util;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Signature {

    //秘钥
    private String skey;

    //验签加盐值
    private String salt;

    public Signature(String skey, String salt) {
        this.skey = skey;
        this.salt = salt;
    }

    /***
     * 密文解密，并转成Map,并对Map数据进行验签
     * @param ciphertext
     * @return
     */
    public Map<String,String> security(String ciphertext) throws Exception {
        //1.解密
        String decrypt =new String( AESUtil.encryptAndDecrypt(Base64Util.decodeURL(ciphertext), skey,2) , "UTF-8");
        //2.明文转Map并根据Key降序
        Map<String,String> decryptTreeMap = JSON.parseObject(decrypt, TreeMap.class);
        //3.验签
        String signature = decryptTreeMap.remove("signature");
        String localSignature = MD5.md5(JSON.toJSONString(decryptTreeMap),salt);
        //true 验签成功，false  验签失败
        return signature.equals(localSignature)? decryptTreeMap : null;
    }


    /***
     * Map加密，携带验签
     */
    public String security(Map<String,String> dataMap) throws Exception {
        //1.将dataMap转成TreeMap
        dataMap = JSON.parseObject(JSON.toJSONString(dataMap),TreeMap.class);
        //2.将TreeMap转成JSON
        String treeJson = JSON.toJSONString(dataMap);
        //3.执行MD5摘要加密
        String signature = MD5.md5(treeJson,salt);
        //4.摘要加密内容添加到dataMap中
        dataMap.put("signature",signature);
        //5.AES加密dataMap
        return Base64Util.encodeURL(AESUtil.encryptAndDecrypt(JSON.toJSONString(dataMap).getBytes("UTF-8"),skey,1));
    }


    public static void main(String[] args) throws Exception {
        String skey="ab2cc473d3334c39";
        String salt="XPYQZb1kMES8HNaJWW8+TDu/4JdBK4owsU9eXCXZDOI=";

        // 需要加密的字串
        Map<String,String> dataMap = new HashMap<String,String>();
        dataMap.put("body", "商城订单-");
        dataMap.put("out_trade_no","AAA");
        dataMap.put("device_info", "PC");
        dataMap.put("fee_type", "CNY");
        dataMap.put("total_fee", "1");
        dataMap.put("spbill_create_ip","192.168.100.130");
        dataMap.put("notify_url", "http://www.example.com/wxpay/notify");
        dataMap.put("trade_type", "NATIVE");  // 此处指定为扫码支付

        Signature signature = new Signature(skey,salt);
        //String cSrc = "5Ei1DggFVRPJ7Lses+ox3S40NV4dlYIuKSwRhvEbuDWlGKPj92pQOf/yvbAxKdmj1LCc8n6YRhMdlPDFYfx4FMPW5LHLCoEhmBQBtvb7ia8IWcHnMGPX9sX5eaOLolzeURLhxGVJZ8GKvTIPuaLW0mmaJmIrI1bSL6did6h0QAe5SNnQJyORRydtPEXp5nPteiUT8cW4mA2MNiQyUNG/+cCzVdBVPwkNIrh0WU2gqgcYbuCuBJMNvt3a/lpBzkCf0zlwoA5GWiAFZ6FpBtwR/Mj8rkmqe7IXnWjPv2dSMwbSFhZAXGmG8dUsYj/u7iNLzfVBcncbG5ZEWdw0+IXFEThTLJbA26kdrt4f58zRHkrRg9y7A6uVyDvduXBw0743ZXuJgI2a9pt2vq4CRY2/xA==";
        String cSrc = signature.security(dataMap);
        System.out.println(cSrc);
        Map<String, String> map1 = signature.security(cSrc);
        System.out.println(map1);

    }
}
