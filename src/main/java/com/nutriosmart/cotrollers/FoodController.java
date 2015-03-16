package com.nutriosmart.cotrollers;

import com.google.common.base.Strings;
import com.nutriosmart.dao.FoodDAO;
import com.nutriosmart.db.MongoDB;
import com.nutriosmart.pojos.Food;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by sahar on 3/15/15.
 */
@RestController
public class FoodController {

    private static final String USDAURL = "http://ndb.nal.usda.gov/ndb/foods";
    private final FoodDAO foodDAO= new FoodDAO(MongoDB.getInstance().setUpMongoDB());

    @RequestMapping("/foods")
    public Food foods(@RequestParam(value="description", defaultValue = "") String description, @RequestParam(value="foodGroup", defaultValue = "") String foodGroup,
                      @RequestParam(value="manufacturer", defaultValue = "") String manufacturer) throws MalformedURLException, IOException {
        Food food = null;
        String url = USDAURL;
        if (!Strings.isNullOrEmpty(description)) {
          url += "?qlookup=" + description;
        }
        if (!Strings.isNullOrEmpty(foodGroup)) {
            if (url.equals(USDAURL)) {
                url += "?fgcd=" + foodGroup;
            }
            else
                url += "&fgcd=" + foodGroup;
        }
        if (!Strings.isNullOrEmpty(manufacturer)) {
            if (url.equals(USDAURL)) {
                url += "?manu=" + manufacturer;
            }
            else
                url += "&manu=" + manufacturer;
        }
        Document doc = Jsoup.connect(url).get();
        //Find the food and create one and foodDAO.addFood
        System.out.print("body " + doc);
        return food;
    }
}
