package com.ys.mvc.service;


import com.ys.mvc.dao.pojo.Order;
import com.ys.mvc.dao.pojo.Pageable;
import com.ys.mvc.dao.pojo.User;

import java.util.List;

/**
 * Created by peijian.wang on 2017/6/18.
 */
public interface OrderService {


    Pageable<Order> getList(Pageable<Order> pageable, User user);

    List<Order> getUserOrder(User user);

    void addOrder(Order order);

    void closeOrder(Order order);

    Order getOrderDetail(Order order);
}
