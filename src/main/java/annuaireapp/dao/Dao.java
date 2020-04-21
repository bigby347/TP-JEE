package annuaireapp.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import annuaireapp.models.Group;
import annuaireapp.models.Person;

@Service
@Repository("Dao")
@Transactional
public class Dao implements annuaireapp.dao.IDao {

    protected final Log logger = LogFactory.getLog(getClass());

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    // start service
    @PostConstruct
    public void start() {
        System.out.println("Start " + this);
    }

    // stop service
    @PreDestroy
    public void stop() {
        System.out.println("Stop " + this);
    }

    @Override
    public Collection<Group> findAllGroups() {
        return em.createNamedQuery("Group.findAll", Group.class).getResultList();
    }

    @Override
    public Collection<Person> findAllPersons() {
        return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

    @Override
    public Collection<Person> findAllPersonsInGroup(Integer idGroup) {
        Group g = em.find(Group.class,idGroup);
        return em.createNamedQuery("Person.findInGroup", Person.class).setParameter("ownGroup",g).getResultList();
    }

    @Override
    public Collection<Person> findPersonsByName(String research) {
        Collection<Person> collection = new ArrayList<>();
        try {
            collection =em.createNamedQuery("Person.findByName", Person.class).setParameter("research", "%" + research + "%").getResultList();
        }catch (Exception e){
            logger.info("Entity not Found -> findPersonsByName()");
            collection = null;
        }

        return collection;
    }

    @Override
    public Collection<Group> findGroupsByName(String research) {
        Collection<Group> collection = new ArrayList<>();
        try {
            collection = em.createNamedQuery("Group.findByName", Group.class).setParameter("research", "%"+ research + "%").getResultList();
        }catch (Exception e){
            logger.info("Entity not Found -> findGroupsByName()");
            collection = null;
        }

        return collection;
    }

    @Override
    public Person findPersonByEmail(String email) {
        Person p = new Person();
        try {
            p = em.createNamedQuery("Person.findByEmail",Person.class).setParameter("email",email).getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            p=null;
        }
        return p;
    }

    @Override
    public <T> T find(Class<T> clazz, Object id) {
        return em.find(clazz, id);
    }

    @Override
    public <T> void add(T t) {
        em.persist(t);
        logger.info("Entity" + t + "added");
    }

    @Override
    public <T> void update(T t) {
        em.merge(t);
        logger.info("Entity" + t + "updated");
    }


    @Override
    public <T> void remove(T t) {
        em.remove(t);
        logger.info("Entity" + t + "removed");
    }

    public void clear() {
        em.clear();
    }
}
