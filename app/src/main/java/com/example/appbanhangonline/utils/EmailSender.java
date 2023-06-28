package com.example.appbanhangonline.utils;

import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    private String email;
    private String code;

    public EmailSender(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public void sendEmail() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new SendEmailTask());
        executor.shutdown();
    }

    private class SendEmailTask implements Callable<Void> {
        @Override
        public Void call() throws Exception {
            // Địa chỉ email và mật khẩu của tài khoản gửi email
            String senderEmail = "thuythan2811@gmail.com";
            String password = "voxiwgjytxygjgfx";

            // Cấu hình các thuộc tính để gửi email
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            // Tạo phiên làm việc để gửi email
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, password);
                }
            });

            try {

                MimeMessage message = new MimeMessage(session);

                message.setFrom(new InternetAddress(senderEmail));

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

                message.setSubject("Verification Code");
                message.setText("Your verification code is: " + code);

                // Gửi email
                Logger.info("code" + code);
                Transport.send(message);
            } catch (MessagingException e) {
                Logger.error(String.valueOf(e));
                throw new RuntimeException(e);
            }

            return null;
        }
    }
}

