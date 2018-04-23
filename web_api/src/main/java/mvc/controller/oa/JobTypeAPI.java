package mvc.controller.oa;

import mvc.dao.pojo.JobType;
import mvc.service.JobTypeService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.APIResult;
import utils.enumeration.APICode;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/25.
 */
@RestController
@RequestMapping(value = "/oa/jobType")
public class JobTypeAPI {

    private static Logger log = Logger.getLogger(JobTypeAPI.class);

    private JobTypeService jobTypeService;

    public JobTypeAPI(JobTypeService jobTypeService) {
        this.jobTypeService = jobTypeService;
    }

    @RequestMapping(value = "/getList")
    public String getList() throws IOException {
        return new APIResult(jobTypeService.getList()).toString();
    }

    @RequestMapping(value = "/save")
    public String save(@RequestBody JobType jobType) throws IOException {
        jobTypeService.save(jobType);
        return new APIResult().toString();
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestBody JobType jobType) throws IOException {
        jobTypeService.delete(jobType);
        return new APIResult().toString();
    }
}
