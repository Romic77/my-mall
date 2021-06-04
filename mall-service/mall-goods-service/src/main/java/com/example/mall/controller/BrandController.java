package com.example.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.goods.model.Brand;
import com.example.mall.service.BrandService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 增加方法
     * @return com.example.util.RespResult
     * @author chenqi
     * @date 2021/6/4 23:47
    */
    @PostMapping
    public RespResult add(@RequestBody Brand brand){
        brandService.save(brand);
        return RespResult.ok();
    }

    /**
     * 修改方法
     * @param brand
     * @return com.example.util.RespResult
     * @author chenqi
     * @date 2021/6/4 23:50
    */
    @PutMapping
    public RespResult update(@RequestBody Brand brand){
        brandService.updateById(brand);
        return RespResult.ok();
    }

    @DeleteMapping("/{id}")
    public RespResult delete(@PathVariable("id") String id){
        brandService.removeById(id);
        return RespResult.ok();
    }

    @PostMapping("/search")
    public RespResult<List<Brand>> queryList(@RequestBody Brand brand){
        List<Brand> brands=brandService.queryList(brand);
        return RespResult.ok(brands);
    }

    @PostMapping("/search/{page}/{size}")
    public RespResult<Page<Brand>> queryPageList(@RequestBody Brand brand,@PathVariable("page")Long page,@PathVariable("size") Long size){
        Page<Brand> brands=brandService.queryPageList(brand,page,size);
        return RespResult.ok(brands);
    }

}
