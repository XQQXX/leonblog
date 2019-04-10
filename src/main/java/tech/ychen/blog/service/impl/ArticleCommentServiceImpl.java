package tech.ychen.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ychen.blog.dao.ArticleCommentDao;
import tech.ychen.blog.entiy.ArticleComment;
import tech.ychen.blog.service.ArticleCommentService;

import java.util.List;

/**
 * @author leon
 * @date 2019-04-08 21:39
 */
@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentDao articleCommentDao;

    /**
     * 根据文章id列出所有评论
     * @param articleId
     * @return
     */
    @Override
    public List<ArticleComment> findAllCommentSByArticleId(Integer articleId) {
        return articleCommentDao.findArticleCommentByArticleId(articleId) ;
    }

    /**
     * 插入一条评论
     * @param articleComment
     * @return
     */
    @Override
    public int insertComment(ArticleComment articleComment) {
        return articleCommentDao.insertComment(articleComment);
    }

    /**
     * 更新评论
     * @param articleComment
     * @return
     */
    @Override
    public int updateArticleComment(ArticleComment articleComment) {
        return articleCommentDao.updateComment(articleComment);
    }

    /**
     * 通过id找评论
     * @param id
     * @return
     */
    @Override
    public ArticleComment findArticleCommentById(Integer id) {
        return articleCommentDao.findArticleCommentbyId(id);
    }
}
