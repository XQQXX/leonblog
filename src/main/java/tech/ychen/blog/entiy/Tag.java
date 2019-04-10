package tech.ychen.blog.entiy;

/**
 * 文章标签表
 */
public class Tag {

  private Integer id;
  private String tagName;
  private Integer number;//该标签下文章数量
  private Integer effective;//标签是否有效 0：否，1：是
  private java.util.Date gmtCreate;
  private java.util.Date gmtModified;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getTagName() {
    return tagName;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }


  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
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
