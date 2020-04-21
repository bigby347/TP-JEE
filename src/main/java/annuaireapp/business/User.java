package annuaireapp.business;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component()
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String firstName;

    public User(Integer id, String name, String firstName) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
    }

    public User(){
        this.id = 0;
        this.name = "Anonymous";
        this.firstName = "Anonymous";
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
