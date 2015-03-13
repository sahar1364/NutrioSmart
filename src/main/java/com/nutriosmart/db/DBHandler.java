package com.nutriosmart.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.DB;

import java.net.UnknownHostException;

/**
 * Created by sahar on 3/13/15.
 */
public class DBHandler {
    public static final String mongoURIString = "mongodb://localhost";
    public static final String dbName = "nutrio";

    public static DB setUpMongDB(String mongoURIString, String dbName) throws UnknownHostException {
        final MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoURIString));
        return mongoClient.getDB(dbName);
    }

}
