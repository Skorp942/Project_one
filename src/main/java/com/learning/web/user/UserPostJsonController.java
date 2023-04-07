package com.learning.web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.dao.UserDao;
import com.learning.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserPostJsonController {

    @Autowired
    private UserEditValidator userEditValidator;
    private final UserDao userDao;
    private ObjectMapper objectMapper;

    public UserPostJsonController(UserDao userDao) {this.userDao = userDao;}

    @PostMapping("/user/postUser.json")
    @ResponseBody
    public String save(@RequestBody  User user) {
        String returnText;
        //userEditValidator.validate(user);
        userDao.saveOrUpdate(user);
        returnText = "Добавлено";
        return returnText;
    }
}
