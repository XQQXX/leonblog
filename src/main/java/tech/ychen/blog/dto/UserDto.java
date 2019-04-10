package tech.ychen.blog.dto;

import org.springframework.stereotype.Component;
import tech.ychen.blog.entiy.User;

/**
 * @author leon
 * @date 2019-04-08 14:58
 *
 */
@Component
public class UserDto  {

    private String oldPassword;

    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
