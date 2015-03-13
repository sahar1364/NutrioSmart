package com.nutriosmart.cotrollers;

import com.nutriosmart.dao.UserDAO;
import com.nutriosmart.db.MongoDB;
import com.nutriosmart.pojos.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;

/**
 * Created by sahar on 3/13/15.
 */
@RestController
public class UserController {
    private final UserDAO userDAO = new UserDAO(MongoDB.getInstance().setUpMongoDB());

    @RequestMapping("/signup")
    public User signup(@RequestParam(value="userName") String userName, @RequestParam(value="password") String password, @RequestParam(value="email") String email,
                           @RequestParam(value="firstName", defaultValue="") String firstName, @RequestParam(value="lastName", defaultValue="") String lastName) throws UnknownHostException {
        User user = userDAO.addUser(userName, password, email);
        return user;
    }

    @RequestMapping("/login")
    public User login(@RequestParam(value="userName") String userName, @RequestParam(value="password") String password) {
        User user = userDAO.loginUser(userName, password);
        return user;
    }
}
