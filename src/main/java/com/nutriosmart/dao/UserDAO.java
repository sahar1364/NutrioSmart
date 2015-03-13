package com.nutriosmart.dao;

import com.google.common.base.Strings;
import com.mongodb.*;
import com.nutriosmart.pojos.User;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by sahar on 3/13/15.
 */
public class UserDAO {
    private final DBCollection userCollection;
    private Random random = new SecureRandom();

    public UserDAO(DB nutrioDB) {
        userCollection = nutrioDB.getCollection("users");
    }

    public User addUser(String userName, String password, String email) {
        if (Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password) || Strings.isNullOrEmpty(password))
            return null;
        String encodedPassoword = makePasswordHash(password, Integer.toString(random.nextInt()));
        User user = new User(userName, encodedPassoword, email);
        userCollection.insert(user);
        return user;
    }

    public User loginUser(String userName, String password) {
        if (Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password))
            return null;
        DBObject user = userCollection.findOne(new BasicDBObject("_id", userName));
        if (user == null)
            return null;
        String userPasswordInDB = (String)(user.get("password"));
        String encodedPassoword = makePasswordHash(password, userPasswordInDB.split(",")[1]);
        String email = (String)(user.get("email"));
        if (userPasswordInDB.equals(encodedPassoword)) {
            User newUser = new User(userName, password, email);
            return newUser;
        }
        return null;
    }

    //taken from MongoDB Inc. sample code
    private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return encoder.encode(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
    
}
