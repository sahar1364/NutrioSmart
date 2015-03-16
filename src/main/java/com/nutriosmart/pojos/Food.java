package com.nutriosmart.pojos;

import com.mongodb.BasicDBObject;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sahar on 3/15/15.
 */
@RestController
public class Food extends BasicDBObject {
    private String description;
    private String manufacturer;
    private String foodGroup;

    public Food(String description, String manufacturer, String foodGroup) {
        this.description = description;
        this.manufacturer = manufacturer;
        this.foodGroup = foodGroup;
    }

}
