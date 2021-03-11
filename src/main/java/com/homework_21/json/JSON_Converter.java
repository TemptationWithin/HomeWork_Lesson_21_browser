package com.homework_21.json;

import com.google.gson.Gson;
import com.homework_21.jdbc.JDBC_CRUD_Manager;
import com.homework_21.model.City;

public class JSON_Converter {

    public static String serialization(City city){
        Gson gson = new Gson();
        String json = gson.toJson(city);
        return json;
    }
    public static City deserialization(Gson gson){
        City city = gson.fromJson(String.valueOf(gson), City.class);
        return city;
    }

}
