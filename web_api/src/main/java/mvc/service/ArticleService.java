package mvc.service;

import mvc.dao.pojo.Article;
import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.Product;
import mvc.dao.pojo.User;

import java.util.List;

/**
 * Created by peijian.wang on 2017/3/18.
 */
public interface ArticleService {

    Pageable<Article> getList(Pageable<Article> pageable, User user);

    int delete(Article article);

    boolean save(Article article);

    Article getDetail(Article article);

    boolean setStatus(Article article, int status);

    /**
     * 获取所有的文章列表
     *
     * @return
     */
    List<Article> getAllList();


    List<Article> getCoverList();

    Article getDetail(String id);

    List<Product> getRelationList(String id);
}
