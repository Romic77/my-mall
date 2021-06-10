package com.example.mall.controller;

import com.example.goods.model.Category;
import com.example.mall.service.CategoryService;
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
 * @Date: Created in 2021-06-10 09:42
 */
@RestController
@RequestMapping("/category")
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


}