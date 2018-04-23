package com.ys.mvc.controller.app;

import com.ys.mvc.service.JobTypeService;
import com.ys.utils.APIResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/25.
 */
@RestController
@RequestMapping(value = "/app/jobType")
public class AppJobTypeAPI {

    private static Logger log = Logger.getLogger(AppJobTypeAPI.class);

    private final JobTypeService jobTypeService;

    public AppJobTypeAPI(JobTypeService jobTypeService) {
        this.jobTypeService = jobTypeService;
    }

    @RequestMapping(value = "/getList")
    public APIResult getList() throws IOException {
        return new APIResult(jobTypeService.getList());
    }
}
