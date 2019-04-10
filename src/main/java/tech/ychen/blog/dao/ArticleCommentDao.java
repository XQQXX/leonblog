package tech.ychen.blog.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tech.ychen.blog.entiy.ArticleComment;
import tech.ychen.blog.entiy.ArticleContent;
import tech.ychen.blog.provider.ArticleCommentProvider;
import tech.ychen.blog.provider.ArticleContentProvider;

import java.util.List;

@Mapper
@Component
public interface ArticleCommentDao {

    /**
     * 根据文章id查评论
     * @param articleId
     * @return
     */
    @Select("SELECT * FROM `article_comment` WHERE `article_id`= #{articleId}")
    @Results({
            @Result(property = "articleId",column = "article_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "comContent",column = "com_content"),
            @Result(property = "effective",column = "is_effective"),
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
    })
    List<ArticleComment> findArticleCommentByArticleId(Integer articleId);


    /**
     * 插入评论
     * @param articleComment
     * @return
     */
    @Insert("INSERT INTO `article_comment`(`article_id`, `user_id`, `com_content`, `is_effective`, `gmt_create`, `gmt_modified`)" +
            " VALUES (#{articleId}, #{userId}, #{comContent}, #{effective}, #{gmtCreate}, #{gmtModified});")
    int insertComment(ArticleComment articleComment);

    @UpdateProvider(type = ArticleCommentProvider.class,method = "updateArticleComment")
    int updateComment(ArticleComment articleComment);


    @Select("SELECT * FROM `article_comment` WHERE `id`= #{id}")
    @Results({
            @Result(property = "articleId",column = "article_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "comContent",column = "com_content"),
            @Result(property = "effective",column = "is_effective"),
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
    })
    ArticleComment findArticleCommentbyId(Integer id);
}
