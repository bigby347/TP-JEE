package annuaireapp.dao;

import annuaireapp.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PersonDao implements IDao<Person> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @Override
    public Optional<Person> get(long id) {
        Person p = em.find(Person.class,id);
        return Optional.ofNullable(p);
    }

    @Override
    public List<Person> findAll() {
        List<Person> result = em.createNamedQuery("Person.findAll").getResultList();
        if(result.isEmpty()) System.err.println("Persons is empty");
        return result;
    }

    @Override
    public void add(Person person) {
        em.persist(person);
        System.err.println("Person saved.");
    }

    @Override
    public void update(Person person) {
        em.merge(person);
        System.err.println("Person updated.");
    }

    @Override
    public void remove(Person person) {
        em.remove(person);
        System.err.println("Person removed.");
    }
}
