package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-06-24 13:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart implements Serializable {

    @Id
    private String _id;
    private String userName;
    private String name;
    private Integer price;
    private String image;
    private String skuId;
    private Integer num;
}
