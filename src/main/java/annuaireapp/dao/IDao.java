package annuaireapp.dao;

import java.util.Collection;

import annuaireapp.models.Group;
import annuaireapp.models.Person;

public interface IDao {


	public Collection<Group> findAllGroups();

	public Collection<Person> findAllPersons();

	public Collection<Person>  findAllPersonsInGroup(Integer idGroup);

	public Collection<Person> findPersonsByName(String research);

	public Collection<Group> findGroupsByName(String research);

	public Person findPersonByEmail(String email);

	public <T> T find(Class<T> clazz,Object id);
	public <T> void add(T t);
	public <T> void update(T t);

	public <T> void remove(T t);
	//public <T> Collection<T> findByStringProperty(Class<T> clazz, String propertyName, String propertyValue);
}