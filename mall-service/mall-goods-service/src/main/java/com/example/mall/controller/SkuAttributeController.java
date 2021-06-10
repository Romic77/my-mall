package com.example.mall.controller;

import com.example.goods.model.SkuAttribute;
import com.example.mall.service.SkuAttributeService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 10:48
 */
@RestController
@RequestMapping("/skuAttribute")
public class SkuAttributeController {
    @Autowired
    private SkuAttributeService skuAttributeService;

    /**
     * 根据分类id查询属性集合
     */
    @GetMapping("/category/{id}")
    public RespResult<List<SkuAttribute>> categoryAttributeList(@PathVariable("id") Integer id){
        return RespResult.ok(skuAttributeService.queryList(id));
    }
}
