package tech.ychen.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ychen.blog.dao.ArticleTagDao;
import tech.ychen.blog.entiy.ArticleTag;
import tech.ychen.blog.service.ArticleTagService;

import java.util.List;

/**
 * @author leon
 * @date 2019-04-09 11:09
 */
@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Autowired
    private ArticleTagDao articleTagDao;

    /**
     * 插入Article tag
     * @param articleTag
     * @return
     */
    @Override
    public int insertArticleTag(ArticleTag articleTag) {
        return articleTagDao.insert(articleTag);
    }

    /**
     * 根据文章id 列出 articleTag
     * @param articleId
     * @return
     */
    @Override
    public List<ArticleTag> listByArticleId(Integer articleId) {
        return articleTagDao.selectAllByArticleId(articleId);
    }

    @Override
    public int deleteByArticleId(Integer articleId) {
        return articleTagDao.deleteByArticleId(articleId);
    }
}
