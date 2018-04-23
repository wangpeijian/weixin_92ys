package mvc.controller.app;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangpeijian on 2017/3/9 0009.
 */
@RestController
@RequestMapping(value = "/wxApi")
public class WeChatAPI {

    private static Logger log = Logger.getLogger(WeChatAPI.class);

    /**
     * 微信授权回调地址
     * @param code
     * @return
     */
    @RequestMapping(value = "/authorize")
    public void authorize(HttpServletResponse response, @RequestParam(name = "code") String code) throws IOException {

        //重定向到指定的地址
        response.sendRedirect("");
    }

}
