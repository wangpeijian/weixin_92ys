package mvc.service;

import mvc.dao.pojo.JobType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by peijian.wang on 2017/3/25.
 */
public interface JobTypeService {

    /**
     * 获取职位列表
     * @return
     */
    List<JobType> getList();

    /**
     * 保存或添加新的职位
     * @return
     */
    int save(JobType jobType);

    /**
     * 删除职位
     * @return
     */
    int delete(JobType jobType);
}
