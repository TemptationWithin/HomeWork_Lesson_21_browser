package com.homework_21.jdbc;

import com.homework_21.json.JSON_Converter;
import com.homework_21.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBC_CRUD_Manager {
    //Singleton
    private static JDBC_CRUD_Manager jdbc_crud_manager;
    private JDBC_CRUD_Manager(){}

    public static JDBC_CRUD_Manager getJdbc_crud_manager() {
        if (jdbc_crud_manager == null){
            jdbc_crud_manager = new JDBC_CRUD_Manager();
        }
        return jdbc_crud_manager;
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Statement statement = null;

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "krendelek123456";

    {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to a selected database...");

            connection = DriverManager.getConnection(DATABASE_URL, LOGIN, PASSWORD);

        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }



    /**
     *  display of possible functions for the selected СRUD mode
     */
    public String printTextInfoByIndex(int index){
        switch (index){
            case (1):

                return "\nPlease add info about new city by Enter: " +
                        "\n 1.NAME " +
                        "\n 2.COUNTRY_CODE " +
                        "\n 3.DISTRICT " +
                        "\n 4.POPULATION " +
                        "\n (enter 'exit' anywhere to exit)";
            case (2):
                return "\nEditing modes(select by number): " +
                        "\n 1.SET NAME BY OLD NAME " +
                        "\n 2.SET COUNTRY_CODE BY CITY NAME" +
                        "\n 3.SET DISTRICT BY CITY NAME" +
                        "\n 4.SET POPULATION BY CITY NAME";
            case (3):
                return "\nReading modes(select by number): " +
                        "\n 1.READ FULL TABLE: " +
                        "\n 2.READ CITIES BY POPULATION RANGE (ORDERED BY POPULATION) " +
                        "\n 3.READ CITIES BY COUNTRY CODE ";
            case (4):
                return "\nDeleting modes(select by number): " +
                        "\n 1.DELETE BY NAME: " +
                        "\n 2.DELETE CITIES BY RANGE OF POPULATION  " +
                        "\n 3.DELETE DATABASE BY NAME (BY PASSWORD)";
            case (5):
                closingResources();
                return "\nExit the program ";
            default: return "Unknown operation. Please, try again.";
        }
    }

    /**
     *  Creating a new record in the City table
     */
    public void createCity(String city_name, String city_country_code, String city_district, int city_population){
        try {
                preparedStatement = connection.prepareStatement("INSERT city " +
                        "(Name, CountryCode, District, Population)" +
                        "VALUES (?, ?, ?, ?)");
                System.out.println("\n\n Trying to add new entry... ");

                preparedStatement.setString(1, city_name);
                preparedStatement.setString(2, city_country_code);
                preparedStatement.setString(3, city_district);
                preparedStatement.setInt(4, city_population);
                preparedStatement.executeUpdate();

                System.out.println("Entry was added successfully! ");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     *  Reading the entire table City
     */
    public ArrayList<String> readFullTable(){
        ArrayList<String> stringListCities = new ArrayList<>();
        try {
            System.out.println("You have selected the mode: 1.READ FULL TABLE: ");
            preparedStatement = connection.prepareStatement("SELECT *" +
                                                            "FROM city ");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                City city = new City(resultSet.getString("Name"),
                                     resultSet.getString("District"),
                                     resultSet.getString("CountryCode"),
                                     resultSet.getInt("Population"));
                city.setId(resultSet.getInt("id"));
                stringListCities.add(JSON_Converter.serialization(city));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        System.out.println(stringListCities);
        return stringListCities;
    }

    /**
     *  Reading cities by population range from the City table
     */
    public ArrayList<String> readByPopulationRange(int lowLimit, int upLimit){
            ArrayList<String> stringPopulationRange = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * " +
                    "FROM city " +
                    "WHERE Population BETWEEN ? AND ? " +
                    "ORDER BY Population DESC ");
            preparedStatement.setInt(1, lowLimit);
            preparedStatement.setInt(2, upLimit);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                City city = new City(resultSet.getString("Name"),
                        resultSet.getString("District"),
                        resultSet.getString("CountryCode"),
                        resultSet.getInt("Population"));
                city.setId(resultSet.getInt("id"));
                stringPopulationRange.add(JSON_Converter.serialization(city));
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return stringPopulationRange;
    }

    /**
     *  Reading cities by country code from the City table
     */
    public ArrayList<String> readByCountryCode(String countryCode){
            ArrayList<String> stringCityByDistrict = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * " +
                                                                "FROM city " +
                                                                "WHERE CountryCode = ?");

            preparedStatement.setString(1, countryCode);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                City city = new City(resultSet.getString("Name"),
                        resultSet.getString("District"),
                        resultSet.getString("CountryCode"),
                        resultSet.getInt("Population"));
                city.setId(resultSet.getInt("id"));
                stringCityByDistrict.add(JSON_Converter.serialization(city));
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return stringCityByDistrict;
    }

    /**
     *  Changing the city name from the old name in the City table
     */
    public void updateNameByOldName(String oldName, String newName){
        try {
            System.out.println("You have selected the mode: 1.SET NAME BY OLD NAME " +
                    "\nPlease, enter the name of the city you want to change");
            String oldCityName = oldName;
            System.out.println("Please, enter the new name of the city you want to change");
            String newCityName = newName;
            preparedStatement = connection.prepareStatement("UPDATE city " +
                    "SET Name = ? " +
                    "WHERE Name LIKE ?");

            System.out.println("\n Trying to set entry... ");
            preparedStatement.setString(1, newCityName);
            preparedStatement.setString(2, oldCityName);
            preparedStatement.executeUpdate();
            System.out.println("\n Entry was set successfully! ");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     *  Changing the country code by city name in the City table
     */
    public void updateCountryCodeByCityName(String cityName, String newCountryCode){
        try {
            String name = cityName;
            String countryCode = newCountryCode;
            preparedStatement = connection.prepareStatement("UPDATE city " +
                                                                "SET CountryCode = ? " +
                                                                "WHERE Name LIKE ?");

            System.out.println("\n\n Trying to set entry... ");
            preparedStatement.setString(1, countryCode);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            System.out.println("\n entry was set successfully! ");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     * Changing a district by city name in the City table
     */
    public void updateDistrictByCityName(String name, String newDistrict){
        try{
            String cityName = name;
            String district = newDistrict;
            preparedStatement = connection.prepareStatement("UPDATE city " +
                    "SET District = ? " +
                    "WHERE Name LIKE ?");

            System.out.println("\n\n Trying to set entry... ");
            preparedStatement.setString(1, district);
            preparedStatement.setString(2, cityName);
            preparedStatement.executeUpdate();
            System.out.println("\n entry was set successfully! ");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     * Population change by city name in the City table
     */
    public void updatePopulationByCityName(String name, int population){
        try {
            String cityName = name;
            System.out.println("Please, enter the new population of the city " + cityName);
            int newPopulation = population;
            preparedStatement = connection.prepareStatement("UPDATE city " +
                                                                "SET Population = ? " +
                                                                "WHERE Name LIKE ?");
            System.out.println("\n\n Trying to set entry... ");
            preparedStatement.setInt(1, newPopulation);
            preparedStatement.setString(2, cityName);
            preparedStatement.executeUpdate();
            System.out.println("\n entry was set successfully! ");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     * Deleting a city by name
     */
    public void deleteCityByName(String name){
        try {
                preparedStatement = connection.prepareStatement("DELETE FROM city " +
                                                                    "WHERE Name = ?");
                System.out.println("\n Trying to delete... ");
                preparedStatement.setString(1, name);
                System.out.println(" Entry was deleted successfully! ");
                preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     * Deleting a city by id
     */
    public void deleteCityById(int id){
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM city " +
                                                                "WHERE id = ?");
            System.out.println("\n Trying to delete... ");
            preparedStatement.setInt(1, id);
            System.out.println(" Entry was deleted successfully! ");
            preparedStatement.executeUpdate();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     * Removing cities by population range
     */
    public void deleteByPopulationRange(int low, int up){
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM city " +
                                                                "WHERE Population BETWEEN ? AND ?");
            System.out.println("\n Trying to delete... ");
            preparedStatement.setInt(1, low);
            preparedStatement.setInt(2, up);
            preparedStatement.executeUpdate();
            System.out.println("\n entries was deleted successfully! ");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     * Closing the required resources
     */
    public void closingResources(){
        System.out.println("\n*********  closing resources... ************");
        try {
            if (connection != null){
                connection.close();
            }
            if (statement != null){
                statement.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (resultSet != null){
                resultSet.close();
            }
            System.out.println("Resources was closed successfully!");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    /**
     * Choose: Create or Connect to dataBase
     */
    public void createOrConnectDatabase(int response, String name){
        String DATABASE_URL = "jdbc:mysql://localhost:3306/";
        System.out.println("Please, enter the name of database.");
        String dataBaseName = name;
        DATABASE_URL += dataBaseName;
        try {
            switch (response) {
                case (1):     // -----------CREATE NEW DATABASE WITH CITY TABLE --------------------------------------
                    createDatabase(dataBaseName);
                    connection = DriverManager.getConnection(DATABASE_URL, LOGIN, PASSWORD);
                    System.out.println("Connected database successfully...");
                    String sql_createTableCity =
                            "CREATE TABLE city " +
                                    " (id INT AUTO_INCREMENT PRIMARY KEY, " +
                                    " Name VARCHAR(35) not NULL, " +
                                    " CountryCode VARCHAR(3) not NULL, " +
                                    " District VARCHAR(20) not NULL, " +
                                    " Population INT not NULL)";
                    statement = connection.createStatement();
                    statement.executeUpdate(sql_createTableCity);
                    System.out.println("For convenient work, an empty city table was created...");
                    break; // break case (1)
                case (2):     // ------------CONNECT TO DATABASE------------------------------------
                    System.out.println("Ok, we will trying connect to the database " + dataBaseName + "...");
                    connection = DriverManager.getConnection(DATABASE_URL, LOGIN, PASSWORD);
                    System.out.println("Connected database successfully...");
                    break;  // break case (2)
                default:
                    System.out.println("You can only answer 'yes' or 'no'. Please try again");
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     * Creating dataBase - used only inside class
     */
    private void createDatabase(String name){
        System.out.println("Creating a new database...");
        try {
            Statement statement = connection.createStatement();
            String sql_createDataBase = "CREATE DATABASE " + name;
            statement.executeUpdate(sql_createDataBase);
            System.out.println("Creation of a new database was successful!");
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     * Drop dataBase
     */
    public void deleteDatabase(String name){
            try {
                preparedStatement = connection.prepareStatement("DROP DATABASE + ?");
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
                    System.out.println("Database was deleted!");
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
    }



    public boolean isNameContainsInDatabase(String name){
        ArrayList<String> listName = new ArrayList<>();
        try{
        preparedStatement = connection.prepareStatement("SELECT Name " +
                                                            "FROM city ");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            listName.add(resultSet.getString("Name"));
        }
    }catch (SQLException sqlException){
        sqlException.printStackTrace();
    }
        if (listName.contains(name)){
            return true;
        } else return false;
    }
    public boolean isIdContainsInDatabase(int id){
        ArrayList<Integer> listName = new ArrayList<>();
        try{
            preparedStatement = connection.prepareStatement("SELECT id " +
                                                                "FROM city ");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listName.add(resultSet.getInt("id"));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        if (listName.contains(id)){
            return true;
        } else return false;
    }
    public boolean isPasswordValid(String password){
        if (password.equals(PASSWORD)){
            return true;
        } else {
            return false;
        }
    }

}
