package annuaireapp.web.controller;

import annuaireapp.business.IDirectoryManager;
import annuaireapp.business.User;
import annuaireapp.models.Group;
import annuaireapp.models.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
@RequestMapping("/person")
@SessionAttributes("user")
public class PersonController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    IDirectoryManager manager;

    @Autowired
    User user;

    @ModelAttribute("user")
    public User newUser() {
        return user;
    }

    @ModelAttribute("persons")
    Collection<Person> persons(){ return manager.findAllPersons(user);}

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(){
        logger.info("Persons List Page loaded");
        return new ModelAndView("personList","persons",persons());
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable("id") Integer id) {
        logger.info("Show person with id =" + id);
        return new ModelAndView("person", "person", manager.findPerson(user, id));
    }

    //Charge la vue d'édition d'une personnne
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") Integer id) {
        Person p = manager.findPerson(user, id);
        if (user.getId().equals(p.getId())) {
            return new ModelAndView("personEdit", "person", p);
        }
        return new ModelAndView("redirect:/person/show/" + id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String save(@ModelAttribute("person") @Valid Person person,
                       @RequestParam(value = "groupId", required = false) Integer groupId,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "personEdit";
        }
        if (groupId != null) {
            Group group = manager.findGroup(groupId);
            if (group != null) {
                logger.info("Group " + groupId + " found");
            }
            person.setGroupe(group);
            manager.savePerson(user, person);
        }

        return "redirect:/person/show/" + person.getId();
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }


}
