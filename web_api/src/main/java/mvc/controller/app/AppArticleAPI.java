package mvc.controller.app;

import mvc.controller.oa.ArticleAPI;
import mvc.dao.pojo.Article;
import mvc.service.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.APIResult;
import utils.enumeration.APICode;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/18.
 */
@RestController
@RequestMapping(value = "/app/article")
public class AppArticleAPI {

    private static Logger log = Logger.getLogger(AppArticleAPI.class);

    private ArticleService articleService;

    public AppArticleAPI(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/getCoverList")
    public String getCoverList() throws IOException {
        return new APIResult(articleService.getCoverList()).toString();
    }

    @RequestMapping(value = "/getDetail")
    public String getDetail(@RequestBody Article article) throws IOException {
        return new APIResult(articleService.getDetail(article.getId())).toString();
    }

    @RequestMapping(value = "/getRelationList")
    public String getRelationList(@RequestBody Article article) throws IOException {
        return new APIResult(articleService.getRelationList(article.getId())).toString();
    }

}
