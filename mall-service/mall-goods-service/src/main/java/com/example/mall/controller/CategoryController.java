package com.example.mall.controller;

import com.example.goods.model.Category;
import com.example.mall.service.CategoryService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-10 09:42
 */
@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据分类父ID查询子分类
     * @param id
     * @return
     */
    @GetMapping("/parent/{id}")
    public RespResult<List<Category>> findByParentId(@PathVariable("id") Integer id) {
        return RespResult.ok(categoryService.findByParentId(id));
    }

    @GetMapping("/{id}")
    public RespResult<Category> one(@PathVariable("id") Integer id){
        return RespResult.ok(categoryService.getById(id));
    }

}
