package mvc.controller.oa;

import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.Product;
import mvc.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.APIResult;
import utils.enumeration.APICode;
import utils.enumeration.ProductCode;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/4/2.
 */
@RestController
@RequestMapping(value = "/oa/product")
public class ProductAPI {

    private static Logger log = Logger.getLogger(ProductAPI.class);

    private ProductService productService;

    public ProductAPI(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/getTypeList")
    public String getTypeList() throws IOException {
        return new APIResult(productService.getTypeList()).toString();
    }

    @RequestMapping(value = "/save")
    public String save(@RequestBody Product product) throws IOException {

        productService.save(product);

        return new APIResult().toString();
    }

    @RequestMapping(value = "/getDetail")
    public String getDetail(@RequestBody Product product) throws IOException {
        return new APIResult(productService.getDetail(product)).toString();
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestBody Product product) throws IOException {
        return new APIResult(productService.delete(product)).toString();
    }

    @RequestMapping(value = "/getServiceList")
    public String getServiceList(@RequestBody Pageable<Product> pageable) throws IOException {

        Product p = new Product();
        p.setType(ProductCode.Service.getType());
        pageable.setObject(p);

        Pageable<Product> pageData = productService.getList(pageable);
        return new APIResult(pageData).toString();
    }

    @RequestMapping(value = "/getResourceList")
    public String getResourceList(@RequestBody Pageable<Product> pageable) throws IOException {

        Product p = new Product();
        p.setType(ProductCode.Resource.getType());
        pageable.setObject(p);

        Pageable<Product> pageData = productService.getList(pageable);
        return new APIResult(pageData).toString();
    }

    @RequestMapping(value = "/getPersonalList")
    public String getPersonalList(@RequestBody Pageable<Product> pageable) throws IOException {

        Product p = new Product();
        p.setType(ProductCode.Personal.getType());
        pageable.setObject(p);

        Pageable<Product> pageData = productService.getList(pageable);
        return new APIResult(pageData).toString();
    }
}
