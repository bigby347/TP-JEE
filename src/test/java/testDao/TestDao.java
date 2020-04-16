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

    @BeforeAll
    public static void beforeAll() {
        g1 = new Group(1, "M1");
        g2 = new Group(2, "M2");

        p1 = new Person("Rian", "Jacques", "jacques.rian@gmail.com",
                "rian.com", "13-07-1942", "password");

        p2 = new Person("Jordin", "Michel", "jordin.michel@gmail.com",
                "jordin.com", "26-05-1995", "annuaire");

        p3 = new Person("Petit", "Pompe", "pompe.petit@gmail.com",
                "lilpump.com", "01-01-2020", "12345678");

        p1.setGroup(g1);
        p2.setGroup(g2);
        p3.setGroup(g2);
    }


    public static void afterAll() {
    }

    @Test
    public void testAddGroup() {

        dao.add(g1);
        Group result = dao.find(Group.class, g1.getId());
        assertEquals(result.getId(), g1.getId());

    }

    @Test
    public void testAddPerson(){
        dao.add(p1);
        Person result = dao.find(Person.class,p1.getId());
        assertEquals(p1.getId(),result.getId());
    }


    @Test
    public void testUpdate(){
        dao.add(g2);
        dao.add(p2);

        p2.setName("Toto");
        dao.update(p2);

        Person result = dao.find(Person.class,p2.getId());
        assertEquals(result.getName(),"Toto");

    }

    @Test
    public void testFindAll(){
        //TODO
    }

}
