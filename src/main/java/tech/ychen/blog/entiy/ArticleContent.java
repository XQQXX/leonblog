package tech.ychen.blog.entiy;

/**
 * 文章内容表
 */
public class ArticleContent {

  private Integer id;
  private Integer articleId;//article_info 表 id
  private String articleContent;//文章内容 md源码
  private java.util.Date gmtCreate;
  private java.util.Date gmtModified;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Integer getArticleId() {
    return articleId;
  }

  public void setArticleId(Integer articleId) {
    this.articleId = articleId;
  }


  public String getArticleContent() {
    return articleContent;
  }

  public void setArticleContent(String articleContent) {
    this.articleContent = articleContent;
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
