package tech.ychen.blog.dto;

import org.springframework.stereotype.Component;

/**
 * @author leon
 * @date 2019-04-08 17:31
 */
@Component
public class AdminUserDto {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
