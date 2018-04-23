package com.ys.mvc.controller.app;

import com.ys.mvc.controller.oa.OrderAPI;
import com.ys.mvc.dao.pojo.Order;
import com.ys.mvc.dao.pojo.User;
import com.ys.mvc.service.OrderService;
import com.ys.mvc.service.UserService;
import com.ys.utils.APIResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by peijian.wang on 2017/6/18.
 */
@RestController
@RequestMapping(value = "/app/order")
public class AppOrderAPI {

    private static Logger log = Logger.getLogger(OrderAPI.class);

    private final OrderService orderService;

    private final UserService userService;

    public AppOrderAPI(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping(value = "/addOrder")
    public APIResult addOrder(@RequestBody Order order) throws IOException {
        orderService.addOrder(order);
        return new APIResult();
    }

    @RequestMapping(value = "/getUserOrder")
    public APIResult getUserOrder(@RequestBody User user) throws IOException {
        return new APIResult(orderService.getUserOrder(user));
    }

    @RequestMapping(value = "/closeOrder")
    public APIResult closeOrder(@RequestBody Order order) throws IOException {
        orderService.closeOrder(order);
        return new APIResult();
    }

    @RequestMapping(value = "/getOrderDetail")
    public APIResult getOrderDetail(@RequestBody Order order) throws IOException {
        return new APIResult(orderService.getOrderDetail(order));
    }

}
