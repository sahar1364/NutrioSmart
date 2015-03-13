package com.nutriosmart.pojos;

import com.mongodb.BasicDBObject;

/**
 * Created by sahar on 3/13/15.
 */
public class User extends BasicDBObject {
    private String userName;
    private String email;
    private String password;

    public User(String userName, String password, String email) {
        this.append("_id", userName);
        this.append("password", password);
        this.append("email", email);
    }
}
