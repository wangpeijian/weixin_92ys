package mvc.controller.app;

import mvc.controller.oa.OrderAPI;
import mvc.dao.pojo.Order;
import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.User;
import mvc.service.OrderService;
import mvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.APIResult;

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
    public String addOrder(@RequestBody Order order) throws IOException {
        orderService.addOrder(order);
        return new APIResult().toString();
    }

    @RequestMapping(value = "/getUserOrder")
    public String getUserOrder(@RequestBody User user) throws IOException {
        List<Order> orders = orderService.getUserOrder(user);
        return new APIResult(orders).toString();
    }


}
