package annuaireapp.dao;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Primary;
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
		return em.createNamedQuery("Group.findAll",Group.class).getResultList();
	}

	@Override
	public Collection<Person> findAllPerson() {
		return em.createNamedQuery("Person.findAll",Person.class).getResultList();
	}

	@Override
	public Collection<Person> findAllPersonsInGroup(long idGroup) {
		return null;
	}

	@Override
	public <T> T find(Class<T> clazz,Object id) {
		return em.find(clazz,id);
	}

	@Override
	public <T> void add(T t) {
    	em.persist(t);
    	logger.info("Entity"+ t +"added");
	}

	@Override
	public <T> void update(T t) {
		em.merge(t);
		logger.info("Entity"+ t +"updated");
	}


	@Override
	public <T> void remove(T t) {
		em.remove(t);
		logger.info("Entity"+ t +"removed");
	}

	public void clear(){
		em.clear();
	}
}
