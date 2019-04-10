package tech.ychen.blog.provider;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.jdbc.SQL;
import tech.ychen.blog.entiy.ArticleTag;

/**
 * @author leon
 * @date 2019-04-09 12:55
 */
public class ArticleTagProvider {

    public String updateArticleTag(final ArticleTag articleTag) {


        return new SQL() {{
            UPDATE("article_tag");

            if (articleTag.getArticleId() != null) {

                SET("article_id=#{articleId}");
            }
            if (articleTag.getTagId() != null) {
                SET("tag_id=#{tagId}");
            }
            if (articleTag.getGmtCreate() != null) {
                SET("gmt_create=#{gmtCreate}");
            }
            if (articleTag.getGmtModified() != null) {
                SET("gmt_modified=#{gmtModified}");
            }


        }}.toString();


    }


    public String deletebyArticleId(Integer articleId) {

        return new SQL() {
            {

            DELETE_FROM("article_tag");

            WHERE("article_id = #{article_id}");

        }


    }.

    toString();


}




}
