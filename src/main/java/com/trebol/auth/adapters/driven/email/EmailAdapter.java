package com.trebol.auth.adapters.driven.email;

import com.trebol.auth.domain.spi.IEmailPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@RequiredArgsConstructor
public class EmailAdapter implements IEmailPort {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmailPassword(String to, String password, String name) {
        String subject = "Contraseña generada automáticamente.";
        String body = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<div style='background-color: #f4f4f4; padding: 20px;'>" +
                "<h2 style='color: #1f5d11;'>Hola, " + name + "!</h2>" +
                "<p style='font-size: 16px;'>Tu cuenta ha sido creada exitosamente. A continuación, encontrarás tu contraseña generada automáticamente:</p>" +
                "<div style='background-color: #ffffff; padding: 15px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);'>" +
                "<h3 style='color: #1f5d11;'>Contraseña: <span style='font-weight: bold;'>" + password + "</span></h3>" +
                "</div>" +
                "<p style='font-size: 16px;'>Gracias,<br/>Treból</p>" +
                "<img src='https://firebasestorage.googleapis.com/v0/b/trebol-3c95d.appspot.com/o/logo.png?alt=media&token=afae3435-ed69-4ee9-87ce-834d664e6b2d' alt='Logo Trebol' style='width: 50px; height: auto;' >" +
                "</div>" +
                "</body>" +
                "</html>";
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmailRecoveryPassword(String to, String code, String name) {
        String subject = "Código de recuperación de contraseña.";
        String body = "<html>" +
                "<body style='font-family: Arial, sans-serif;'>" +
                "<div style='background-color: #f4f4f4; padding: 20px;'>" +
                "<h2 style='color: #1f5d11;'>Hola, " + name + "!</h2>" +
                "<p style='font-size: 16px;'>Hemos recibido una solicitud para recuperar tu contraseña. A continuación, encontrarás tu código de recuperación:</p>" +
                "<div style='background-color: #ffffff; padding: 15px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);'>" +
                "<h3 style='color: #1f5d11;'>Código de Recuperación: <span style='font-weight: bold;'>" + code + "</span></h3>" +
                "</div>" +
                "<p style='font-size: 16px;'>Este código es válido por un tiempo limitado. Si no realizaste esta solicitud, puedes ignorar este mensaje.</p>" +
                "<p style='font-size: 16px;'>Gracias,<br/>Treból</p>" +
                "<img src='https://firebasestorage.googleapis.com/v0/b/trebol-3c95d.appspot.com/o/logo.png?alt=media&token=afae3435-ed69-4ee9-87ce-834d664e6b2d' alt='Logo Trebol' style='width: 50px; height: auto;' >" +
                "</div>" +
                "</body>" +
                "</html>";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
