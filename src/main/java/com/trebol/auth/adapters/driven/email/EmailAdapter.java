package com.trebol.auth.adapters.driven.email;

import com.trebol.auth.domain.spi.IEmailPort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RequiredArgsConstructor
public class EmailAdapter implements IEmailPort {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmailPassword(String to, String password, String name) {
        String subject = "Contraseña generada automáticamente.";
        String body = "Hola [Nombre del Usuario],\n" +
                "\n" +
                "Tu cuenta ha sido creada exitosamente. A continuación, encontrarás tu contraseña generada automáticamente:\n" +
                "\n" +
                "Contraseña: [contraseña]\n" +
                "\n" +
                "\n" +
                "Gracias,\n" +
                "Treból\n";
        body = body.replace("[Nombre del Usuario]", name);
        body = body.replace("[contraseña]", password);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailSender.send(mailMessage);
    }
}
