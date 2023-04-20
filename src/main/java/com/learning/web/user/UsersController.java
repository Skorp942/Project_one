package com.learning.web.user;

import com.learning.dao.UserDao;
import com.learning.dto.JsonUserDto;
import com.learning.dto.JsonUsersDto;
import com.learning.util.paginated.SimplePaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @RequestMapping(value="/user/updateListUsers.html", method=RequestMethod.POST)
    public String sendHtmlFragment(
            Model model,
            @ModelAttribute("UsersController.command") UsersForm command
    ){
        SimplePaginatedList list = userDao.getUsers(command);
        model.addAttribute("list", list);
        model.addAttribute("command", command);
        return "/user/fragmentListUsers";
    }
}
