package App.Controller;

import DAO.usersDAO;
import classes.users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class signinControl {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView signin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin");
        return modelAndView;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ModelAndView auth(@RequestParam(name = "user_id", required = true) int user_id,
                             @RequestParam(name = "passw", required = true) String passw) throws SQLException {
        usersDAO usersdao = new usersDAO();
        users user = usersdao.checkbyIDandPssw(user_id,passw);
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setViewName("signin");
            return modelAndView;
        }

        modelAndView.setViewName("userhome");
        modelAndView.addObject("is_admin", user.getIs_admin());
        modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView auth(@RequestParam(name = "name", required = true) String user_name,
                             @RequestParam(name = "contact_info", required = true) String contact_info) throws SQLException {
        usersDAO usersdao = new usersDAO();
        users user = new users();
        user.setIs_admin(false);
        user.setUser_name(user_name);
        user.setUser_contact_info(contact_info);
        usersdao.addUser(user);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("userhome");
        modelAndView.addObject("is_admin", user.getIs_admin());
        modelAndView.addObject("user_id", user.getUser_id());
        return modelAndView;
    }
}
