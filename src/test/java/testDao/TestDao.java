package testDao;


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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)

@ComponentScan(basePackages = ("annuaireapp.dao"))
public class TestDao {


    @Autowired
    IDao dao;

    static DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    static Person p1, p2, p3,p4;
    static Group g1, g2,g3;

    @BeforeAll
    public static void beforeAll() {
        g1 = new Group(100000, "M1");
        g2 = new Group(200000, "M2");
        g3 = new Group(300000, "L3");

        p1 = new Person("Rian", "Jacques", "jacques.rian@gmail.com",
                "rian.com", "13-07-1942", "password");

        p2 = new Person("Jordin", "Michel", "jordin.michel@gmail.com",
                "jordin.com", "26-05-1995", "annuaire");

        p3 = new Person("Petit", "Pompe", "pompe.petit@gmail.com",
                "lilpump.com", "01-01-2020", "12345678");

        p4 = new Person("Test", "Email", "test.email@gmail.com",
                "test.com", "01-01-2020", "12345678");
        p1.setGroupe(g1);
        p2.setGroupe(g2);
        p3.setGroupe(g2);
        p4.setGroupe(g3);
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
        dao.update(g2);
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

    @Test
    public void testFindPersonsByname(){
        dao.update(g2);
        p3.setName("totob");
        dao.add(p3);

        Collection<Person> personList = dao.findPersonsByName("toto");
        System.out.println("lol");

        for (Person person :personList){
            assertTrue(person.getName().contains("toto"));
        }

    }

    @Test
    public void testFindByEmail(){
        dao.add(g3);
        dao.add(p4);
        Person p = dao.findPersonByEmail("test.email@gmail.com");
        assertEquals("test.email@gmail.com",p.getEmail());
    }

    @Test
    public void testFindByEmailNotFound(){
       Person p = dao.findPersonByEmail("test@gmail.com");
        assertNull(p);
    }

}
