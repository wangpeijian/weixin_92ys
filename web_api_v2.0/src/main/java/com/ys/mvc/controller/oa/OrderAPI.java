package com.ys.mvc.controller.oa;

import com.ys.mvc.dao.pojo.Order;
import com.ys.mvc.dao.pojo.Pageable;
import com.ys.mvc.dao.pojo.User;
import com.ys.mvc.service.OrderService;
import com.ys.mvc.service.UserService;
import com.ys.utils.APIResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/6/18.
 */
@RestController
@RequestMapping(value = "/oa/order")
public class OrderAPI {

    private static Logger log = Logger.getLogger(OrderAPI.class);

    private final OrderService orderService;

    private final UserService userService;

    public OrderAPI(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getList")
    public APIResult getList(@RequestBody Pageable<Order> pageable, @RequestParam("userId") String userId) throws IOException {
        User user = userService.findById(userId);
        Pageable<Order> pageData = orderService.getList(pageable, user);
        return new APIResult(pageData);
    }

}
