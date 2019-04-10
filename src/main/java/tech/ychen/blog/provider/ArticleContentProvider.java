package tech.ychen.blog.provider;

import org.apache.ibatis.jdbc.SQL;
import tech.ychen.blog.entiy.ArticleContent;
import tech.ychen.blog.entiy.ArticleInfo;

/**
 * @author leon
 * @date 2019-04-08 16:24
 */
public class ArticleContentProvider  {

    public String updateArticleContent(final ArticleContent articleContent){

        return new SQL(){{

            UPDATE("article_content");

            if(articleContent.getArticleId() != null){

                SET("article_id=#{articleId}");
            }
            if(articleContent.getArticleContent() != null){

                SET("article_content=#{articleContent}");
            }

            if(articleContent.getGmtCreate() != null){
                SET("gmt_create=#{gmtCreate}");
            }
            if(articleContent.getGmtModified() != null){
                SET("gmt_modified=#{gmtModified}");
            }


            WHERE("id=#{id}");


        }
        }.toString();



    }




















    //UPDATE `leonblog`.`article_content` SET `article_id` = 1, `article_content` = '1',
    // `gmt_create` = '2019-04-08 15:44:56', `gmt_modified` = '2019-04-08 15:45:02' WHERE `id` = 1;


}
