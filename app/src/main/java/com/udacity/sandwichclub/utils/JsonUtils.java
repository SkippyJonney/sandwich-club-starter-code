package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json)  {

        //Create Sandwich object to populate
        Sandwich tastySandwich = new Sandwich();

        //Parse Json into Sandwich object
        tastySandwich.setMainName(getJsonString("name","mainName",json));
        tastySandwich.setAlsoKnownAs(getJsonStringArray("name","alsoKnownAs",json));
        tastySandwich.setPlaceOfOrigin(getJsonString("placeOfOrigin", json));
        tastySandwich.setDescription(getJsonString("description", json));
        tastySandwich.setImage(getJsonString("image",json));
        tastySandwich.setIngredients(getJsonStringArray("ingredients",json));

        return tastySandwich;
    }

    //Get String from Json using key value
    public static String getJsonString(String key1, String data) {
        String tmp = "";
        try {
            JSONObject json = new JSONObject(data);
            tmp = json.getString(key1);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return tmp;
    }

    //Overload using two key values for second layer data
    public static String getJsonString(String key1, String key2, String data) {
        String tmp = "";
        try {
            JSONObject json = new JSONObject(data);
            tmp = new JSONObject(json.getString(key1)).getString(key2);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return tmp;
    }

    //Get String[] from Json using key value
    public static List<String> getJsonStringArray(String key1, String data) {
        List<String> tmp = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(data);
            JSONArray jsonArray = new JSONArray(json.getString(key1));
            //parse JSONArray
           for(int i = 0;i < jsonArray.length();i++) {
                tmp.add(jsonArray.getString(i));
           }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return tmp;
    }

    //Overload using two key values for second layer data
    public static List<String> getJsonStringArray(String key1,String key2, String data) {
        List<String> tmp = new ArrayList<>();
        try {
            JSONObject json = new JSONObject(data);
            JSONArray jsonArray = new JSONObject(json.getString(key1)).getJSONArray(key2);
            //parse JSONArray
            for(int i = 0;i < jsonArray.length();i++) {
                tmp.add(jsonArray.getString(i));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return tmp;
    }

}
