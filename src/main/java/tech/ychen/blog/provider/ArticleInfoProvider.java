package tech.ychen.blog.provider;

import org.apache.ibatis.jdbc.SQL;
import tech.ychen.blog.entiy.ArticleInfo;
import tech.ychen.blog.entiy.User;

/**
 * @author leon
 * @date 2019-04-08 16:13
 */
public class ArticleInfoProvider {

    public String updateArticleInfo(final ArticleInfo articleInfo){

        return new SQL(){{

            UPDATE("article_info");

            if(articleInfo.getArticleTitle() != null){

                SET("article_title=#{articleTitle}");
            }
            if(articleInfo.getArticleSummary() != null){

                SET("article_summary=#{articleSummary}");
            }
            if(articleInfo.getArticlePic() != null){

                SET("article_pic=#{articlePic}");
            }
            if(articleInfo.getArticleTraffic()!=null){

                SET("article_traffic=#{articleTraffic}");
            }
            if(articleInfo.getTop() != null){

                SET("is_top=#{top}");
            }
            if(articleInfo.getAllowComment() != null){
                SET("is_allow_comment=#{allowComment}");
            }
            if(articleInfo.getGmtCreate() != null){
                SET("gmt_create=#{gmtCreate}");
            }
            if(articleInfo.getGmtModified() != null){
                SET("gmt_modified=#{gmtModified}");
            }


            WHERE("id=#{id}");


        }
        }.toString();



    }
}
