package com.ys.mvc.dao.mapper;

import com.ys.mvc.dao.pojo.ResourcesType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by peijian.wang on 2017/3/25.
 */
public interface ResourcesTypeMapper {

    @Select("select * from resourcestype ORDER BY updateTime")
    List<ResourcesType> getList();

    @Insert("insert into resourcestype values (#{id}, #{name}, #{cover}, #{updateTime})")
    int save(ResourcesType resourcesType);

    @Update("update resourcestype r set name = #{name}, cover = #{cover}, updateTime = #{updateTime} where id = #{id}")
    int update(ResourcesType resourcesType);

    @Delete("delete r from resourcestype r where r.id = #{id}")
    int delete(ResourcesType resourcesType);

    @Select("select * from resourcestype r where r.id = #{id}")
    ResourcesType findById(ResourcesType resourcesType);

}
