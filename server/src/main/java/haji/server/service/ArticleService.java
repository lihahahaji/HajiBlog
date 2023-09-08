package haji.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import haji.server.controller.ArticleController;
import haji.server.dao.ArticleDao;
import haji.server.pojo.ArticlePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleDao articleDao;

    public List<ArticlePojo> selectAll()
    {
        return articleDao.selectList(new QueryWrapper<>());
    }

    public void insertArticle(String articleTitle,String articleContent)
    {
        articleDao.insert(new ArticlePojo(null,articleTitle,articleContent));
    }

    public void updateArticleById(Integer articleId, String articleTitle,String articleContent)
    {
        articleDao.updateById(new ArticlePojo(articleId,articleTitle,articleContent));
    }

    public void deleteById(Integer articleId)
    {
        articleDao.deleteById(articleId);
    }




}
