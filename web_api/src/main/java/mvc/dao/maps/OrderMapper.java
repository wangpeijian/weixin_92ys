package mvc.dao.maps;

import mvc.dao.pojo.JobType;
import mvc.dao.pojo.Order;
import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.Product;
import org.apache.ibatis.annotations.Delete;
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
            " #{authorId}, #{discount}, #{remark}, #{title}, #{type}, #{cover} ) ")
    int add(Order order);

    Order findById(String id);

    List<Order> getList(Pageable<Order> pageable);

    @Select("select count(*) from `order` o")
    int count();


    List<Order> getUserOrder(String id);
}
