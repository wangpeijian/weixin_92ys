package com.ys.mvc.dao.mapper;

import com.ys.mvc.dao.pojo.ProductItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peijian.wang on 2017/4/4.
 */
@Repository
public interface ProductItemMapper {

    @Insert("insert into productitem values (#{id}, #{name}, #{pid}, #{price}, #{isItem}, #{no})")
    int add(ProductItem productItems);

    int deleteByPid(List<String> ids);

    @Select("select * from productitem p where p.pid = #{param1} order by p.no")
    List<ProductItem> findItemByPid(String pid);
}
