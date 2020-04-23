package testEmailManager;

import annuaireapp.business.EmailManager;
import annuaireapp.dao.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)

public class TestEmailManager {

    @Autowired
    EmailManager emailManager;

    @Test
    public void testEmail() {
        emailManager.sendMail("alexandre.putzu@etu.univ-amu.fr", "Test subject", "Test mail");
    }
}
