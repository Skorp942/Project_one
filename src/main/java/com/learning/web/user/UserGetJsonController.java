package com.learning.web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.dao.UserDao;
import com.learning.entity.User;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserGetJsonController {

    private final UserDao userDao;
    private ObjectMapper objectMapper;

    public UserGetJsonController(UserDao userDao, ObjectMapper objectMapper) {this.userDao = userDao;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/user/getUser.json")
    public User getUserById(@RequestParam Integer userId) {
        return userDao.getUserByUserId(userId);
    }
}
