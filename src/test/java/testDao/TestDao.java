package testDao;


import annuaireapp.dao.IDao;

import annuaireapp.dao.SpringConfiguration;
import annuaireapp.models.Group;
import annuaireapp.models.Person;


import org.junit.jupiter.api.*;
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
    static Group g1,g2,g3,g4;

    @BeforeAll
    public static void beforeAll() {
    }

    @AfterAll
    public static void afterAll() {
    }

    @BeforeEach
    void setUp() {
        System.out.println("debut du test");
        g1 = new Group(100000, "M1");
        g2 = new Group(200000, "M2");
        g3 = new Group(300000, "GROUPE 3");
        g4 = new Group(400000, "GROUPE 4");

        p1 = new Person("Rian", "Jacques", "jacques.rian@gmail.com",
                "rian.com", "1942-07-13-", "password");

        p2 = new Person("Jordin", "Michel", "jordin.michel@gmail.com",
                "jordin.com", "1995-26-05", "annuaire");

        p3 = new Person("Petit", "Pompe", "pompe.petit@gmail.com",
                "lilpump.com", "2000-01-01", "12345678");

        p4 = new Person("Test", "Email", "test.email@gmail.com",
                "test.com", "01-01-2020", "12345678");
        p1.setGroupe(g1);
        p2.setGroupe(g2);
        p3.setGroupe(g3);
        p4.setGroupe(g4);
        dao.add(g1);
        dao.add(g2);
        dao.add(g3);
        dao.add(g4);
    }

    @AfterEach
    void tearDown() {
        dao.remove(g1);
        dao.remove(g2);
        dao.remove(g3);
        dao.remove(g4);
        System.out.println("fin du test");
    }


    private boolean containsPerson (Person person, Collection<Person> personCollection) {
        for (Person p : personCollection) {
            if (p.getId().equals(person.getId()))
                return true;
        }
        return false;
    }

    private boolean containsGroup(Group group, Collection<Group> groupsCollection) {
        for (Group g : groupsCollection) {
            if (g.getId().equals(group.getId()))
                return true;
        }
        return false;
    }

    @Test
    public void testAddGroup() {
        Group g = new Group(500000, "TestAddGroup");
        dao.add(g);
        Group result = dao.find(Group.class, g.getId());
        assertEquals(g.getId(),result.getId());
        dao.remove(g);
    }

    @Test
    public void testAddPerson(){
        Person person = new Person("Test", "AddPerson", "test.email@gmail.com",
                "test.com", "01-01-2020", "12345678");
        person.setGroupe(g1);
        dao.add(person);
        Person result = dao.find(Person.class,person.getId());
        assertEquals(person.getId(),result.getId());
        dao.remove(person);
    }

    @Test
    public void testUpdate(){
        dao.add(p2);

        p2.setName("Toto");
        dao.update(p2);

        Person result = dao.find(Person.class,p2.getId());
        assertEquals(result.getName(),"Toto");
        dao.remove(p2);
    }

    @Test
    public void testFindAllGroups(){
        Collection<Group> groupCollection = dao.findAllGroups();

        assertTrue(containsGroup(g2,groupCollection));
        assertTrue(containsGroup(g3,groupCollection));
    }

    @Test
    public void testFindAllPersons(){
        dao.add(p2);
        dao.add(p3);
        Collection<Person>personList = dao.findAllPersons();

        assertTrue(containsPerson(p2,personList));
        assertTrue(containsPerson(p3,personList));


        dao.remove(p3);
        dao.remove(p2);
    }

    @Test
    public void testFindPersonsByname(){
        p2.setName("totoa");
        p3.setName("totob");
        dao.add(p3);
        dao.add(p2);

        Collection<Person> personList = dao.searchPersons("toto","");

        for (Person person :personList){
            assertTrue(person.getName().contains("toto"));
        }
        dao.remove(p3);
        dao.remove(p2);
    }

    @Test
    public void testFindPersonsByFirstName(){
        p2.setFirstName("totoa");
        p3.setFirstName("totob");
        dao.add(p3);
        dao.add(p2);

        Collection<Person> personList = dao.searchPersons("","toto");

        for (Person person :personList){
            assertTrue(person.getFirstName().contains("toto"));
        }
        dao.remove(p3);
        dao.remove(p2);
    }

    @Test
    public void testFindPersonsByCompleteName(){
        p2.setName("toto");
        p3.setName("toto");
        p2.setFirstName("tata");
        dao.add(p3);
        dao.add(p2);

        Collection<Person> personList = dao.searchPersons("toto","tata");
        assertTrue(containsPerson(p2,personList));
        assertFalse(containsPerson(p3,personList));
        dao.remove(p3);
        dao.remove(p2);
    }

    @Test
    public void testFindByEmail(){
        dao.add(p4);
        Person p = dao.findPersonByEmail("test.email@gmail.com");
        assertEquals("test.email@gmail.com",p.getEmail());
        dao.remove(p4);
    }

    @Test
    public void testFindByEmailNotFound(){
       Person p = dao.findPersonByEmail("test@gmail.com");
       assertNull(p);
    }

    @Test
    public void testFindGroupsByName(){
        Collection<Group> groups = dao.findGroupsByName("GROUPE");

        for (Group g :groups){
            assertTrue(g.getName().contains("GROUPE"));
        }
    }

    @Test
    public void testFindGroupsByNameNotFound(){

        Collection<Group> groups = dao.findGroupsByName("test");

        for (Group g :groups){
            assertFalse(g.getName().contains("test"));
        }

    }

    @Test
    public void testFindPersonsInGroup(){
        p4.setGroupe(g3);
        dao.add(p3);
        dao.add(p4);

        Collection<Person> persons = dao.findAllPersonsInGroup(300000);
        assertTrue(containsPerson(p3,persons));
        assertTrue(containsPerson(p4,persons));

        dao.remove(p3);
        dao.remove(p4);
    }

}
