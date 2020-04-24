package annuaireapp.web.controller;

import annuaireapp.business.IDirectoryManager;
import annuaireapp.business.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("")
@SessionAttributes("user")
public class UserController {

    protected final Log logger = LogFactory.getLog(getClass());

    private static String previouspage;

    @Autowired
    IDirectoryManager manager;

    @Autowired
    User user;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        logger.info("Home page loaded");
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        if (user.getId() != 0) return "home";
        logger.info("Login page loaded");
        previouspage = request.getHeader("Referer");
        logger.info(previouspage);

        return "login";
    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout() {
        logger.info("Logout user");
        user = manager.logout(user);
        return new ModelAndView("index", "user", user);
    }

    @RequestMapping(value = "/connection")
    public ModelAndView connection(
            @RequestParam(value = "inputEmail", required = true) String inputEmail,
            @RequestParam(value = "inputPassword", required = true) String inputPassword
    ) {
        if (user.getId() != 0) return new ModelAndView("redirect:login");
        if (manager.login(user, inputEmail, inputPassword)) {
            logger.info("Login user with id:" + user.getId());
            return new ModelAndView("redirect:" + previouspage, "user", user);
        } else {
            logger.info("Invalid user or password");

            return new ModelAndView("redirect:login");
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {
        logger.info("Login page loaded");
        return "search";
    }

    @RequestMapping(value = "/searchGroups")
    public ModelAndView searchGroups(
            @RequestParam(value = "inputGroupResearch", required = true) String inputResearch
    ) {
        return new ModelAndView("groupList", "groups", manager.findGroupsByName(user, inputResearch));
    }

    @RequestMapping(value = "/searchPersons")
    public ModelAndView searchPersons(
            @RequestParam(value = "inputResearchName", required = false) String inputName,
            @RequestParam(value = "inputResearchFirstName", required = false) String inputFirstName
    ) {
        return new ModelAndView("personList", "persons", manager.searchPersons(user, inputName,inputFirstName));
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String reset() {
        logger.info("Reset Password page loaded");
        if(user.getId() != 0){
            return "redirect:home";
        }else {
            return "resetPassword";
        }
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public ModelAndView resetPassword(
            @RequestParam(value = "inputEmail", required = true) String inputEmail) {
            if(manager.resetPassword(inputEmail)){
                return new ModelAndView("redirect:/resetPassword","emailSend","Email Sent!");
            } else {
                return new ModelAndView("redirect:/resetPassword","errorNotFound","This account does not exits !");
            }
    }

    @ModelAttribute("user")
    public User newUser() {
        return user;
    }
}
