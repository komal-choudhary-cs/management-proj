package com.common.management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
 import org.springframework.core.io.ByteArrayResource;



import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendLoginSuccessEmail(
            String toEmail,
            String username,
            byte[] pdfBytes
    ) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Login Successful");
            helper.setText(
                "Hi " + username + ",\nYou have logged in successfully."
            );

            helper.addAttachment(
                "login-success.pdf",
                new ByteArrayResource(pdfBytes)
            );

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
