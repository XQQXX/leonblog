package tech.ychen.blog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;
import tech.ychen.blog.config.MailConfig;

import javax.mail.internet.MimeMessage;

/**
 * @author leon
 * @date 2019-04-07 16:08
 */
@Component
public class MailUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailConfig mailConfig;




}
