package com.ys.mvc.dao.mapper;

import com.ys.mvc.dao.pojo.Order;
import com.ys.mvc.dao.pojo.Pageable;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peijian.wang on 2017/4/2.
 */
@Repository
public interface OrderMapper {

    @Insert("insert into `order` values (#{id}, #{productId}, #{price}, #{date}, #{customerId}, #{status}," +
            " #{authorId}, #{discount}, #{remark}, #{title}, #{type}, #{cover}, #{haveAttachment}, #{attachmentURL}" +
            ", #{attachmentPassword}, #{director}, #{directorPhone} ) ")
    int add(Order order);

    Order findById(String id, String customerId);

    List<Order> getList(Pageable<Order> pageable);

    @Select("select count(*) from `order` o")
    int count();


    List<Order> getUserOrder(String id);

    @Update("update `order` o set o.status = 1 where o.id = #{param1} and o.customerId = #{param2}")
    void closeOrder(String orderId, String customerId);
}
