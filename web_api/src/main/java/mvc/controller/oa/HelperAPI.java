package mvc.controller.oa;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import sun.misc.BASE64Decoder;
import utils.enumeration.APICode;
import utils.APIResult;
import utils.WebConfig;
import utils.tools.LocalFile;

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
    public String postImage(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {

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
                    if (myFileName.trim() != "") {
                        //重命名上传后的文件名
                        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
                        String fileName = df.format(new Date()) + "_" + file.getOriginalFilename();
                        //定义上传路径
                        String path = WebConfig.PATH_ARTICLE_IMAGE + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);

                        //返回录片的网络地址到前台
                        JSONObject jo = new JSONObject();
                        jo.put("link", WebConfig.URL_ARTICLE_IMAGE + fileName);
                        return jo.toJSONString();
                    }
                }
            }
        }

        return new APIResult(APICode.ERROR).toString();
    }

    /**
     * 富文本编辑器删除文章中的图片
     *
     * @param src
     * @return
     */
    @RequestMapping(value = "/removeImage")
    public String removeImage(@RequestBody String src) {
        try {
            src = src.replace("src=", "");
            src = URLDecoder.decode(src, "utf-8");

            if(LocalFile.removeLocalFile(src, 3)){
                return new APIResult(APICode.SUCCESS).toString();
            }else{
                return new APIResult(APICode.ERROR).toString();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new APIResult(APICode.ERROR).toString();
        }
    }

    /**
     * 上传base64文件
     *
     * @param hashMap
     * @return
     */
    @RequestMapping(value = "/postBase64")
    public String removeImage(@RequestBody HashMap<String, String> hashMap) {

        //文件的base64编码数据
        String base64 = hashMap.get("base64");
        //图片文件后缀名
        String fileName = hashMap.get("fileName");
        //图片作用域
        String scoped = hashMap.get("scoped");

        String FILE_PATH = "";
        String FILE_URL = "";

        if (scoped.equals("article_cover")) {
            FILE_PATH = WebConfig.PATH_ARTICLE_COVER;
            FILE_URL = WebConfig.URL_ARTICLE_COVER;
        }else if(scoped.equals("user_head")){
            FILE_PATH = WebConfig.PATH_USER_HEAD;
            FILE_URL = WebConfig.URL_USER_HEAD;
        }else if(scoped.equals("resources_type_cover")){
            FILE_PATH = WebConfig.PATH_RESOURCES_TYPE_COVER;
            FILE_URL = WebConfig.URL_RESOURCES_TYPE_COVER;
        }else if(scoped.equals("product_cover")){
            FILE_PATH = WebConfig.PATH_PRODUCT_COVER;
            FILE_URL = WebConfig.URL_PRODUCT_COVER;
        }else if(scoped.equals("product_carousel")){
            FILE_PATH = WebConfig.PATH_PRODUCT_CAROUSEL;
            FILE_URL = WebConfig.URL_PRODUCT_CAROUSEL;
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
            fileName = df.format(new Date()) + "_" + fileName;
            //定义上传路径
            String path = FILE_PATH + fileName;

            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();

            imgUrl = FILE_URL + fileName;

        } catch (Exception e) {
            return new APIResult(APICode.ERROR).toString();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imgUrl", imgUrl);

        return new APIResult(jsonObject).toString();
    }
}
