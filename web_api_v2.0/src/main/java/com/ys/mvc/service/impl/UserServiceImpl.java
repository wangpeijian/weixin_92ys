package com.ys.mvc.service.impl;

import com.ys.mvc.dao.mapper.RoleMapper;
import com.ys.mvc.dao.mapper.UserMapper;
import com.ys.mvc.dao.pojo.Pageable;
import com.ys.mvc.dao.pojo.Role;
import com.ys.mvc.dao.pojo.User;
import com.ys.mvc.service.UserService;
import com.ys.utils.APIResult;
import com.ys.utils.enumeration.APICode;
import com.ys.utils.enumeration.RoleCode;
import com.ys.utils.tools.Checker;
import com.ys.utils.tools.Lang;
import com.ys.utils.tools.LocalFile;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by dx on 17-1-19.
 */
@Service
//@Transactional
public class UserServiceImpl implements UserService {

    private static Logger log = Logger.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final LocalFile localFile;

    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper, LocalFile localFile) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.localFile = localFile;
    }

    public APIResult oaLogin(User user, APIResult res) {

        User existUser = userMapper.getUserByUsername(user);
        //判断用户是否存在
        if (existUser == null) {
            return res.setCodeAndMsg(APICode.USER_NOT_EXIST);
        }

        //判断密码是否正确
        if (!existUser.getPassword().equals(user.getPassword())) {
            return res.setCodeAndMsg(APICode.USER_PASSWORD_ERROR);
        }

        //判断用户权限是否可以登录后台
        Role role = existUser.getRole();
        if (role != null && !role.getCode().equals(RoleCode.ADMIN)) {
            return res.setCodeAndMsg(APICode.USER_AUTHORITY_ERROR);
        }

        existUser.setPassword("");
        res.setData(existUser);
        return res.setCodeAndMsg(APICode.SUCCESS);
    }

    public Pageable<User> getList(Pageable pageable, String type, String keyword) {

        pageable.countStart();

        //判断列表类型
        if(type.equals(RoleCode.WORKER)){
            Role role = new Role();
            role.setCode(RoleCode.WORKER);
            pageable.setObject(role);

            pageable.setList(userMapper.getList(pageable));
            pageable.setCount(userMapper.countUser(pageable));
            return pageable;

        }else if(type.equals(RoleCode.USER)){
            Role role = new Role();
            role.setCode(RoleCode.USER);
            pageable.setObject(role);

            if (Checker.isNull(keyword)) {
                pageable.setList(userMapper.getList(pageable));
                pageable.setCount(userMapper.countUser(pageable));
                return pageable;
            }else{
                pageable.setList(userMapper.getUserListByKeyword(pageable, keyword));
                pageable.setCount(userMapper.countUserByKeyword(pageable, keyword));
                return pageable;
            }
        }

        return null;
    }

    public User findById(String id) {
        return userMapper.findById(id);
    }

    public APIResult saveWorker(User user, APIResult res) {

        //判断是否是添加用户
        if(Checker.isNull(user.getId())){
            //新增用户
            if(userMapper.getUserByUsername(user) != null){
                //账号重复
                return res.setCodeAndMsg(APICode.USER_ALREADY_EXIST);
            }else{
                user.setId(Lang.getUUID());
                Role role = roleMapper.findByCode(RoleCode.WORKER);
                user.setRoleId(role.getId());
                user.setRegisterTime(new Date());
                userMapper.save(user);
            }

        }else{
            //编辑用户
            User oldUser = userMapper.findById(user.getId());
            String oldHead = oldUser.getHead();
            oldUser.setName(user.getName());
            oldUser.setPassword(user.getPassword());
            oldUser.setHead(user.getHead());
            oldUser.setSex(user.getSex());
            oldUser.setPhone(user.getPhone());
            oldUser.setStyle(user.getStyle());
            oldUser.setJobTypeId(user.getJobTypeId());
            oldUser.setRemark(user.getRemark());
            userMapper.update(oldUser);
            //更新成功,如果是选择了新的头像，则删除旧头像文件
            if(!oldHead.equals(user.getHead())){
                localFile.removeLocalFile(oldHead, 0);
            }
        }

        return res.setCodeAndMsg(APICode.SUCCESS);
    }

    public int delete(User user) {
        User oldUser = userMapper.findById(user.getId());
        int line = userMapper.delete(user);
        if(line == 1){
            localFile.removeLocalFile(oldUser.getHead(), 0);
        }
        return line;
    }

    /**
     * 获取用户详情
     *
     * @param user
     * @return
     */
    public User getDetail(User user) {
        return userMapper.findById(user.getId());
    }

    /**
     * 获取所有的工人列表
     *
     * @return
     */
    public List<User> getWorkerList() {
        return userMapper.getWorkerList();
    }
}
