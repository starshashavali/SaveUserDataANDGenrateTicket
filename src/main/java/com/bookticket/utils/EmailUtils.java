package com.bookticket.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String text) {
        sendEmail(to, subject, text,null,null);
    }

    public void sendEmailWithAttachment(String to, String subject, String text, byte[] attachment, String attachmentName) {
        sendEmail(to, subject, text, attachment, attachmentName);
    }

    private void sendEmail(String to, String subject, String text, byte[] attachment, String attachmentName) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, attachment != null);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            if (attachment != null && attachmentName != null) {
                ByteArrayResource attachmentResource = new ByteArrayResource(attachment);
                helper.addAttachment(attachmentName, attachmentResource);
            }

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
