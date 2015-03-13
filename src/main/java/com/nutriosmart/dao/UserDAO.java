package com.nutriosmart.dao;

import com.google.common.base.Strings;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DB;
import com.mongodb.MongoException;
import com.nutriosmart.pojos.User;

/**
 * Created by sahar on 3/13/15.
 */
public class UserDAO {
    private final DBCollection userCollection;
    public UserDAO(DB nutrioDB) {
        userCollection = nutrioDB.getCollection("users");
    }
    public User addUser(String userName, String password, String email) {
        if (Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password) || Strings.isNullOrEmpty(password))
            return null;
        User user = new User(userName, password, email);
        userCollection.insert(user);
        return user;
    }
}
