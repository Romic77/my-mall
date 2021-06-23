package com.example.controller;

import com.example.service.PageService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-23 14:15
 */
@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 生成静态页
     */
    @GetMapping("/{id}")
    public RespResult html(@PathVariable("id") String id) throws Exception {
        pageService.html(id);
        return RespResult.ok();
    }
}
