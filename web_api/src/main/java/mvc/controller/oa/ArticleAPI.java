package mvc.controller.oa;

import mvc.dao.pojo.Article;
import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.User;
import mvc.service.ArticleService;
import mvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.enumeration.APICode;
import utils.APIResult;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/18.
 */
@RestController
@RequestMapping(value = "/oa/article")
public class ArticleAPI {

    private static Logger log = Logger.getLogger(ArticleAPI.class);

    private ArticleService articleService;

    private UserService userService;

    public ArticleAPI(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getList")
    public String getList(@RequestBody Pageable<Article> pageable, @RequestParam("userId") String userId) throws IOException {

        User user = userService.findById(userId);

        Pageable<Article> pageData = articleService.getList(pageable, user);

        return new APIResult(pageData).toString();
    }

    @RequestMapping(value = "/delete")
    public String delete(@RequestBody Article article) throws IOException {
        articleService.delete(article);
        return new APIResult().toString();
    }

    @RequestMapping(value = "/save")
    public String save(@RequestBody Article article) throws IOException {
        articleService.save(article);
        return new APIResult().toString();
    }

    @RequestMapping(value = "/getDetail")
    public String getDetail(@RequestBody Article article) throws IOException {
        return new APIResult(articleService.getDetail(article)).toString();
    }

    @RequestMapping(value = "/publish")
    public String publish(@RequestBody Article article) throws IOException {
        articleService.setStatus(article, 1);
        return new APIResult().toString();
    }

    @RequestMapping(value = "/unpublished")
    public String unpublished(@RequestBody Article article) throws IOException {
        articleService.setStatus(article, 0);
        return new APIResult().toString();
    }

    @RequestMapping(value = "/getAllList")
    public String getAllList() throws IOException {
        return new APIResult(articleService.getAllList()).toString();
    }
}
