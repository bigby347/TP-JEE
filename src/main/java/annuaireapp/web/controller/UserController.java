package annuaireapp.web.controller;

import annuaireapp.business.IDirectoryManager;
import annuaireapp.business.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
@SessionAttributes("user")
public class UserController {

    protected final Log logger = LogFactory.getLog(getClass());
    @Autowired
    IDirectoryManager manager;

    @Autowired
    User user;

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        logger.info("Home page loaded");
        return "home";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        logger.info("Login page loaded");

        return "login";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ModelAndView logout(){
        logger.info("Logout user");
        user = manager.logout(user);
        return new ModelAndView("index","user",user);
    }

    @RequestMapping(value = "/connection")
    public ModelAndView connection(
            @RequestParam(value = "inputEmail",required = true) String inputEmail,
            @RequestParam(value = "inputPassword",required = true) String inputPassword
    ){
        if (manager.login(user,inputEmail,inputPassword)){
            logger.info("Login user with id:" + user.getId());
            return new ModelAndView("redirect:home","user",user);
        } else {
            logger.info("Invalid user or password");
            return new ModelAndView("redirect:login");
        }
    }
    @ModelAttribute("user")
    public User newUser() {
        return user;
    }
}
