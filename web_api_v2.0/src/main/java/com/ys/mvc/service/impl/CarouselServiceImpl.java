package com.ys.mvc.service.impl;

import com.ys.mvc.dao.mapper.CarouselMapper;
import com.ys.mvc.dao.pojo.Carousel;
import com.ys.mvc.dao.pojo.Product;
import com.ys.mvc.service.CarouselService;
import com.ys.utils.tools.Lang;
import com.ys.utils.tools.LocalFile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peijian.wang on 2017/4/4.
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    private final CarouselMapper carouselMapper;

    private final LocalFile localFile;

    public CarouselServiceImpl(CarouselMapper carouselMapper, LocalFile localFile) {
        this.carouselMapper = carouselMapper;
        this.localFile = localFile;
    }

    /**
     * 为产品添加轮播图
     *
     * @param product
     * @return
     */
    public int add(Product product) {

        for(Carousel c : product.getCarousels()){
            c.setId(Lang.getUUID());
            c.setPid(product.getId());
            carouselMapper.add(c);
        }

        return 0;
    }

    /**
     * 删除旧的轮播图
     *
     * @param carousel
     * @return
     */
    public int delete(Carousel carousel) {

        int line = carouselMapper.delete(carousel.getId());
        if( line == 1 ){
            //删除成功将本地图片也删除
            localFile.removeLocalFile(carousel.getUrl(), 6);
        }

        return line;
    }

    /**
     * 删除全部轮播图
     *
     * @param carousels
     * @return
     */
    public int delete(List<Carousel> carousels) {

        if(carousels != null){
            for(Carousel c : carousels){
                this.delete(c);
            }
        }

        return 0;
    }

    /**
     * 先删除旧的轮播图，再加入新的
     *
     * @param oldProduct
     * @param newProduct
     * @return
     */
    public int update(Product oldProduct, Product newProduct) {

        if(oldProduct.getCarousels() == null){
            oldProduct.setCarousels(new ArrayList<Carousel>());
        }

        if(newProduct.getCarousels() == null){
            newProduct.setCarousels(new ArrayList<Carousel>());
        }

        ArrayList<Carousel> updateList = new ArrayList<Carousel>();

        //选出所有旧的不存在于新的图片中的，需要被移除的轮播图
        for(Carousel oldCarousel : oldProduct.getCarousels()){
            boolean exist = false;
            for(Carousel newCarousel : newProduct.getCarousels()){
                if(oldCarousel.getId().equals(newCarousel.getId()) || oldCarousel.getUrl().equals(newCarousel.getUrl())){
                    exist = true;
                }
            }

            if(!exist){
                this.delete(oldCarousel);
            }
        }

        //选出不在旧的图片中的，需要添加的新图片
        ArrayList<Carousel> newCarouselList = new ArrayList<Carousel>();
        for(Carousel newCarousel : newProduct.getCarousels()){
            boolean exist = false;
            for(Carousel oldCarousel : oldProduct.getCarousels()){
                if(oldCarousel.getId().equals(newCarousel.getId()) || oldCarousel.getUrl().equals(newCarousel.getUrl())){
                    exist = true;
                    updateList.add(newCarousel);
                }
            }

            if(!exist){
                newCarouselList.add(newCarousel);
            }
        }

        //将新的图片存入
        Product p = new Product();
        p.setId(oldProduct.getId());
        p.setCarousels(newCarouselList);
        this.add(p);
        this.update(updateList);

        return 0;
    }

    public int update(ArrayList<Carousel> updateList){

        if(updateList != null){
            for(Carousel c : updateList){
                carouselMapper.update(c);
            }
        }

        return 0;
    }

    /**
     * 设置轮播图
     *
     * @param product
     */
    public void setCarousels(Product product) {
        product.setCarousels(carouselMapper.findByPid(product.getId()));
    }
}
