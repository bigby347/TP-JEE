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
import java.util.List;
import java.util.Random;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfiguration.class)

@ComponentScan(basePackages = ("annuaireapp.dao"))
public class DataInsert {

    @Autowired
    IDao dao;

    String names[] = {"Rian", "Jordin", "Petit", "Lothbrock", "McGill", "Goodman", "White", "Pinkman", "Saeba", "Queen", "Wayne", "Teller", "Hunt", "DeSanta", "Croft"};
    String firstNames[] = {"Bob", "Jacques", "Michel", "Gabriel", "Lucas", "Louis", "Pierre", "Jean", "Victor", "Paul", "Antoine", "Nathan"};

    String groupNames[] = {"ILD", "FSI", "IAAA", "GIG", "IMD"};
    String cursus[] = {"L1", "L2", "L3", "M1", "M2"};
    String year[] = {"2015/2016", "2016/2017", "2017/2018", "2018/2019", "2019/2020", "2020/2021"};

    @Test
    public void personsInsert() {
        List<Group> groupList = groupsInsert();
        Random random = new Random();
        for (int j = 0; j < names.length; j++) {
            for (int i = 0; i < firstNames.length; i++) {
                int groupId = random.nextInt(groupList.size());
                Person p = new Person();

                p.setName(names[j]);
                p.setFirstName(firstNames[i]);
                p.setEmail(names[j] + firstNames[i] + i + j + "@gmail.com");
                p.setBirthDay("1997-01-01");
                p.setWebsite("www."+names[j] + firstNames[i] + i + j+ ".fr");
                p.setPassword(names[j] + firstNames[i]  + i + j);
                p.setGroupe(groupList.get(groupId));
                dao.add(p);

                for (int k = 0; k < firstNames.length; k++) {
                    groupId = random.nextInt(groupList.size());
                    Person p2 = new Person();

                    p2.setName(names[j]);
                    p2.setFirstName(firstNames[i] + "-" + firstNames[k]);
                    p2.setEmail(names[j] + firstNames[i] + "-" + firstNames[k] + i + j + k + "@gmail.com");
                    p2.setBirthDay("1992-08-05");
                    p2.setWebsite("www." + names[j] + firstNames[i] + firstNames[k] + i + j + k + ".fr");
                    p2.setPassword(names[j] + firstNames[i] + firstNames[k] + i + j + k);
                    p2.setGroupe(groupList.get(groupId));
                    dao.add(p2);
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
