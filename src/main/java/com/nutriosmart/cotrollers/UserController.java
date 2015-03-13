package com.nutriosmart.cotrollers;

import com.nutriosmart.dao.UserDAO;
import com.nutriosmart.db.DBHandler;
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
    @RequestMapping("/user")
    public User userMethod(@RequestParam(value="userName") String userName, @RequestParam(value="password") String password, @RequestParam(value="email") String email) throws UnknownHostException {
        UserDAO userDAO = new UserDAO(DBHandler.setUpMongDB(DBHandler.mongoURIString, DBHandler.dbName));
        User user = userDAO.addUser(userName, password, email);
        return user;
    }
}
