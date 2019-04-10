package tech.ychen.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tech.ychen.blog.config.MailConfig;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private JavaMailSender javaMailSender;


    /**
     * 发送普通邮件
     *
     * @param email
     * @param code
     *
     */
    public  void sendEffectiveMail(String email,int code){

        SimpleMailMessage message = new SimpleMailMessage();

        String effectLink = " http://localhost:8080/api/v1/session/mail/effective?code="+code;

        message.setFrom(mailConfig.getMailUsername());

        message.setTo(email);

        message.setSubject("leonblog 账号激活");

        message.setText(effectLink);

        javaMailSender.send(message);



    }

    /**
     * 发送带有附件的邮件
     *
     * @param to
     * @param title
     * @param content
     * @param fileList
     */
    public void sendAttachmentsMail(String to, String title, String content, List<File> fileList) {

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailConfig.getMailUsername());
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(content);
            String fileName = null;
            for (File file : fileList) {
                fileName = MimeUtility.encodeText(file.getName(), "GB2312", "B");
                helper.addAttachment(fileName, file);
            }
        } catch (Exception e) {

            e.printStackTrace();

        }

        javaMailSender.send(message);


    }


}
