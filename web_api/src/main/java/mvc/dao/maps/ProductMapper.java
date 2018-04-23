package mvc.dao.maps;

import mvc.dao.pojo.JobType;
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
public interface ProductMapper {

    Product findById(String id);

    @Insert("insert into product values (#{id}, #{type}, #{title}, #{authorId}, #{cover}, #{content}," +
            " #{resourcesTypeId}, #{articleId}, #{discount}, #{updateTime} ) ")
    int add(Product product);

    @Update("update product set type = #{type}, title = #{title}, authorId = #{authorId}, cover = #{cover}," +
            " content = #{content}, resourcesTypeId = #{resourcesTypeId}, articleId = #{articleId}," +
            " discount = #{discount}, updateTime = #{updateTime} where id = #{id}")
    int update(Product product);

    List<Product> getList(Pageable<Product> pageable);

    @Select("select count(*) from product p where p.type = #{0}")
    int count(String type);

    @Delete("delete p from product p where p.id = #{id}")
    int delete(Product product);

    @Select("select p.id, p.cover, p.type, p.title from product p where p.articleId = #{0} order by p.updateTime desc")
    List<Product> getRelationList(String id);

    @Select("select p.id, p.cover, p.title from product p where p.type = 'service' order by p.updateTime desc")
    List<Product> getServiceInterList();

    List<Product> getWorkerDataByType(JobType jobType);

    @Select("select p.id, p.cover, p.title from product p where p.type = #{type} and p.resourcesTypeId = #{resourcesTypeId} order by p.updateTime desc")
    List<Product> getSourceList(Product product);
}
