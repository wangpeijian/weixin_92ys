package mvc.dao.maps;

import mvc.dao.pojo.ProductItemSnapshot;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peijian.wang on 2017/4/4.
 */
@Repository
public interface ProductItemSnapshotMapper {

    @Insert("insert into productitem_snapshot values (#{id}, #{name}, #{pid}, #{price}, #{isItem}, #{no}, #{num})")
    int add(ProductItemSnapshot ProductItemSnapshot);

    @Select("select * from productitem_snapshot o where o.pid = #{0} order by o.no")
    List<ProductItemSnapshot> findItemByPid(String pid);
}
