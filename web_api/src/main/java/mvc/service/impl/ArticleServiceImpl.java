package mvc.service.impl;

import mvc.dao.maps.ArticleMapper;
import mvc.dao.maps.ProductMapper;
import mvc.dao.pojo.Article;
import mvc.dao.pojo.Pageable;
import mvc.dao.pojo.Product;
import mvc.dao.pojo.User;
import mvc.service.ArticleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import utils.enumeration.RoleCode;
import utils.tools.Lang;
import utils.tools.LocalFile;

import java.util.Date;
import java.util.List;

/**
 * Created by peijian.wang on 2017/3/18.
 */
@Service
//@Transactional
public class ArticleServiceImpl implements ArticleService {

    private static Logger log = Logger.getLogger(ArticleServiceImpl.class);

    private ArticleMapper articleMapper;

    private ProductMapper productMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper, ProductMapper productMapper) {
        this.articleMapper = articleMapper;
        this.productMapper = productMapper;
    }

    public Pageable<Article> getList(Pageable<Article> pageable, User user) {

        pageable.countStart();

        //工人只能看到自己的文章
        if(user.getRole().getCode().equals(RoleCode.WORKER)){
            Article article = new Article();
            article.setUserId(user.getId());
            pageable.setObject(article);
            pageable.setList(articleMapper.getSelfList(pageable));
            pageable.setCount(articleMapper.countSelfArticle(article));
        }else{
            //管理员、游客能看到所有的文章
            pageable.setList(articleMapper.getList(pageable));
            pageable.setCount(articleMapper.countArticle());
        }

        return pageable;
    }

    public int delete(Article article) {
        Article oldArticle = articleMapper.findById(article);
        int line = articleMapper.delete(article);
        //删除旧的封面文件
        if(line == 1){
            LocalFile.removeLocalFile(oldArticle.getCover(), 1);
        }
        return line;
    }

    public boolean save(Article article){
        //判断是否存在相同的id如果有则是更新
        Article oldArticle = articleMapper.findById(article);
        if (oldArticle == null) {
            //新增一条记录
            article.setId(Lang.getUUID());
            //article.setPublishTime(new Date());
            article.setUpdateTime(new Date());
            //默认是未发布
            article.setStatus(0);
            articleMapper.add(article);

        } else {
            //编辑一条记录
            String oldCover = oldArticle.getCover();
            oldArticle.setUpdateTime(new Date());
            oldArticle.setPublishTime(article.getPublishTime());
            oldArticle.setTitle(article.getTitle());
            oldArticle.setContent(article.getContent());
            oldArticle.setCover(article.getCover());
            oldArticle.setUserId(article.getUserId());
            articleMapper.update(oldArticle);
            //更新成功,如果是选择了新的封面，则删除旧的封面文件
            if(!oldCover.equals(article.getCover())){
                LocalFile.removeLocalFile(oldCover, 1);
            }
        }

        return true;
    }

    public Article getDetail(Article article) {
        return articleMapper.findById(article);
    }

    public boolean setStatus(Article article, int status) {
        article.setStatus(status);
        articleMapper.setStatus(article);
        return false;
    }

    /**
     * 获取所有的文章列表
     *
     * @return
     */
    public List<Article> getAllList() {
        return articleMapper.getAllList();
    }

    public List<Article> getCoverList() {
        return articleMapper.getCoverList();
    }

    public Article getDetail(String id) {
        return articleMapper.getDetail(id);
    }

    public List<Product> getRelationList(String id) {
        return productMapper.getRelationList(id);
    }

}
