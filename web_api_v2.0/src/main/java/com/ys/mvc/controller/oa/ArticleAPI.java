package com.ys.mvc.controller.oa;

import com.ys.mvc.dao.pojo.Article;
import com.ys.mvc.dao.pojo.Pageable;
import com.ys.mvc.dao.pojo.User;
import com.ys.mvc.service.ArticleService;
import com.ys.mvc.service.UserService;
import com.ys.utils.APIResult;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by peijian.wang on 2017/3/18.
 */
@RestController
@RequestMapping(value = "/oa/article")
public class ArticleAPI {

    private static Logger log = Logger.getLogger(ArticleAPI.class);

    private final ArticleService articleService;

    private final UserService userService;

    public ArticleAPI(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getList")
    public APIResult getList(@RequestBody Pageable<Article> pageable, @RequestParam("userId") String userId) throws IOException {

        User user = userService.findById(userId);

        Pageable<Article> pageData = articleService.getList(pageable, user);

        return new APIResult(pageData);
    }

    @RequestMapping(value = "/delete")
    public APIResult delete(@RequestBody Article article) throws IOException {
        articleService.delete(article);
        return new APIResult();
    }

    @RequestMapping(value = "/save")
    public APIResult save(@RequestBody Article article) throws IOException {
        articleService.save(article);
        return new APIResult();
    }

    @RequestMapping(value = "/getDetail")
    public APIResult getDetail(@RequestBody Article article) throws IOException {
        return new APIResult(articleService.getDetail(article));
    }

    @RequestMapping(value = "/publish")
    public APIResult publish(@RequestBody Article article) throws IOException {
        articleService.setStatus(article, 1);
        return new APIResult();
    }

    @RequestMapping(value = "/unpublished")
    public APIResult unpublished(@RequestBody Article article) throws IOException {
        articleService.setStatus(article, 0);
        return new APIResult();
    }

    @RequestMapping(value = "/getAllList")
    public APIResult getAllList() throws IOException {
        return new APIResult(articleService.getAllList());
    }
}
