package annuaireapp.dao;

import annuaireapp.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.Optional;

public class PersonDao implements IDao<Person> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @Override
    public Optional<Person> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public void add(Person person) {
        em.persist(person);

    }

    @Override
    public void update(Person person, String[] params) {

    }

    @Override
    public void remove(Person person) {

    }
}
