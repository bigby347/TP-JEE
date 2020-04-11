package annuaireapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;

@Entity(name = "Person")
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull
    @Basic(optional = false)
    @Column(name = "last_name", length = 100)
    private String lastName;

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
    @JoinColumn(name = "group")
    private Group group;

    public Person(String lastName, String firstName,
                  String email, String website,
                  Date birthDay, String password) {
        super();
        this.lastName = lastName;
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
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", birthDay=" + birthDay +
                ", password='" + password + '\'' +
                ", group=" + group +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
