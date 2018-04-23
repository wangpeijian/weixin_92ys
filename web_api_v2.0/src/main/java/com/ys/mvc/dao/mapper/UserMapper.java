package com.ys.mvc.dao.mapper;

import com.ys.mvc.dao.pojo.Pageable;
import com.ys.mvc.dao.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dx on 17-1-19.
 */
@Repository
public interface UserMapper {

    User getUserByUsername(User user);

    List<User> getList(Pageable pageable);

    @Select("select count(*) from user u, role r where r.code = #{object.code} and r.id = u.roleId")
    int countUser(Pageable pageable);

    @Select("select * from user u, role r " +
            " where (u.name like CONCAT('%',#{param2},'%') or u.phone like CONCAT('%',#{param2},'%'))  " +
            " and r.code = #{param1.object.code} " +
            " and r.id = u.roleId " +
            " order by u.registerTime DESC limit #{param1.start}, #{param1.paging} ")
    List<User> getUserListByKeyword(Pageable pageable, String keyword);

    @Select("select count(*) from user u, role r " +
            " where (u.name like CONCAT('%',#{param2},'%') or u.phone like CONCAT('%',#{param2},'%')) " +
            " and r.code = #{param1.object.code} " +
            " and r.id = u.roleId ")
    int countUserByKeyword(Pageable pageable, String keyword);

    User findById(String id);

    @Insert("insert into user values (#{id}, #{name}, #{username}, #{password}, #{head}, #{lastTime}," +
            " #{registerTime}, #{roleId}, #{sex}, #{phone}, #{province}, #{city}, #{style}, #{jobTypeId}, #{remark}) ")
    int save(User user);

    @Update("update user set name = #{name}, username = #{username}, password = #{password}, head = #{head}" +
            ", lastTime = #{lastTime}, registerTime = #{registerTime}, roleId = #{roleId}, sex = #{sex}" +
            ", phone = #{phone}, province = #{province}, city = #{city}, style = #{style}, jobTypeId = #{jobTypeId}" +
            ", remark = #{remark} where id = #{id} ")
    int update(User user);

    @Delete("delete u from user u where u.id = #{id}")
    int delete(User user);

    @Select("select * from user u, role r where r.code = 'worker' and r.id = u.roleId")
    List<User> getWorkerList();
}
