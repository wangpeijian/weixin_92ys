package com.ys.mvc.service;


import com.ys.mvc.dao.pojo.Product;

/**
 * Created by peijian.wang on 2017/4/4.
 */
public interface ProductItemService {

    int add(Product product);

    int delete(Product oldProduct);

    int update(Product oldProduct, Product newProduct);

    void setItems(Product product);
}
