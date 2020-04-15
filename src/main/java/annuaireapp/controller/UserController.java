package annuaireapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class UserController {

    protected final Log logger = LogFactory.getLog(getClass());


    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        logger.info("Home page");

        return "home";
    }

}
