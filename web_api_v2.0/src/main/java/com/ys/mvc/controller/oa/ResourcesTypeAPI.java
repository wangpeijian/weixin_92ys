package com.ys.mvc.controller.oa;

import com.ys.mvc.dao.pojo.ResourcesType;
import com.ys.mvc.service.ResourcesService;
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
@RequestMapping(value = "/oa/resourcesType")
public class ResourcesTypeAPI {

    private static Logger log = Logger.getLogger(ResourcesTypeAPI.class);

    private final ResourcesService resourcesService;

    public ResourcesTypeAPI(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @RequestMapping(value = "/getList")
    public APIResult getList() throws IOException {
        return new APIResult(resourcesService.getList());
    }

    @RequestMapping(value = "/save")
    public APIResult save(@RequestBody ResourcesType resourcesType) throws IOException {
        return new APIResult(resourcesService.save(resourcesType));
    }

    @RequestMapping(value = "/delete")
    public APIResult delete(@RequestBody ResourcesType resourcesType) throws IOException {
        resourcesService.delete(resourcesType);
        return new APIResult();
    }

}
