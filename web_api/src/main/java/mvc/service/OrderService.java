package mvc.service;

import mvc.dao.pojo.Order;
import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.User;

import java.util.List;

/**
 * Created by peijian.wang on 2017/6/18.
 */
public interface OrderService {


    Pageable<Order> getList(Pageable<Order> pageable, User user);

    List<Order> getUserOrder(User user);

    void addOrder(Order order);
}
