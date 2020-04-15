package testDao;


import annuaireapp.dao.Dao;
import annuaireapp.dao.IDao;

import annuaireapp.dao.SpringConfiguration;
import annuaireapp.models.Group;
import annuaireapp.models.Person;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;


import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.TabularData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)

@ComponentScan(basePackages = ("annuaireapp.dao"))
public class TestDao {


    @Autowired
    IDao dao;

    static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    static Person p1, p2, p3;
    static Group g1, g2;
    static Date date1, date2, date3;

    @BeforeAll
    public static void beforeAll() {
        g2 = new Group(2, "M2");

        try {
            date1 = df.parse("13-07-1942");
            date2 = df.parse("26-05-1995");
            date3 = df.parse("01-01-2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        p1 = new Person("Rian", "Jacques", "jacques.rian@gmail.com",
                "rian.com", date1, "password");

        p2 = new Person("Jordin", "Michel", "jordin.michel@gmail.com",
                "jordin.com", date2, "annuaire");

        p3 = new Person("Petit", "Pompe", "pompe.petit@gmail.com",
                "lilpump.com", date3, "12345678");

        p1.setGroup(g1);
        p2.setGroup(g2);
        p3.setGroup(g2);
    }


    public static void afterAll() {
    }

    @Test
    public void testAddGroup() {
        g1 = new Group(1, "M1");
        dao.add(g1);
        Group result = dao.find(Group.class, g1.getId());
        assertEquals(result.getId(), g1.getId());

    }
}
