package annuaireapp.business;

import annuaireapp.models.Group;
import annuaireapp.models.Person;

import java.util.Collection;

public interface IDirectoryManager {
    // créer un utilisateur anonyme
    User newUser();

    // chercher une personne
    Person findPerson(User user,Integer personId);

    // chercher un groupe
    Group findGroup(Integer groupId);

    // chercher les personnes d'un groupe
    Collection<Person> findAllPersonsInGroup(User user, Integer groupId);

    Collection<Person> findAllPersons(User user);

    Collection<Group> findAllGroups();

    Collection<Group> findGroupsByName(User user, String research);

    Collection<Person> findPersonsByName(User user, String research);

    Person getUser(String email);

    // identifier un utilisateur
    boolean login(User user, String userEmail, String password);

    // oublier l'utilisateur
    User logout(User user);

    // enregistrer une personne
    void savePerson(User user, Person p);

    public boolean resetPassword(String email);
}
