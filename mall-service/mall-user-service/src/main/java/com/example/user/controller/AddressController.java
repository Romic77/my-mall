package com.example.user.controller;

import com.example.model.Address;
import com.example.user.service.AddressService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 16:25
 */
@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 用户收件地址列表
     */
    @GetMapping("/list")
    public RespResult<List<Address>> list() {
        return RespResult.ok(addressService.addressList("zs"));
    }
}
