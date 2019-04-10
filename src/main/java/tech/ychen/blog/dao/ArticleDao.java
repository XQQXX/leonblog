package tech.ychen.blog.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import tech.ychen.blog.entiy.ArticleContent;
import tech.ychen.blog.entiy.ArticleInfo;
import tech.ychen.blog.provider.ArticleContentProvider;
import tech.ychen.blog.provider.ArticleInfoProvider;
import tech.ychen.blog.provider.UserProvider;

import java.util.List;

/**
 * @author leon
 * @date 2019-04-08 15:37
 */

@Mapper
@Component
public interface ArticleDao {


    /**
     * 增加文章记录
     * @param articleInfo
     * @return
     */

    @Insert("INSERT INTO `article_info`(`article_title`, `article_summary`, `article_pic`, `article_traffic`, `is_top`, `is_allow_comment`, `gmt_create`, `gmt_modified`) " +
            "VALUES (#{articleTitle}, #{articleSummary}, #{articlePic}, #{articleTraffic}, #{top}, #{allowComment}, #{gmtCreate}, #{gmtModified});")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    //@SelectKey(statement="call identity()", keyProperty="id", before=false, resultType=int.class)
    int insertinfo(ArticleInfo articleInfo);


    @Insert("INSERT INTO `article_content`(`article_id`, `article_content`, `gmt_create`, `gmt_modified`) " +
            "VALUES (#{articleId}, #{articleContent},#{gmtCreate},#{gmtModified})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    int insertContent(ArticleContent articleContent);


    /**
     * 删除文章记录
     * @param id
     * @return
     */
    @Delete("DELETE from `article_info` WHERE `id` = #{id}")
    int deleteInfoByid(Integer id);

    @Delete("DELETE from `article_content` WHERE `article_id` = #{articleId}")
    int deletrContent(Integer articleId);


    /**
     * 更新文章记录
     * @param articleInfo
     * @return
     */
    @UpdateProvider(type = ArticleInfoProvider.class,method = "updateArticleInfo")
    //@Update("UPDATE `leonblog`.`article_info` SET `article_title` = 't', `article_summary` = 't', `article_pic` = 't', `article_traffic` = 1, `is_top` = 1, `is_allow_comment` = 1, `gmt_create` = '2019-04-08 15:44:36', `gmt_modified` = '2019-04-08 15:44:43' WHERE `id` = 1;\n")
    int updateArticleInfo(ArticleInfo articleInfo);

    @UpdateProvider(type = ArticleContentProvider.class,method = "updateArticleContent")
    int updateArticleContent(ArticleContent articleContent);


    /**
     * 根据id查文章信息
     * @param id
     * @return
     */
    @Select("SELECT * FROM `article_info` WHERE `id` = #{id}")
    @Results({
            @Result(property = "articleTitle", column = "article_title"),
            @Result(property = "articleSummary", column ="article_summary" ),
            @Result(property = "articlePic", column = "article_pic"),
            @Result(property = "articleTraffic", column = "article_traffic"),
            @Result(property = "top", column ="is_top"),
            @Result(property = "allowComment", column = "is_allow_comment"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    ArticleInfo findArticleInfoById(Integer id);

    @Select("SELECT * FROM `article_info` WHERE `article_title` = #{title}")
    @Results({
            @Result(property = "articleTitle", column = "article_title"),
            @Result(property = "articleSummary", column ="article_summary" ),
            @Result(property = "articlePic", column = "article_pic"),
            @Result(property = "articleTraffic", column = "article_traffic"),
            @Result(property = "top", column ="is_top"),
            @Result(property = "allowComment", column = "is_allow_comment"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    ArticleInfo findArticleInfoByTiltle(String title);

    /**
     * 查询所有文章信息
     * @return
     */
    @Select("SELECT * FROM `article_info`")
    @Results({
            @Result(property = "articleTitle", column = "article_title"),
            @Result(property = "articleSummary", column ="article_summary" ),
            @Result(property = "articlePic", column = "article_pic"),
            @Result(property = "articleTraffic", column = "article_traffic"),
            @Result(property = "top", column ="is_top"),
            @Result(property = "allowComment", column = "is_allow_comment"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    List<ArticleInfo> findAllArticleInfo();


    /**
     * 根据articleId查文章内容
     * @param articleId
     * @return
     */
    @Select("SELECT * FROM `article_content` WHERE `article_id` = #{articleId}")
    @Results({
            @Result(property = "articleId", column = "article_id"),
            @Result(property = "articleContent", column = "article_content"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    ArticleContent findContentByArticleId(Integer articleId);


    /**
     * 查询所有文章内容
     * @return
     */
    @Select("SELECT * FROM `article_content`")
    @Results({
            @Result(property = "articleId", column = "article_id"),
            @Result(property = "articleContent", column = "article_content"),
            @Result(property = "gmtCreate", column = "gmt_create"),
            @Result(property = "gmtModified", column = "gmt_modified")
    })
    List<ArticleContent> findAllArticleContent();
}
