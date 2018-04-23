package utils.tools;

import utils.WebConfig;

import java.io.File;

/**
 * Created by Administrator on 2017/3/23 0023.
 * 操作服务器中用户上传的各类文件
 */
public class LocalFile {

    public static boolean removeLocalFile(String url, int type){

        String path = "";

        switch (type){
            case 0:     //用户头像
                path = url.replace(WebConfig.URL_USER_HEAD, WebConfig.PATH_USER_HEAD);
                break;
            case 1:     //文章封面
                path = url.replace(WebConfig.URL_ARTICLE_COVER, WebConfig.PATH_ARTICLE_COVER);
                break;
            case 3:     //文章内的图片
                path = url.replace(WebConfig.URL_ARTICLE_IMAGE, WebConfig.PATH_ARTICLE_IMAGE);
                break;
            case 4:     //资源分类封面
                path = url.replace(WebConfig.URL_RESOURCES_TYPE_COVER, WebConfig.PATH_RESOURCES_TYPE_COVER);
                break;
            case 5:     //产品封面
                path = url.replace(WebConfig.URL_PRODUCT_COVER, WebConfig.PATH_PRODUCT_COVER);
                break;
            case 6:     //产品轮播图
                path = url.replace(WebConfig.URL_PRODUCT_CAROUSEL, WebConfig.PATH_PRODUCT_CAROUSEL);
                break;
        }

        File rf = new File(path);

        if(rf.exists()){
            if(rf.delete()){
                return true;
            }
        }

        return false;
    }

}
