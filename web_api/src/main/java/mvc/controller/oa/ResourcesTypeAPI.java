package mvc.controller.oa;

import mvc.dao.pojo.ResourcesType;
import mvc.service.ResourcesService;
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
@RequestMapping(value = "/oa/resourcesType")
public class ResourcesTypeAPI {

    private static Logger log = Logger.getLogger(ResourcesTypeAPI.class);

    private ResourcesService resourcesService;

    public ResourcesTypeAPI(ResourcesService resourcesService) {
        this.resourcesService = resourcesService;
    }

    @RequestMapping(value = "/getList")
    public String getList() throws IOException {
        return new APIResult(resourcesService.getList()).toString();
    }

    @RequestMapping(value = "/save")
    public String save(@RequestBody ResourcesType resourcesType) throws IOException {
        return new APIResult(resourcesService.save(resourcesType)).toString();
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestBody ResourcesType resourcesType) throws IOException {
        resourcesService.delete(resourcesType);
        return new APIResult().toString();
    }

}
