package mvc.service;

import mvc.dao.pojo.User;
import mvc.dao.pojo.Pageable;
import utils.APIResult;

import java.util.List;

/**
 * Created by dx on 17-1-19.
 */
public interface UserService {

    APIResult oaLogin(User user, APIResult res);

    Pageable<User> getList(Pageable<User> pageable, String type, String keyword);

    User findById(String id);

    APIResult saveWorker(User user, APIResult res);

    int delete(User user);

    /**
     * 获取用户详情
     *
     * @param user
     * @return
     */
    User getDetail(User user);

    /**
     * 获取所有的工人列表
     *
     * @return
     */
    List<User> getWorkerList();
}
