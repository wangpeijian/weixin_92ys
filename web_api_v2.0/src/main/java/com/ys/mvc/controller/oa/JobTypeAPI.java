package com.ys.mvc.controller.oa;

import com.ys.mvc.dao.pojo.JobType;
import com.ys.mvc.service.JobTypeService;
import com.ys.utils.APIResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/25.
 */
@RestController
@RequestMapping(value = "/oa/jobType")
public class JobTypeAPI {

    private static Logger log = Logger.getLogger(JobTypeAPI.class);

    private final JobTypeService jobTypeService;

    public JobTypeAPI(JobTypeService jobTypeService) {
        this.jobTypeService = jobTypeService;
    }

    @RequestMapping(value = "/getList")
    public APIResult getList() throws IOException {
        return new APIResult(jobTypeService.getList());
    }

    @RequestMapping(value = "/save")
    public APIResult save(@RequestBody JobType jobType) throws IOException {
        jobTypeService.save(jobType);
        return new APIResult();
    }

    @RequestMapping(value = "/delete")
    public APIResult delete(@RequestBody JobType jobType) throws IOException {
        jobTypeService.delete(jobType);
        return new APIResult();
    }
}
