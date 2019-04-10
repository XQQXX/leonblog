package tech.ychen.blog.service;

import tech.ychen.blog.entiy.ArticleTag;

import java.util.List;

public interface ArticleTagService {

    int insertArticleTag(ArticleTag articleTag);

    List<ArticleTag> listByArticleId(Integer articleId);

    List<ArticleTag> listByTagId(Integer tagId);

    int deleteByArticleId(Integer articleId);


}
