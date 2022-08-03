package pl.coderslab.charity.email;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}