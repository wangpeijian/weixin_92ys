package mvc.service.impl;

import mvc.dao.maps.ProductMapper;
import mvc.dao.maps.ResourcesTypeMapper;
import mvc.dao.pojo.JobType;
import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.Product;
import mvc.service.CarouselService;
import mvc.service.ProductItemService;
import mvc.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import utils.enumeration.ProductCode;
import utils.tools.Lang;
import utils.tools.LocalFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by peijian.wang on 2017/4/2.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static Logger log = Logger.getLogger(ProductServiceImpl.class);

    private ProductMapper productMapper;

    private ProductItemService productItemService;

    private CarouselService carouselService;

    private ResourcesTypeMapper resourcesTypeMapper;

    public ProductServiceImpl(ProductMapper productMapper, ProductItemService productItemService, CarouselService carouselService, ResourcesTypeMapper resourcesTypeMapper) {
        this.productMapper = productMapper;
        this.productItemService = productItemService;
        this.carouselService = carouselService;
        this.resourcesTypeMapper = resourcesTypeMapper;
    }

    /**
     * 获取产品类型
     *
     * @return
     */
    public HashMap<String, String> getTypeList() {

        HashMap<String, String> type = new HashMap<String, String>();

        type.put(ProductCode.Service.getType(), ProductCode.Service.getName());
        type.put(ProductCode.Resource.getType(), ProductCode.Resource.getName());
        type.put(ProductCode.Personal.getType(), ProductCode.Personal.getName());

        return type;
    }

    /**
     * 新增或者修改一个产品
     *
     * @return
     */
    public boolean save(Product product) {

        Product oldProduct = this.getDetail(product); // productMapper.findById(product.getId());

        if(oldProduct == null){
            //新增产品
            product.setId(Lang.getUUID());
            product.setUpdateTime(new Date());

            //增加服务项目
            productItemService.add(product);
            carouselService.add(product);

            productMapper.add(product);
        }else{

            productItemService.update(oldProduct, product);
            carouselService.update(oldProduct, product);

            String oldCover = oldProduct.getCover();

            oldProduct.setType(product.getType());
            oldProduct.setTitle(product.getTitle());
            oldProduct.setAuthorId(product.getAuthorId());
            oldProduct.setCover(product.getCover());
            oldProduct.setContent(product.getContent());
            oldProduct.setResourcesTypeId(product.getResourcesTypeId());
            oldProduct.setArticleId(product.getArticleId());
            oldProduct.setDiscount(product.getDiscount());
            oldProduct.setUpdateTime(new Date());

            productMapper.update(oldProduct);

            //更新成功,如果是选择了新的封面，则删除旧的封面文件
            if(!oldCover.equals(product.getCover())){
                LocalFile.removeLocalFile(oldCover, 5);
            }
        }

        return true;
    }

    /**
     * 获取产品详情
     *
     * @param product
     * @return
     */
    public Product getDetail(Product product) {

        Product p = productMapper.findById(product.getId());
        if(p != null){
            productItemService.setItems(p);
            carouselService.setCarousels(p);
        }

        return p;
    }

    /**
     * 删除产品
     *
     * @param product
     * @return
     */
    public boolean delete(Product product) {

        Product remove = productMapper.findById(product.getId());
        productMapper.delete(remove);
        productItemService.delete(remove);

        carouselService.setCarousels(remove);
        carouselService.delete(remove.getCarousels());

        return true;
    }

    /**
     * 获取企业服务列表
     *
     * @param pageable
     * @return
     */
    public Pageable<Product> getList(Pageable<Product> pageable) {

        pageable.countStart();
        pageable.setList(productMapper.getList(pageable));
        pageable.setCount(productMapper.count(pageable.getObject().getType()));

        return pageable;
    }

    public HashMap getHomeData() {
        HashMap<String, List> hashMap = new HashMap<String, List>();

        hashMap.put("service", productMapper.getServiceInterList());
        hashMap.put("resource", resourcesTypeMapper.getList());

        return hashMap;
    }

    public List<Product> getWorkerDataByType(JobType jobType) {
        return productMapper.getWorkerDataByType(jobType);
    }

    public List<Product> getSourceList(Product product) {
        return productMapper.getSourceList(product);
    }
}
