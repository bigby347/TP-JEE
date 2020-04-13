package annuaireapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "Group.findAll", query = "Select g From Group g"),
        @NamedQuery(name = "Group.findById", query = "Select g From Group g where g.id = :id"),
        @NamedQuery(name = "Group.removeAll", query = "Delete From Group"),
        @NamedQuery(name = "Group.remove", query = "delete from Group g where g.id = :id")
})


@Entity(name = "Group")
public class Group implements Serializable {

    @NotNull
    @Id
    private Integer id;

    @NotNull
    @Basic(optional = false)
    @Column(name = "name", length = 255)
    private String name;


    @OneToMany(//
            cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE },
            fetch = FetchType.LAZY, mappedBy = "group")
    private Set<Person> persons;

    public Group() {
        super();
    }

    public Group(Integer id, String name){
        this.id = id;
        this.name= name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", persons=" + persons.toString() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person p){
        if(persons == null){
            persons = new HashSet<>();
        }
        p.setGroup(this);
        persons.add(p);
    }
}
