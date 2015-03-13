package com.nutriosmart.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.DB;

import java.net.UnknownHostException;

/**
 * Created by sahar on 3/13/15.
 */
public class MongoDB {
    private static final String mongoURIString = "mongodb://localhost";
    private static final String dbName = "nutrio";
    private static MongoClient mongoClient = null;
    private static MongoDB mongoDB = null;

    private MongoDB()throws UnknownHostException {
        if (mongoClient == null) {
            mongoClient = new MongoClient(new MongoClientURI(mongoURIString));
        }
    }
    public static MongoDB getInstance() {
        if (mongoDB == null) {
            try {
                mongoDB = new MongoDB();
            } catch (UnknownHostException uhe) {

            }
        }
        return mongoDB;
    }

    public DB setUpMongoDB() {
        return mongoClient.getDB(dbName);
    }

}
