package com.example.mall.controller;

import com.example.goods.model.Product;
import com.example.mall.service.SpuService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 13:43
 */
@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {
    @Autowired
    private SpuService spuService;

    /**
     * 产品保存
     */
    @PostMapping("/save")
    public RespResult save(@RequestBody Product product) {

        return RespResult.ok();
    }
}
