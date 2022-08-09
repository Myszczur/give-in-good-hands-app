package pl.coderslab.charity.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendSimpleMessage(
            String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("myszczur02@asd.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}
