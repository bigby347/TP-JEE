package annuaireapp.dao;

import annuaireapp.models.Groupe;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class GroupeDao implements IDao<Groupe> {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    @Override
    public Optional<Groupe> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Groupe> findAll() {
        return null;
    }

    @Override
    public void add(Groupe groupe) {
        em.persist(groupe);
    }

    @Override
    public void update(Groupe groupe) {
    }

    @Override
    public void remove(Groupe groupe) {

    }
}
