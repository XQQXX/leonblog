package tech.ychen.blog.entiy;

/**
 * 文章评论表 关联 article_info 表和 user 表
 */
public class ArticleComment {

  private Integer id;
  private Integer articleId;//article_info 表 id
  private Integer userId;//user 表 id
  private String comContent;//评论内容
  private Integer effective;//评论是否有效 0：否，1：是
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


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getComContent() {
    return comContent;
  }

  public void setComContent(String comContent) {
    this.comContent = comContent;
  }

  public Integer getEffective() {
    return effective;
  }

  public void setEffective(Integer effective) {
    this.effective = effective;
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
