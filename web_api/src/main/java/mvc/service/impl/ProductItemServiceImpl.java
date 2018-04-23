package mvc.service.impl;

import mvc.dao.maps.ProductItemMapper;
import mvc.dao.pojo.Product;
import mvc.dao.pojo.ProductItem;
import mvc.service.ProductItemService;
import org.springframework.stereotype.Service;
import utils.tools.Lang;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peijian.wang on 2017/4/4.
 */
@Service
public class ProductItemServiceImpl implements ProductItemService {

    private final ProductItemMapper productItemMapper;

    public ProductItemServiceImpl(ProductItemMapper productItemMapper) {
        this.productItemMapper = productItemMapper;
    }

    public int add(Product product) {

        int pNo = 0;
        //先增加父节点
        for(ProductItem p : product.getProductItems()){
            p.setPid(product.getId());
            p.setIsItem(0);
            p.setId(Lang.getUUID());
            p.setNo(pNo++);
            productItemMapper.add(p);

            int sNo = 0;
            for(ProductItem s : p.getProductItems()){
                s.setPid(p.getId());
                s.setIsItem(1);
                s.setId(Lang.getUUID());
                s.setNo(sNo++);
                productItemMapper.add(s);
            }
        }

        return 1;
    }

    public int delete(Product oldProduct){

        List<String> ids = new ArrayList<String>();
        ids.add(oldProduct.getId());

        if(oldProduct.getProductItems() != null){
            for(ProductItem p : oldProduct.getProductItems()){
                ids.add(p.getId());
            }
        }

        productItemMapper.deleteByPid(ids);
        return 1;
    }

    public int update(Product oldProduct, Product newProduct){
        this.delete(oldProduct);
        this.add(newProduct);
        return 1;
    }

    public void setItems(Product product) {
        if(product.getProductItems() != null){
            for(ProductItem p : product.getProductItems()){
                p.setProductItems(productItemMapper.findItemByPid(p.getId()));
            }
        }
    }
}
