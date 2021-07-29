package com.example.user.controller;

import com.alibaba.nacos.client.utils.IPUtil;
import com.example.mall.util.IpUtil;
import com.example.model.UserInfo;
import com.example.user.service.UserInfoService;
import com.example.util.JwtToken;
import com.example.util.MD5;
import com.example.util.RespResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/info")
public class UserInfoController {
    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public RespResult<String> login(HttpServletRequest request, @RequestParam String username, @RequestParam String pwd) throws Exception {
        //登录
        UserInfo userInfo = userInfoService.getById(username);
        if (userInfo != null) {
            //匹配密码是否一致
            if (StringUtils.equals(userInfo.getPassword(), pwd)) {
                //封装用户信息实现加密
                Map<String,Object> dataMap = new HashMap<String,Object>();
                dataMap.put("username", userInfo.getUsername());
                dataMap.put("name", userInfo.getName());
                dataMap.put("roles", userInfo.getRoles());

                //获取客户端ip
                String ip = IpUtil.getIpAddr(request);
                String md5Ip=MD5.md5(ip);
                dataMap.put("ip",md5Ip);
                logger.info("当前ip为{};当前ip的md5为{}",ip,md5Ip);
                //创建令牌
                String token = JwtToken.createToken(dataMap);
                return RespResult.ok(token);
            }
            return RespResult.error("账户密码不正确");
        }

        return RespResult.error("账户不存在");
    }
}
