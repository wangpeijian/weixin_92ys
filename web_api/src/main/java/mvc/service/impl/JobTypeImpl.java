package mvc.service.impl;

import mvc.dao.maps.JobTypeMapper;
import mvc.dao.pojo.JobType;
import mvc.service.JobTypeService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import utils.tools.Lang;

import java.util.Date;
import java.util.List;

/**
 * Created by peijian.wang on 2017/3/25.
 */
@Service
public class JobTypeImpl implements JobTypeService{

    private static Logger log = Logger.getLogger(JobTypeImpl.class);

    private final JobTypeMapper jobTypeMapper;

    public JobTypeImpl(JobTypeMapper jobTypeMapper) {
        this.jobTypeMapper = jobTypeMapper;
    }

    /**
     * 获取职位列表
     *
     * @return
     */
    public List<JobType> getList() {
        return jobTypeMapper.getList();
    }

    /**
     * 保存或添加新的职位
     *
     * @param jobType
     * @return
     */
    public int save(JobType jobType) {

        JobType oldJobType = jobTypeMapper.findById(jobType);

        if(oldJobType == null){
            jobType.setId(Lang.getUUID());
            jobType.setUpdateTime(new Date());
            return jobTypeMapper.save(jobType);
        }else{
            oldJobType.setName(jobType.getName());
            oldJobType.setCode(jobType.getCode());
            jobType.setUpdateTime(new Date());
            return jobTypeMapper.update(jobType);
        }
    }

    /**
     * 删除职位
     *
     * @param jobType
     * @return
     */
    public int delete(JobType jobType) {
        return jobTypeMapper.delete(jobType);
    }
}
