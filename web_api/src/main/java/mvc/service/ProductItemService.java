package mvc.service;

import mvc.dao.pojo.Product;
import mvc.dao.pojo.ProductItem;

import java.util.List;

/**
 * Created by peijian.wang on 2017/4/4.
 */
public interface ProductItemService {

    int add(Product product);

    int delete(Product oldProduct);

    int update(Product oldProduct, Product newProduct);

    void setItems(Product product);
}
