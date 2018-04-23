package utils;

/**
 * Created by wangpeijian on 2017/3/9 0009.
 */

public class WebConfig {

    public final static String APPID = "123";

    /*项目和文件目录*/
    public final static String PATH_WEB_API = System.getProperty("webapp.root");

    public final static String URL_WEB_API = "http://localhost:8081/";

    /**
     * 文章中的图片
     */
    public final static String PATH_ARTICLE_IMAGE = PATH_WEB_API + "views\\image\\upload\\article_image\\";
    public final static String URL_ARTICLE_IMAGE = URL_WEB_API + "views/image/upload/article_image/";

    /**
     * 文章封面
     */
    public final static String PATH_ARTICLE_COVER = PATH_WEB_API + "views\\image\\upload\\article_cover\\";
    public final static String URL_ARTICLE_COVER = URL_WEB_API + "views/image/upload/article_cover/";

    /**
     * 用户头像
     */
    public final static String PATH_USER_HEAD = PATH_WEB_API + "views\\image\\upload\\user_head\\";
    public final static String URL_USER_HEAD = URL_WEB_API + "views/image/upload/user_head/";

    /**
     * 资源类型封面
     */
    public final static String PATH_RESOURCES_TYPE_COVER = PATH_WEB_API + "views\\image\\upload\\resources_type_cover\\";
    public final static String URL_RESOURCES_TYPE_COVER = URL_WEB_API + "views/image/upload/resources_type_cover/";

    /**
     * 产品封面
     */
    public final static String PATH_PRODUCT_COVER = PATH_WEB_API + "views\\image\\upload\\product_cover\\";
    public final static String URL_PRODUCT_COVER = URL_WEB_API + "views/image/upload/product_cover/";

    /**
     * 产品轮播图
     */
    public final static String PATH_PRODUCT_CAROUSEL = PATH_WEB_API + "views\\image\\upload\\product_carousel\\";
    public final static String URL_PRODUCT_CAROUSEL = URL_WEB_API + "views/image/upload/product_carousel/";

}