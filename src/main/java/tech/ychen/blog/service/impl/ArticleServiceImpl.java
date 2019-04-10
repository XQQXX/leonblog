package tech.ychen.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ychen.blog.dao.ArticleDao;
import tech.ychen.blog.entiy.ArticleContent;
import tech.ychen.blog.entiy.ArticleInfo;
import tech.ychen.blog.service.ArticleService;

import java.util.List;

/**
 * @author leon
 * @date 2019-04-08 16:44
 */
@Service
public class ArticleServiceImpl implements ArticleService {



    @Autowired
    private ArticleDao articleDao;

    /**
     * 查到所有文章
     * @return
     */
    @Override
    public List<ArticleInfo> findAllArticleInfo() {
        return articleDao.findAllArticleInfo();
    }

    /**
     *
     * @return
     */
    @Override
    public ArticleContent findArticleContentByArticleId(Integer articleId) {
        return articleDao.findContentByArticleId(articleId);
    }

    @Override
    public ArticleInfo findArticleInfotById(Integer id) {
        return articleDao.findArticleInfoById(id);
    }
    @Override
    public int insertArticleInfo(ArticleInfo articleInfo) {
        return articleDao.insertinfo(articleInfo);
    }

    @Override
    public int inserArticleContent(ArticleContent articleContent) {
        return articleDao.insertContent(articleContent);
    }


    @Override
    public void deleteArticle(Integer id) {
         articleDao.deleteInfoByid(id);
         articleDao.deletrContent(id);
    }

    @Override
    public void updateArticle(ArticleInfo articleInfo) {
        articleDao.updateArticleInfo(articleInfo);
        //articleDao.updateArticleContent(articleContent);
    }

    @Override
    public ArticleInfo findArticleInfoByTitle(String title) {
        return articleDao.findArticleInfoByTiltle(title);
    }
}
