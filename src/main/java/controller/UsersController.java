package controller;

import dao.UsersDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    @Qualifier("UsersDao")
    private UsersDao dao;
    public static final int USERS_PER_PAGE = 10;

    @RequestMapping(value = "/users.html", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        List<User> users = dao.getAll();
        if (users.size() <= USERS_PER_PAGE) {
            ModelAndView modelAndView = new ModelAndView("Users");
            modelAndView.addObject("allUsers", dao.getAll());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/users/page/1");
        }
    }

    @RequestMapping(value = "/users/page/{pageId}")
    public ModelAndView getUsersPerPage(@PathVariable("pageId") int pageId) {
        ModelAndView modelAndView = new ModelAndView("UsersPerPage");
        List<User> users = dao.getAll();
        int numberOfPages = (int) Math.ceil((double) users.size() / (double) USERS_PER_PAGE);
        List<User> usersPerPage = dao.getUsersPerPage((pageId * USERS_PER_PAGE - USERS_PER_PAGE), USERS_PER_PAGE);
        modelAndView.addObject("numberOfPages", numberOfPages);
        modelAndView.addObject("usersPerPage", usersPerPage);
        return modelAndView;
    }

    @RequestMapping(value = "/usersSearch.html", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam("name") String name) {
        List<User> usersFilteredByName = dao.getByName(name);
        if (usersFilteredByName.size() == 0) {
            ModelAndView modelAndView = new ModelAndView("NoUsersFound");
            modelAndView.addObject("name", name);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("UsersSearch");
            modelAndView.addObject("foundUsers", dao.getByName(name));
            return modelAndView;
        }
    }

    @RequestMapping(value = "/createUser.html", method = RequestMethod.GET)
    public ModelAndView createUser() {
        return new ModelAndView("CreateUser");
    }

    @RequestMapping(value = "/createUser.html", method = RequestMethod.POST)
    public String userAdded(@RequestParam("name") String name, @RequestParam("age") String age,
                            @RequestParam(value = "isAdmin", defaultValue = "false") boolean isAdmin) {
        User user = new User();
        user.setName(name);
        user.setAge(Integer.parseInt(age));
        user.setAdmin(isAdmin);
        dao.saveUser(user);
        return "redirect:/users.html";
    }

    @RequestMapping(value = "/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        dao.deleteUser(userId);
        return "redirect:/users.html";
    }

    @RequestMapping(value = "/editUser/{userId}")
    public ModelAndView editUser(@PathVariable("userId") Integer userId) {
        ModelAndView modelAndView = new ModelAndView("EditUser");
        User user = dao.getById(userId);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value = "/editUser/{userId}", method = RequestMethod.POST)
    public String editDaoUser(@PathVariable("userId") Integer userId, @RequestParam("name") String name,
                              @RequestParam("age") String age,
                              @RequestParam(value = "isAdmin",defaultValue = "false") boolean isAdmin) {
        User user = dao.getById(userId);
        user.setName(name);
        user.setAge(Integer.parseInt(age));
        user.setAdmin(isAdmin);
        dao.updateUser(user);
        return "redirect:/users.html";
    }
}