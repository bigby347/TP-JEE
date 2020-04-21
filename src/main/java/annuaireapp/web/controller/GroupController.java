package annuaireapp.web.controller;

import annuaireapp.business.IDirectoryManager;
import annuaireapp.business.User;
import annuaireapp.models.Group;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/group")
@SessionAttributes("user")
public class GroupController {

    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    IDirectoryManager manager;

    @Autowired
    User user;

    @ModelAttribute("user")
    public User newUser() {
        return user;
    }

    @ModelAttribute("groups")
    public Collection<Group> groups(){
        return manager.findAllGroups();
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(){
        logger.info("Groups List Page loaded");

        return new ModelAndView("groupList","groups",groups());
    }

    @RequestMapping(value = "/show/{id}")
    public ModelAndView show(@PathVariable("id") Integer id){
        logger.info("Group show Page loaded");
        ModelAndView mav = new ModelAndView("groupShow");
        mav.addObject("group",manager.findGroup(id));
        mav.addObject("persons",manager.findAllPersonsInGroup(user,id));
        return mav;
    }
}
