package mvc.dao.maps;

import mvc.dao.pojo.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/23 0023.
 */
@Repository
public interface RoleMapper {

    @Select("select * from role r where r.code = #{0}")
    Role findByCode(String code);

}
