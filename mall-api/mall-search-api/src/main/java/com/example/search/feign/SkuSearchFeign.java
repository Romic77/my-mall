package com.example.search.feign;


import com.example.search.model.SkuEs;
import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("mall-search")
@RequestMapping("/search")
public interface SkuSearchFeign {

    @PostMapping("/add")
    RespResult add(@RequestBody SkuEs skuEs);

    @DeleteMapping("/del/{id}")
    RespResult del(@PathVariable("id")String id);

}
