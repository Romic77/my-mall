package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.model.Address;
import com.example.user.mapper.AddressMapper;
import com.example.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 16:21
 */
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> addressList(String username) {
        QueryWrapper<Address> queryWrapper=new QueryWrapper<Address>();
        queryWrapper.eq("username",username);

        return addressMapper.selectList(queryWrapper);
    }
    /**
     * 查询用户收件列表
     */

}
