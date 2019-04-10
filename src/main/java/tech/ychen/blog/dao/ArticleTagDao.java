package tech.ychen.blog.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tech.ychen.blog.entiy.ArticleTag;
import tech.ychen.blog.provider.ArticleTagProvider;

import java.util.List;

/**
 * @author leon
 * @date 2019-04-09 11:01
 */
@Mapper
@Component
public interface ArticleTagDao {


    /**
     * 插入一条 tag article 记录
     * @param articleTag
     * @return
     */
    @Insert("INSERT INTO `article_tag`(`article_id`, `tag_id`, `gmt_create`, `gmt_modified`) " +
            "VALUES (#{articleId}, #{tagId}, #{gmtCreate}, #{gmtModified});")
    int insert(ArticleTag articleTag);


    @Select("SELECT * FROM `article_tag` WHERE(`article_id`= #{articleId})")
    List<ArticleTag> selectAllByArticleId(Integer articleId);



    @Select("SELECT * FROM `article_tag` WHERE(`tag_id` = #{tagId})")
    List<ArticleTag> selectAllbyTagId(Integer tagId);



   // @Delete("DELETE * FROM `article_tag` WHERE(`article_id`= #{articleId})")  //ssss
    @DeleteProvider(type = ArticleTagProvider.class,method ="deletebyArticleId" )
    int deleteByArticleId(Integer id);









}
