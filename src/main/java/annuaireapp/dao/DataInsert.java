package annuaireapp.dao;

import annuaireapp.models.Group;
import annuaireapp.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)

@ComponentScan(basePackages = ("annuaireapp.dao"))
public class DataInsert {

    @Autowired
    IDao dao;

    String names[] = {"Rian", "Jordin", "Petit", "Lothbrock", "McGill", "Goddman", "White", "Pinkman", "Saeba", "Queen", "Wayne", "Teller", "Hunt", "DeSanta", "Croft"};
    String firstNames[] = {"Bob", "Jacques", "Michel", "Ryo", "Jackson", "Saul", "James", "Walter", "Bruce", "Thomas", "Alfred", "Ethan",
            "Jean", "Pierre", "Jesse", "Oliver", "Benjamin", "Claire", "Jade", "Elise", "Selena", "Kate", "Emma", "Yasmine", "Anne", "Lara"};

    String groupNames[] = {"ILD", "FSI", "IAAA", "GIG", "IMD", "STAPS", "GEA", "GEII", "BIO"};
    String cursus[] = {"L1", "L2", "L3", "M1", "M2"};
    String year[] = {"2015/2016", "2016/2017", "2017/2018", "2018/2019", "2019/2020", "2020/2021"};

    @Test
    public void personsInsert() {
        List<Group> groupList = groupsInsert();
        Random random = new Random();
        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < firstNames.length; i++) {
                for (int k = 0; k < firstNames.length; k++) {
                    int groupId = random.nextInt(groupList.size());
                    Person p = new Person();

                    p.setName(names[j]);
                    p.setFirstName(firstNames[i]+" "+firstNames[k]);
                    p.setEmail(names[j] + firstNames[i]+"-"+firstNames[k] + i + j + k + "@gmail.com");
                    p.setBirthDay("01-01-1997");
                    p.setWebsite(names[j] + firstNames[i]+"-"+firstNames[k] + i + j + k + ".fr");
                    p.setPassword(names[j] + firstNames[i]+"-"+firstNames[k] + i + j + k);
                    p.setGroup(groupList.get(groupId));
                    dao.add(p);
                }
            }
        }

    }

    public List<Group> groupsInsert() {
        List<Group> list = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < groupNames.length; i++) {
            for (int j = 0; j < cursus.length; j++) {
                for (int k = 0; k < cursus.length; k++) {
                    id++;
                    Group g = new Group();
                    String name = cursus[j] + " " + groupNames[i] + " " + year[k];
                    g.setId(id);
                    g.setName(name);

                    dao.add(g);
                    list.add(g);
                }
            }
        }
        return list;
    }

}
