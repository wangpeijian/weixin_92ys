package com.ys.mvc.controller.oa;

import com.ys.exceptions.APIException;
import com.ys.mvc.dao.pojo.Pageable;
import com.ys.mvc.dao.pojo.User;
import com.ys.mvc.service.UserService;
import com.ys.utils.APIResult;
import com.ys.utils.enumeration.APICode;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/14.
 */
@RestController
@RequestMapping(value = "/oa/user")
public class UserAPI {

    private static Logger log = Logger.getLogger(UserAPI.class);

    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/oaLogin")
    public APIResult oaLogin(@RequestBody User user) throws IOException {
        APIResult res = new APIResult();
        return userService.oaLogin(user, res);
    }

    @RequestMapping(value = "/getList")
    public APIResult getList(@RequestBody Pageable<User> pageable, @RequestParam String type, @RequestParam(required=false) String keyword) throws IOException {
        Pageable<User> pageData = userService.getList(pageable, type, keyword);
        return new APIResult(pageData);
    }

    @RequestMapping(value = "/saveWorker")
    public APIResult saveWorker(@RequestBody User user) throws IOException {
        APIResult res = new APIResult();
        return userService.saveWorker(user, res);
    }

    @RequestMapping(value = "/delete")
    public APIResult delete(@RequestBody User user) throws IOException {
        userService.delete(user);
        return new APIResult();
    }

    @RequestMapping(value = "/getDetail")
    public APIResult getDetail(@RequestBody User user) throws IOException {
        return new APIResult(userService.getDetail(user));
    }

    @RequestMapping(value = "/getWorkerList")
    public APIResult getWorkerList() throws IOException {
        return new APIResult(userService.getWorkerList());
    }
}
