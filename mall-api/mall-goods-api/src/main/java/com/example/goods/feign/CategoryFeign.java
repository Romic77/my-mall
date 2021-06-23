package com.example.goods.feign;

import com.example.goods.model.Category;
import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-23 13:29
 */
@FeignClient("mall-goods")
@RequestMapping("/category")
public interface CategoryFeign {
    @GetMapping("/{id}")
    public RespResult<Category> one(@PathVariable("id") Integer id);

}
