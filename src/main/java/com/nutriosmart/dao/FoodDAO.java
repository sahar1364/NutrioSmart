package com.nutriosmart.dao;

import com.google.common.base.Strings;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.nutriosmart.pojos.Food;
import com.nutriosmart.pojos.User;

/**
 * Created by sahar on 3/15/15.
 */
public class FoodDAO {
    private final DBCollection foodCollection;

    public FoodDAO(DB nutrioDB) {
        foodCollection = nutrioDB.getCollection("users");
    }
    public Food addFood(String description, String manufacturer, String foodGroup) {
        if (Strings.isNullOrEmpty(description))
            return null;
        Food food = new Food(description, manufacturer, foodGroup);
        foodCollection.insert(food);
        return food;
    }

}
