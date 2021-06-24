package com.example.goods.feign;

import com.example.goods.model.Sku;
import com.example.model.Cart;
import com.example.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-17 13:52
 */
@FeignClient("mall-goods")
@RequestMapping("/sku")
public interface SkuFeign {

    @PostMapping("/dcount")
    RespResult decount(@RequestBody List<Cart> cartList);

    @GetMapping("/aditems/type")
    public List<Sku> typeItems(@RequestParam("id") Integer id);

    @DeleteMapping("/aditems/type")
    public RespResult delTypeItems(@RequestParam("id") Integer id);

    @PutMapping("/aditems/type")
    public RespResult<List<Sku>> updateTypeItems(@RequestParam("id") Integer id);

    @GetMapping("/{id}")
    public RespResult<Sku> findProductById(@PathVariable("id") String id);
}
