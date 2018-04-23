package mvc.service;

import com.alibaba.fastjson.JSONObject;
import mvc.dao.pojo.JobType;
import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.Product;

import java.util.HashMap;
import java.util.List;

/**
 * Created by peijian.wang on 2017/4/2.
 */
public interface ProductService {

    /**
     * 获取产品类型
     *
     * @return
     */
    HashMap<String, String> getTypeList();

    /**
     * 新增或者修改一个产品
     * @return
     */
    boolean save(Product product);

    /**
     * 获取产品详情
     *
     * @param product
     * @return
     */
    Product getDetail(Product product);

    /**
     * 删除产品
     *
     * @return
     */
    boolean delete(Product product);

    /**
     * 获取企业服务列表
     * @return
     */
    Pageable<Product> getList(Pageable<Product> pageable);

    HashMap getHomeData();

    List<Product> getWorkerDataByType(JobType jobType);

    List<Product> getSourceList(Product product);
}
