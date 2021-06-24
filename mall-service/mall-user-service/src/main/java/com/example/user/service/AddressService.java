package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.model.Address;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 16:21
 */
public interface AddressService extends IService<Address> {
    /**
     * 查询用户收件列表
     */
    List<Address> addressList(String username);
}
