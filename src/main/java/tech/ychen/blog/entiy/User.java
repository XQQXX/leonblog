package tech.ychen.blog.entiy;


import java.util.Date;
import java.util.Random;

/**
 * 用户表
 */
public class User {

  private Integer id;
  private String userName;
  private String userPwd;
  private String userEmail;
  private String userHeadimg;
  private String userIp;
  private int effective; //用户状态 1，可用；0，不可用
  private int code; //生成的验证码
  private java.util.Date gmtCreate;
  private java.util.Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserHeadimg() {
        return userHeadimg;
    }

    public void setUserHeadimg(String userHeadimg) {
        this.userHeadimg = userHeadimg;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public int getEffective() {
        return effective;
    }

    public void setEffective(int effective) {
        this.effective = effective;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
