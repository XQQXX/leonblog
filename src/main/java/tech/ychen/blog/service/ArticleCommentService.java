package tech.ychen.blog.service;

import tech.ychen.blog.entiy.ArticleComment;

import java.util.List;

public interface ArticleCommentService {

    List<ArticleComment> findAllCommentSByArticleId(Integer articleId);

    int insertComment(ArticleComment articleComment);

    int updateArticleComment(ArticleComment articleComment);

    ArticleComment findArticleCommentById(Integer id);
}
