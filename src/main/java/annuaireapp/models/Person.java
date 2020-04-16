package annuaireapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NamedQueries({
        @NamedQuery(name = "Person.findAll", query = "Select p From Person p"),
        @NamedQuery(name = "Person.removeAll", query = "Delete From Person "),
        @NamedQuery(name = "Person.remove", query = "delete from Person p where p.id = :id")
})

@Entity(name = "Person")
@Table(name = "Person")
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
    private Group groupe;

    public Person(String name, String firstName,
                  String email, String website,
                  String birthDay, String password) {
        super();
        this.name = name;
        this.firstName = firstName;
        this.email =email;
        this.website =website;
        this.birthDay = stringToDate(birthDay);
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

    public void setBirthDay(String birthDay) {

        this.birthDay = stringToDate(birthDay);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Group getGroup() {
        return groupe;
    }

    public void setGroup(Group group) {
        this.groupe = group;
    }


    /*Fonction utile*/

    /* Tranforme une chaine de caratère de la forme (dd-MM-yyyy) en objet de type Date*/
    public static Date stringToDate(String date){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Date d = new Date();
        try {
            d=df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
