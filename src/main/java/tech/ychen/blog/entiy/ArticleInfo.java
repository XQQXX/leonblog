package tech.ychen.blog.entiy;

/**
 * 文章表
 */
public class ArticleInfo {

  private Integer id;
  private String articleTitle;
  private String articleSummary;//文章摘要
  private String articlePic; //文章图片地址
  private Integer articleTraffic;//文章访问量
  private Integer top;//文章是否置顶
  private Integer allowComment;//文章是否允许评论 0 不可评论，1可以评论
  private java.util.Date gmtCreate;
  private java.util.Date gmtModified;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


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


  public String getArticlePic() {
    return articlePic;
  }

  public void setArticlePic(String articlePic) {
    this.articlePic = articlePic;
  }


  public Integer getArticleTraffic() {
    return articleTraffic;
  }

  public void setArticleTraffic(Integer articleTraffic) {
    this.articleTraffic = articleTraffic;
  }

  public Integer getTop() {
    return top;
  }

  public void setTop(Integer top) {
    this.top = top;
  }

  public Integer getAllowComment() {
    return allowComment;
  }

  public void setAllowComment(Integer allowComment) {
    this.allowComment = allowComment;
  }

  public java.util.Date getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(java.util.Date gmtCreate) {
    this.gmtCreate = gmtCreate;
  }


  public java.util.Date getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(java.util.Date gmtModified) {
    this.gmtModified = gmtModified;
  }

}
