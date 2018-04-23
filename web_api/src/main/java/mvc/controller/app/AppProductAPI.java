package mvc.controller.app;

import mvc.controller.oa.ProductAPI;
import mvc.dao.pojo.Article;
import mvc.dao.pojo.JobType;
import mvc.dao.pojo.Product;
import mvc.service.ArticleService;
import mvc.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.APIResult;
import utils.enumeration.APICode;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/18.
 */
@RestController
@RequestMapping(value = "/app/product")
public class AppProductAPI {

    private static Logger log = Logger.getLogger(ProductAPI.class);

    private ProductService productService;

    public AppProductAPI(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/getHomeData")
    public String getHomeData() throws IOException {
        return new APIResult(productService.getHomeData()).toString();
    }

    @RequestMapping(value = "/getWorkerDataByType")
    public String getWorkerDataByType(@RequestBody JobType jobType) throws IOException {
        return new APIResult(productService.getWorkerDataByType(jobType)).toString();
    }

    @RequestMapping(value = "/getSourceList")
    public String getSourceList(@RequestBody Product product) throws IOException {
        return new APIResult(productService.getSourceList(product)).toString();
    }

    @RequestMapping(value = "/getDetail")
    public String getDetail(@RequestBody Product product) throws IOException {
        return new APIResult(productService.getDetail(product)).toString();
    }
}
