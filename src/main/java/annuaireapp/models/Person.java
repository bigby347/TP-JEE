package annuaireapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "Select p From Person p"),
        @NamedQuery(name = "Person.findById", query = "Select p From Person p where p.id = :id"),
        @NamedQuery(name = "Person.removeAll", query = "Delete From Person "),
        @NamedQuery(name = "Person.remove", query = "delete from Person p where p.id = :id")
})

@Entity(name = "Person")

@Table(name = "Person",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {
                        "id","name","first_name", "email", "website",
                        "birth_day","password","groupe"
                })
        })
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @NotNull
    @Basic(optional = false)
    @Column(name = "name", length = 100)
    private String name;

    @NotNull
    @Basic(optional = false)
    @Column(name = "first_name", length = 100)
    private String firstName;

    @NotNull
    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    @Basic(optional = false)
    @Column(name = "website")
    private String website;

    @NotNull
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
    private Date birthDay;

    @NotNull
    @Size(min = 8)
    @Column(name = "password")
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "groupe")
    private Groupe groupe;

    public Person(String name, String firstName,
                  String email, String website,
                  Date birthDay, String password) {
        super();
        this.name = name;
        this.firstName = firstName;
        this.email =email;
        this.website =website;
        this.birthDay = birthDay;
        this.password =password;
    }

    public Person(){
        super();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", birthDay=" + birthDay +
                ", password='" + password + '\'' +
                ", group=" + groupe +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String lastName) {
        this.name = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
}
