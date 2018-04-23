package mvc.controller.oa;

import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.User;
import mvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.enumeration.APICode;
import utils.APIResult;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/14.
 */
@RestController
@RequestMapping(value = "/oa/user")
public class UserAPI {

    private static Logger log = Logger.getLogger(UserAPI.class);

    private UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/oaLogin")
    public String oaLogin(@RequestBody User user) throws IOException {
        APIResult res = new APIResult();
        return userService.oaLogin(user, res).toString();
    }

    @RequestMapping(value = "/getList")
    public String getList(@RequestBody Pageable<User> pageable, @RequestParam String type, @RequestParam(required=false) String keyword) throws IOException {
        Pageable<User> pageData = userService.getList(pageable, type, keyword);
        return new APIResult(pageData).toString();
    }

    @RequestMapping(value = "/saveWorker")
    public String saveWorker(@RequestBody User user) throws IOException {
        APIResult res = new APIResult();
        return userService.saveWorker(user, res).toString();
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestBody User user) throws IOException {
        userService.delete(user);
        return new APIResult().toString();
    }

    @RequestMapping(value = "/getDetail")
    public String getDetail(@RequestBody User user) throws IOException {
        return new APIResult(userService.getDetail(user)).toString();
    }

    @RequestMapping(value = "/getWorkerList")
    public String getWorkerList() throws IOException {
        return new APIResult(userService.getWorkerList()).toString();
    }
}
