package annuaireapp.business;

import annuaireapp.dao.IDao;
import annuaireapp.dao.SpringConfiguration;
import annuaireapp.models.Group;
import annuaireapp.models.Person;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;

import java.util.Collection;

@Service
@ContextConfiguration(classes = SpringConfiguration.class)
public class DirectoryManager implements IDirectoryManager {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    IDao dao;

    @Autowired
    IEmailManager emailManager;

    public DirectoryManager() {
    }

    @Override
    public User newUser() {
        User user = new User();
        return user;
    }

    @Override
    public Person findPerson(User user,Integer personId) {
        Person p = dao.find(Person.class,personId);
        if(user.getId() != p.getId()){
            p.setPassword(null);
            if (user.getId()==0) {
                p.setEmail(null);
                p.setBirthDay(null);
            }
        }
        return p;
    }

    @Override
    public Group findGroup(Integer groupId) {
        return dao.find(Group.class,groupId);
    }

    @Override
    public Collection<Person> findAllPersons(User user) {
        return dao.findAllPersons();
    }

    @Override
    public Collection<Group> findAllGroups() {
        return dao.findAllGroups();
    }

    @Override
    public Collection<Person> findAllPersonsInGroup(User user, Integer groupId) {
        Collection<Person> persons = dao.findAllPersonsInGroup(groupId);
        for(Person person :persons) {
            person.setPassword(null);
            /*Si aucun utilisateur n'est connecté on ne renvoie pas certaine info*/
            if(user.getId()==0){
                person.setBirthDay(null);
                person.setEmail(null);
            }
        }

        return persons;
    }

    @Override
    public Collection<Group> findGroupsByName(User user, String research) {
        return dao.findGroupsByName(research);
    }

    @Override
    public Collection<Person> searchPersons(User user, String name, String firstname) {
        Collection<Person> persons = dao.searchPersons(name,firstname);
        for(Person person :persons) {
            person.setPassword(null);
            /*Si aucun utilisateur n'est connecté on ne renvoie pas certaine info*/
            if(user.getId()==0){
                person.setBirthDay(null);
                person.setEmail(null);
            }
        }
        return persons;
    }

    @Override
    public Person getUser(String email) {
        Person p = dao.findPersonByEmail(email);
        return p;
    }

    @Override
    public boolean login(User user, String userEmail, String password) {
        Person person = dao.findPersonByEmail(userEmail);

        if(person != null){
            if(person.getPassword().equals(password)){
                user.setId(person.getId());
                user.setName(person.getName());
                user.setFirstName(person.getFirstName());
                logger.info("User with id = " + user.getId() + " is connected");
                return true;
            }
        }

        return false;
    }

    @Override
    public User logout(User user) {
        logger.info("User with id = " + user.getId() + " is disconnected");
        user = newUser();
        return user;
    }

    @Override
    public void savePerson(User user, Person p) {
        if (user.getId().equals(p.getId())) {
            dao.update(p);
        }
    }

    @Override
    public boolean resetPassword(String email) {
        Person person = dao.findPersonByEmail(email);
        if(person != null){
            String tempPassword = RandomStringUtils.randomAlphanumeric(10);
            person.setPassword(tempPassword);
            dao.update(person);
            String emailBody = String.format(emailManager.getTemplate().getText(),
                    person.getName() + " " + person.getFirstName(),
                    person.getEmail(),
                    tempPassword);
            emailManager.sendMail(person.getEmail(),"Reset Password",emailBody);
            return true;
        }
        return false;
    }
}
