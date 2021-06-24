package com.example.mapper;

import com.example.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 14:39
 */
public interface CartMapper extends MongoRepository<Cart,String> {
}
