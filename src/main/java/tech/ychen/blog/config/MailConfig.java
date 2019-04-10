package tech.ychen.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 邮件配置类
 * @author leon
 * @date 2019-04-04 21:59
 */
@Configuration
@PropertySource(value="classpath:application.properties")
public class MailConfig {

    /**
     * 发送验证消息的邮箱
     */
    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    @Value("${spring.mail.host}")
    private String mailHost;

    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }
}
