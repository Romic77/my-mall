package com.example.feign;

import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-21 13:57
 */
@FeignClient("mall-dw")
public interface HotGoodsFeign {

    @PostMapping("/search/{size}/{hour}/{max}")
    public RespResult<List<Map<String, String>>> searchHotGoods(@PathVariable("size") Integer size, @PathVariable("hour") Integer hour,
                                                                @RequestBody String[] urls, @PathVariable("max") Integer max) ;
}
