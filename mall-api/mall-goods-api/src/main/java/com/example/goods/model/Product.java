package com.example.goods.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*****
 * @Author: 波波
 * @Description: 云商城
 ****/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    // Spu
    private Spu spu;
    // Sku
    private List<Sku> skus;
}
