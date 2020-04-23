package annuaireapp.business;

import org.springframework.mail.SimpleMailMessage;

public interface IEmailManager {
    public void sendMail(String toEmail, String subject, String message);
    public SimpleMailMessage getTemplate();
}
