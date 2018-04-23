package mvc.dao.maps;

import mvc.dao.pojo.Carousel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peijian.wang on 2017/4/4.
 */
@Repository
public interface CarouselMapper {

    @Insert("insert into carousel values ( #{id}, #{url}, #{pid}, #{no} ) ")
    int add(Carousel carousel);

    @Delete("delete c from carousel c where c.id = #{0}")
    int delete(String id);

    @Update("update carousel c set c.no = #{no} where c.id = #{id} ")
    int update(Carousel carousel);

    @Select("select * from carousel c where c.pid = #{0} order by c.no")
    List<Carousel> findByPid(String id);
}
