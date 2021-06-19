package com.example.search.controller;

import com.example.search.model.SkuEs;
import com.example.search.service.SkuSearchService;
import com.example.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SkuSearchController {
    @Autowired
    private SkuSearchService skuSearchService;

    @GetMapping
    public RespResult<Map<String,Object>> search(@RequestParam Map<String, Object> searchMap){

        return RespResult.ok(skuSearchService.search(searchMap));
    }

    @PostMapping("/add")
    public RespResult add(@RequestBody SkuEs skuEs){
        skuSearchService.add(skuEs);
        return  RespResult.ok();
    }

    @DeleteMapping("/del/{id}")
    public RespResult del(@PathVariable("id")String id){
        skuSearchService.del(id);
        return RespResult.ok();
    }
}
