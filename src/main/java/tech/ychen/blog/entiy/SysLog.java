package tech.ychen.blog.entiy;

/**
 * 系统日志表
 */
public class SysLog {

  private Integer id;
  private String ip;//操作ip
  private String remark;//操作内容
  private String operateUrl;//操作地址
  private String operateBy;//操作浏览器
  private java.util.Date gmtCreate;
  private java.util.Date gmtModified;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getOperateUrl() {
    return operateUrl;
  }

  public void setOperateUrl(String operateUrl) {
    this.operateUrl = operateUrl;
  }


  public String getOperateBy() {
    return operateBy;
  }

  public void setOperateBy(String operateBy) {
    this.operateBy = operateBy;
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
