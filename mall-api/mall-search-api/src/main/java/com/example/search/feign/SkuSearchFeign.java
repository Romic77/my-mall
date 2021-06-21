package com.example.search.feign;


import com.example.search.model.SkuEs;
import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("mall-search")
@RequestMapping("/search")
public interface SkuSearchFeign {

    @GetMapping
    RespResult<Map<String,Object>> search(@RequestParam(required = false) Map<String,Object> searchMap);

    @PostMapping("/add")
    RespResult add(@RequestBody SkuEs skuEs);

    @DeleteMapping("/del/{id}")
    RespResult del(@PathVariable("id")String id);

}
