package tech.ychen.blog.service;


import tech.ychen.blog.entiy.ArticleContent;
import tech.ychen.blog.entiy.ArticleInfo;

import java.util.List;

public interface ArticleService {

    List<ArticleInfo> findAllArticleInfo();

    ArticleContent findArticleContentByArticleId(Integer articleId);

    ArticleInfo findArticleInfotById(Integer id);

    int insertArticleInfo(ArticleInfo articleInfo);

    int inserArticleContent(ArticleContent articleContent);

    void deleteArticle(Integer id);

    void updateArticle(ArticleInfo articleInfo);

    ArticleInfo findArticleInfoByTitle(String title);

 }
