package tech.ychen.blog.dto;

import org.springframework.stereotype.Component;
import tech.ychen.blog.entiy.Tag;

import java.util.List;

/**
 * @author leon
 * @date 2019-04-08 17:51
 */
@Component
public class ArticleDto {

    private String articleTitle;

    private String articleSummary;

    private String articleContent;//文章内容 md源码

    private String articlePic; //文章图片地址

    private Integer top;//文章是否置顶

    private List<String> tagList;

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticlePic() {
        return articlePic;
    }

    public void setArticlePic(String articlePic) {
        this.articlePic = articlePic;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
