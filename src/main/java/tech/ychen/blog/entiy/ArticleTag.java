package tech.ychen.blog.entiy;

/**
 * 文章标签表 关联 文章表和标签表
 */
public class ArticleTag {

  private Integer id;
  private Integer articleId;//article_info 表id
  private Integer tagId;//tag 表 id
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


  public Integer getTagId() {
    return tagId;
  }

  public void setTagId(Integer tagId) {
    this.tagId = tagId;
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
