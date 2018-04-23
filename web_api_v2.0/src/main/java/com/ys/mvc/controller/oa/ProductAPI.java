package com.ys.mvc.controller.oa;

import com.ys.mvc.dao.pojo.Pageable;
import com.ys.mvc.dao.pojo.Product;
import com.ys.mvc.service.ProductService;
import com.ys.utils.APIResult;
import com.ys.utils.enumeration.ProductCode;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/4/2.
 */
@RestController
@RequestMapping(value = "/oa/product")
public class ProductAPI {

    private static Logger log = Logger.getLogger(ProductAPI.class);

    private final ProductService productService;

    public ProductAPI(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/getTypeList")
    public APIResult getTypeList() throws IOException {
        return new APIResult(productService.getTypeList());
    }

    @RequestMapping(value = "/save")
    public APIResult save(@RequestBody Product product) throws IOException {

        productService.save(product);

        return new APIResult();
    }

    @RequestMapping(value = "/getDetail")
    public APIResult getDetail(@RequestBody Product product) throws IOException {
        return new APIResult(productService.getDetail(product));
    }

    @RequestMapping(value = "/delete")
    public APIResult delete(@RequestBody Product product) throws IOException {
        return new APIResult(productService.delete(product));
    }

    @RequestMapping(value = "/getServiceList")
    public APIResult getServiceList(@RequestBody Pageable<Product> pageable) throws IOException {

        Product p = new Product();
        p.setType(ProductCode.Service.getType());
        pageable.setObject(p);

        Pageable<Product> pageData = productService.getList(pageable);
        return new APIResult(pageData);
    }

    @RequestMapping(value = "/getResourceList")
    public APIResult getResourceList(@RequestBody Pageable<Product> pageable) throws IOException {

        Product p = new Product();
        p.setType(ProductCode.Resource.getType());
        pageable.setObject(p);

        Pageable<Product> pageData = productService.getList(pageable);
        return new APIResult(pageData);
    }

    @RequestMapping(value = "/getPersonalList")
    public APIResult getPersonalList(@RequestBody Pageable<Product> pageable) throws IOException {

        Product p = new Product();
        p.setType(ProductCode.Personal.getType());
        pageable.setObject(p);

        Pageable<Product> pageData = productService.getList(pageable);
        return new APIResult(pageData);
    }
}
