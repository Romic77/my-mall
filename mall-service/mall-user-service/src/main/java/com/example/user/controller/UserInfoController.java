package com.example.user.controller;

import com.example.model.UserInfo;
import com.example.user.service.UserInfoService;
import com.example.util.JwtToken;
import com.example.util.RespResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/user/info")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public RespResult<String> login(@RequestParam String username, @RequestParam String pwd) {
        //登录
        UserInfo userInfo = userInfoService.getById(username);
        if (userInfo != null) {
            //匹配密码是否一致
            if (StringUtils.equals(userInfo.getPassword(), pwd)) {
                //封装用户信息实现加密
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("username", userInfo.getUsername());
                dataMap.put("name", userInfo.getName());
                dataMap.put("roles", userInfo.getRoles());

                //创建令牌
                String token = JwtToken.createToken(dataMap);
                return RespResult.ok(token);
            }
            return RespResult.error("账户密码不正确");
        }

        return RespResult.error("账户不存在");
    }
}
