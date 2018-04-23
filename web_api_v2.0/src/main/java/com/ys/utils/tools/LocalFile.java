package com.ys.utils.tools;


import com.ys.utils.WebConfig;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by Administrator on 2017/3/23 0023.
 * 操作服务器中用户上传的各类文件
 */
@Component
public class LocalFile {

    private final WebConfig webConfig;

    public LocalFile(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public boolean removeLocalFile(String url, int type){

        String path = url.replace(webConfig.getViewImagePath(), webConfig.getUploadImagesPath());

        File rf = new File(path);

        if(rf.exists()){
            if(rf.delete()){
                return true;
            }
        }

        return false;
    }

}
