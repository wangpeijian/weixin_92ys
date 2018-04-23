package mvc.controller.oa;

import mvc.dao.pojo.Order;
import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.User;
import mvc.service.OrderService;
import mvc.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;
import utils.APIResult;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/6/18.
 */
@RestController
@RequestMapping(value = "/oa/orderAPI")
public class OrderAPI {

    private static Logger log = Logger.getLogger(OrderAPI.class);

    private final OrderService orderService;

    private final UserService userService;

    public OrderAPI(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getList")
    public String getList(@RequestBody Pageable<Order> pageable, @RequestParam("userId") String userId) throws IOException {
        User user = userService.findById(userId);
        Pageable<Order> pageData = orderService.getList(pageable, user);
        return new APIResult(pageData).toString();
    }

}
