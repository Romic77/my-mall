package com.example.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-19 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DruidPage<T> {
    //总记录数
    private Integer total;
    //每页显示条数
    private Integer size;
    //当前页
    private Integer page;
    //偏移量
    private Long offset;
    //总页数
    private Integer totalPages;
    //数据集合
    private T data;
    //排序字段
    private String sort;
    //排序类型
    private String sortType;

    /**
     * 初始化计算偏移量
     */
    public DruidPage(Integer page, Integer size, String sort, String sortType) {
        this.size = size;
        this.page = page;
        this.sort = sort;
        this.sortType = sortType;
        if (page <= 0) {
            this.page = 1;
        }
        //计算偏移量
        this.offset = Long.valueOf((this.page - 1) * size);
    }

    public DruidPage(Integer page, Integer size) {
        this.size = size;
        this.page = page;

        if (page <= 0) {
            this.page = 1;
        }
        //计算偏移量
        this.offset = Long.valueOf((this.page - 1) * size);
    }

    /**
     * 计算分页参数
     */
    public DruidPage<T> pages(T data, Integer total) {
        //总记录数
        this.total = total;
        //数据
        this.data = data;
        //总页数
        if (this.total > 0) {
            this.totalPages = this.total % this.size == 0 ? this.total / this.size : (this.total / this.size) + 1;
        } else {
            this.totalPages = 0;
        }
        return this;
    }
}
