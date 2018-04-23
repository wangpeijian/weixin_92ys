package mvc.service;

import mvc.dao.pojo.Carousel;
import mvc.dao.pojo.Product;

import java.util.List;

/**
 * Created by peijian.wang on 2017/4/4.
 */
public interface CarouselService {

    /**
     * 为产品添加轮播图
     *
     * @param product
     * @return
     */
    int add(Product product);

    /**
     * 删除旧的轮播图
     *
     * @param carousel
     * @return
     */
    int delete(Carousel carousel);

    /**
     * 删除全部轮播图
     *
     * @param carousels
     * @return
     */
    int delete(List<Carousel> carousels);

    /**
     * 先删除旧的轮播图，再加入新的
     *
     * @param oldProduct
     * @param newProduct
     * @return
     */
    int update(Product oldProduct, Product newProduct);

    /**
     * 设置轮播图
     *
     * @param product
     */
    void setCarousels(Product product);

}
