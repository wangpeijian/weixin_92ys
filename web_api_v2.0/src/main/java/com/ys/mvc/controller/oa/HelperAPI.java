package com.ys.mvc.controller.oa;

import com.alibaba.fastjson.JSONObject;
import com.ys.utils.APIResult;
import com.ys.utils.WebConfig;
import com.ys.utils.enumeration.APICode;
import com.ys.utils.tools.LocalFile;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by peijian.wang on 2017/3/18.
 */
@RestController
@RequestMapping(value = "/oa/helper")
public class HelperAPI {

    private static Logger log = Logger.getLogger(HelperAPI.class);

    private final WebConfig webConfig;

    private final LocalFile localFile;

    public HelperAPI(WebConfig webConfig, LocalFile localFile) {
        this.webConfig = webConfig;
        this.localFile = localFile;
    }

    /**
     * 富文本编辑器上传文章中的图片
     *
     * @param request
     * @param response
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value = "/postImage")
    public Object postImage(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            if (iter.hasNext()) {

                //记录上传过程起始时的时间，用来计算上传时间
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (!"".equals(myFileName.trim())) {
                        //重命名上传后的文件名
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                        String fileName = WebConfig.ARTICLE_IMAGE + "_" + df.format(new Date()) + "_" + file.getOriginalFilename();
                        //定义上传路径
                        String path = webConfig.getUploadImagesPath() + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);

                        //返回录片的网络地址到前台
                        JSONObject jo = new JSONObject();
                        jo.put("link", webConfig.getViewImagePath() + fileName);
                        return jo;
                    }
                }
            }
        }

        return new APIResult(APICode.ERROR);
    }

    /**
     * 富文本编辑器删除文章中的图片
     *
     * @param src
     * @return
     */
    @RequestMapping(value = "/removeImage")
    public APIResult removeImage(@RequestBody String src) {
        try {
            src = src.replace("src=", "");
            src = URLDecoder.decode(src, "utf-8");

            if (localFile.removeLocalFile(src, 3)) {
                return new APIResult(APICode.SUCCESS);
            } else {
                return new APIResult(APICode.ERROR);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new APIResult(APICode.ERROR);
        }
    }

    /**
     * 上传base64文件
     *
     * @param hashMap
     * @return
     */
    @RequestMapping(value = "/postBase64")
    public APIResult removeImage(@RequestBody HashMap<String, String> hashMap) {

        //文件的base64编码数据
        String base64 = hashMap.get("base64");
        //图片文件名
        String fileName = hashMap.get("fileName");
        //图片作用域
        String scoped = hashMap.get("scoped");

        String FILE_PATH = webConfig.getUploadImagesPath();
        String FILE_URL = webConfig.getViewImagePath();
        String FILE_TYPE = "UNDEFINED";

        switch (scoped) {
            case WebConfig.ARTICLE_COVER:
            case WebConfig.USER_HEAD:
            case WebConfig.RESOURCES_TYPE_COVER:
            case WebConfig.PRODUCT_COVER:
            case WebConfig.PRODUCT_CAROUSEL:
                FILE_TYPE = scoped;
                break;
        }

        String imgUrl;

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            String regEx = "data:[\\S]*;base64,";
            base64 = base64.replaceAll(regEx, "");
            // 解密
            byte[] b = decoder.decodeBuffer(base64);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            fileName = FILE_TYPE + "_" + df.format(new Date()) + "_" + fileName;
            //定义上传路径
            String path = FILE_PATH + fileName;

            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();

            imgUrl = FILE_URL + fileName;

        } catch (Exception e) {
            return new APIResult(APICode.ERROR);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imgUrl", imgUrl);
        return new APIResult(jsonObject);
    }
}
