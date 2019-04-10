package tech.ychen.blog.provider;

import org.apache.ibatis.jdbc.SQL;
import tech.ychen.blog.entiy.ArticleComment;

/**
 * @author leon
 * @date 2019-04-08 22:41
 */
public class ArticleCommentProvider {
    public String updateArticleComment(final ArticleComment articleComment) {

        return new SQL() {
            {

                UPDATE("article_comment");

                if (articleComment.getArticleId() != null) {

                    SET("article_id=#{articleId}");
                }
                if (articleComment.getUserId() != null) {

                    SET("user_id=#{userId}");
                }
                if (articleComment.getComContent() != null) {

                    SET("com_content=#{comContent}");
                }
                if (articleComment.getEffective() != null) {

                    SET("is_effective=#{effective}");
                }

                if (articleComment.getGmtCreate() != null) {
                    SET("gmt_create=#{gmtCreate}");
                }
                if (articleComment.getGmtModified() != null) {
                    SET("gmt_modified=#{gmtModified}");
                }


                WHERE("id=#{id}");


            }
        }.toString();


    }
}

