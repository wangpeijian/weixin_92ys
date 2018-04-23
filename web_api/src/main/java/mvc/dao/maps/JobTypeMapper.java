package mvc.dao.maps;

import mvc.dao.pojo.JobType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by peijian.wang on 2017/3/25.
 */
@Repository
public interface JobTypeMapper {

    @Select("select * from jobtype ORDER BY updateTime")
    List<JobType> getList();

    @Insert("insert into jobtype values (#{id}, #{name}, #{code}, #{updateTime})")
    int save(JobType jobType);

    @Update("update jobtype j set name = #{name}, code = #{code}, updateTime = #{updateTime} where id = #{id}")
    int update(JobType jobType);

    @Delete("delete j from jobtype j where j.id = #{id}")
    int delete(JobType jobType);

    @Select("select * from jobtype j where j.id = #{id}")
    JobType findById(JobType jobType);
}
