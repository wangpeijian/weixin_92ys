package com.ys.mvc.dao.mapper;

import com.ys.mvc.dao.pojo.Article;
import com.ys.mvc.dao.pojo.Pageable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peijian.wang on 2017/3/18.
 */
@Repository
public interface ArticleMapper {

    @Select("select count(*) from article")
    int countArticle();

    List<Article> getList(Pageable<Article> pageable);

    @Select("select count(*) from article a where a.userId = #{userId}")
    int countSelfArticle(Article article);

    List<Article> getSelfList(Pageable<Article> pageable);

    @Delete("delete a from article a where a.id = #{id}")
    int delete(Article article);

    @Select("select * from article a where a.id = #{id}")
    Article findById(Article article);

    @Insert("insert into article values (#{id}, #{title}, #{publishTime}, #{userId}, #{content}, #{status}, #{cover}, #{updateTime}, #{shareDescribe} ) ")
    int add(Article article);

    @Update("update article set title = #{title}, content = #{content}, cover = #{cover}, updateTime = #{updateTime}, userId = #{userId}, shareDescribe = #{shareDescribe} where id = #{id}")
    int update(Article article);

    @Update("update article set status = #{status} where id = #{id}")
    int setStatus(Article article);

    @Select("select a.title, a.id from article a")
    List<Article> getAllList();

    @Select("select a.title, a.id, a.cover from article a where a.status = 1")
    List<Article> getCoverList();

    Article getDetail(String id);
}
