package com.ys.mvc.controller.app;

import com.ys.mvc.dao.pojo.Article;
import com.ys.mvc.service.ArticleService;
import com.ys.utils.APIResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/18.
 */
@RestController
@RequestMapping(value = "/app/article")
public class AppArticleAPI {

    private static Logger log = Logger.getLogger(AppArticleAPI.class);

    private final ArticleService articleService;

    public AppArticleAPI(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping(value = "/getCoverList")
    public APIResult getCoverList() throws IOException {
        return new APIResult(articleService.getCoverList());
    }

    @RequestMapping(value = "/getDetail")
    public APIResult getDetail(@RequestBody Article article) throws IOException {
        return new APIResult(articleService.getDetail(article.getId()));
    }

    @RequestMapping(value = "/getRelationList")
    public APIResult getRelationList(@RequestBody Article article) throws IOException {
        return new APIResult(articleService.getRelationList(article.getId()));
    }

}
