package annuaireapp.business;

import annuaireapp.dao.IDao;
import annuaireapp.models.Person;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailManager implements IEmailManager{
    @Autowired
    IDao dao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public SimpleMailMessage template;

    public EmailManager(){}

    @Override
    public void sendMail(String toEmail, String subject, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(toEmail);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailMessage.setFrom("no-reply@annuaire.com");

        javaMailSender.send(mailMessage);
    }

    @Override
    public SimpleMailMessage getTemplate(){
        return this.template;
    }
}