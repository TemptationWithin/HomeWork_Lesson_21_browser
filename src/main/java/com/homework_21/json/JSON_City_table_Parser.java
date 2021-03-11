package com.homework_21.json;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JSON_City_table_Parser {

    public String parseID(String s){
        StringBuilder stringBuilder = new StringBuilder();
        String regExId = "[0-9]*[,]";
        Pattern pattern = Pattern.compile(regExId);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            stringBuilder.append(matcher.group());
        }
        stringBuilder.setLength(stringBuilder.length()-4);
        stringBuilder.substring(1,1);
        return String.valueOf(stringBuilder);
    }
    //"cityName":"Brest
    public String parseName(String s){
        StringBuilder stringBuilder = new StringBuilder();
        String regExCountryCode = "((\"cityName\")[:][\"][a-zA-Z]*)";
        Pattern pattern = Pattern.compile(regExCountryCode);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            stringBuilder.append(matcher.group());
        }
        stringBuilder.delete(0,12);
        return String.valueOf(stringBuilder);
    }
    public String parseDistrict(String s){
        StringBuilder stringBuilder = new StringBuilder();
        String regExCountryCode = "((\"district\")[:][\"][a-zA-Z]*)";
        Pattern pattern = Pattern.compile(regExCountryCode);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            stringBuilder.append(matcher.group());
        }
        stringBuilder.delete(0,12);
        return String.valueOf(stringBuilder);
    }
    public String parseCountryCode(String s){
        StringBuilder stringBuilder = new StringBuilder();
        String regExCountryCode = "[\"][A-Z]{3}[\"]";
        Pattern pattern = Pattern.compile(regExCountryCode);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            stringBuilder.append(matcher.group());
        }
        StringBuilder stringBuilder_2 = new StringBuilder();
        String regExCountryCode_2 = "[A-Z]{3}";
        Pattern pattern_2 = Pattern.compile(regExCountryCode_2);
        Matcher matcher_2 = pattern_2.matcher(stringBuilder);
        while (matcher_2.find()){
            stringBuilder_2.append(matcher_2.group());
        }
        return String.valueOf(stringBuilder_2);
    }
    public String parsePopulation(String s){
        StringBuilder stringBuilder = new StringBuilder();
        String regExPopulation = "[0-9]*[}]";
        Pattern pattern = Pattern.compile(regExPopulation);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()){
            stringBuilder.append(matcher.group());
        }
        stringBuilder.setLength(stringBuilder.length()-1);
        return String.valueOf(stringBuilder);
    }

}
