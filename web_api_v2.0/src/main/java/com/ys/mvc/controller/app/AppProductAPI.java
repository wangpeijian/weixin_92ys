package com.ys.mvc.controller.app;

import com.ys.mvc.controller.oa.ProductAPI;
import com.ys.mvc.dao.pojo.JobType;
import com.ys.mvc.dao.pojo.Product;
import com.ys.mvc.service.ProductService;
import com.ys.utils.APIResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/18.
 */
@RestController
@RequestMapping(value = "/app/product")
public class AppProductAPI {

    private static Logger log = Logger.getLogger(ProductAPI.class);

    private final ProductService productService;

    public AppProductAPI(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/getHomeData")
    public APIResult getHomeData() throws IOException {
        return new APIResult(productService.getHomeData());
    }

    @RequestMapping(value = "/getWorkerDataByType")
    public APIResult getWorkerDataByType(@RequestBody JobType jobType) throws IOException {
        return new APIResult(productService.getWorkerDataByType(jobType));
    }

    @RequestMapping(value = "/getSourceList")
    public APIResult getSourceList(@RequestBody Product product) throws IOException {
        return new APIResult(productService.getSourceList(product));
    }

    @RequestMapping(value = "/getDetail")
    public APIResult getDetail(@RequestBody Product product) throws IOException {
        return new APIResult(productService.getDetail(product));
    }
}
