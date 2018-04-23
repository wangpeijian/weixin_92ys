package mvc.controller.app;

import mvc.dao.pojo.JobType;
import mvc.service.JobTypeService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.APIResult;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/25.
 */
@RestController
@RequestMapping(value = "/app/jobType")
public class AppJobTypeAPI {

    private static Logger log = Logger.getLogger(AppJobTypeAPI.class);

    private JobTypeService jobTypeService;

    public AppJobTypeAPI(JobTypeService jobTypeService) {
        this.jobTypeService = jobTypeService;
    }

    @RequestMapping(value = "/getList")
    public String getList() throws IOException {
        return new APIResult(jobTypeService.getList()).toString();
    }
}
