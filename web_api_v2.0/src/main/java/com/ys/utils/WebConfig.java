package com.ys.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wangpeijian on 2017/3/9 0009.
 */
@Component
@ConfigurationProperties(prefix = "webConfig")
public class WebConfig {

    private String appId = "123";

    /*文件上传目录*/
    private String uploadImagesPath;

    /*文件查看目录*/
    private String viewImagePath;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUploadImagesPath() {
        return uploadImagesPath;
    }

    public void setUploadImagesPath(String uploadImagesPath) {
        this.uploadImagesPath = uploadImagesPath;
    }

    public String getViewImagePath() {
        return viewImagePath;
    }

    public void setViewImagePath(String viewImagePath) {
        this.viewImagePath = viewImagePath;
    }

    /**
     * 文章中的图片
     */
    public final static String ARTICLE_IMAGE = "article_image";

    /**
     * 文章封面
     */
    public final static String ARTICLE_COVER = "article_cover";

    /**
     * 用户头像
     */
    public final static String USER_HEAD = "user_head";

    /**
     * 资源类型封面
     */
    public final static String RESOURCES_TYPE_COVER = "resources_type_cover";

    /**
     * 产品封面
     */
    public final static String PRODUCT_COVER = "product_cover";

    /**
     * 产品轮播图
     */
    public final static String PRODUCT_CAROUSEL = "product_carousel";
}