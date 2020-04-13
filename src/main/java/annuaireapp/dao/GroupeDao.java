package annuaireapp.dao;

import annuaireapp.models.Group;
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
public class GroupeDao implements IDao<Group> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @Override
    public Optional<Group> get(long id) {
        Group g = em.find(Group.class,id);
        return Optional.ofNullable(g);
    }

    @Override
    public List<Group> findAll() {
        List<Group> result = em.createNamedQuery("Group.findAll").getResultList();
        if(result.isEmpty()) System.err.println("Groups is empty");
        return result;
    }

    @Override
    public void add(Group group) {
        em.persist(group);
    }

    @Override
    public void update(Group group) {
        em.merge(group);
    }

    @Override
    public void remove(Group group) {
        em.remove(group);
    }
}
