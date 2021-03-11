package com.homework_21.model;

public class City {
    private int id;
    private String cityName;
    private String district;
    private String countryCode;
    private int population;

    public City(String cityName, String district, String countryCode, int population) {
        this.cityName = cityName;
        this.district = district;
        this.countryCode = countryCode;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
