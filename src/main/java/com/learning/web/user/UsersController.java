package com.learning.web.user;

import com.learning.dao.UserDao;
import com.learning.entity.User;
import com.learning.util.paginated.SimplePaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ulyanov on 29.09.16.
 */

@Controller
@SessionAttributes(names = "UsersController.command")
public class UsersController {

    @Autowired private UserDao userDao;

    @ModelAttribute("UsersController.command")
    public UsersForm getCommand(){
        return new UsersForm();
    }

    @RequestMapping("/user/users.html")
    public String searchUsers(
            Model model,
            @ModelAttribute("UsersController.command") UsersForm command
    ){
        SimplePaginatedList list = userDao.getUsers(command);
        model.addAttribute("list", list);
        model.addAttribute("command", command);
        return "user/users";
    }

    @RequestMapping("user/users.html?Username={username}")
    public String searchUser(
            Model model,
            @ModelAttribute("UsersController.command") UsersForm command,
            @PathVariable int username
    ){

        model.addAttribute("command", command);
        return "redirect:/user/users.html";
    }

    @RequestMapping(value = "/user/users.html", method = RequestMethod.POST, params = "_search")
    public String searchUser(
            Model model,
            @ModelAttribute("command") User command,
            BindingResult result
    ){
        model.addAttribute("command", command);
        return "user/users.html?Username=" + command.getUsername();
    }

    @RequestMapping("/user/userDelete.html")
    public String delUser(@RequestParam(required = false) Integer userId){
        if (userId!=null){
            userDao.deleteUser(userId);
        }
        return "redirect:/user/users.html";
    }

}
