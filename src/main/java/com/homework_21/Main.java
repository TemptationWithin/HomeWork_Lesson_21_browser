package com.homework_21;

import com.homework_21.jdbc.JDBC_CRUD_Manager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int operationIndex = 0;

        JDBC_CRUD_Manager manager = JDBC_CRUD_Manager.getJdbc_crud_manager();
        System.out.println("1 - CONNECT - I already have a database." +
                           "2 - CREATE - I want to create a new database.");
        int response = Integer.parseInt(scanner.nextLine());
        String databaseName = scanner.nextLine();
        manager.createOrConnectDatabase(response, databaseName);

        while (operationIndex != 5) {
            //System.out.println(manager.printOperationTextInfo());
            operationIndex = Integer.parseInt(scanner.nextLine());                   // choice of operation
            System.out.println(manager.printTextInfoByIndex(operationIndex));

            switch (operationIndex) {

                case (1):
                    /**
                     ************************ CREATE *********************************
                     */
                    String name = scanner.nextLine();
                    String country_code = scanner.nextLine();
                    String district = scanner.nextLine();
                    int population = Integer.parseInt(scanner.nextLine());
                    manager.createCity(name,country_code,district,population);
                    break; //break case(1)

                case (2):
                    /**
                     ************************ UPDATE *********************************
                     */
                    int updateIndex = Integer.parseInt(scanner.nextLine());

                    switch (updateIndex) {

                        case (1):// ----------------------1.SET NAME BY OLD NAME----------------------
                            String oldName = scanner.nextLine();
                            String newName = scanner.nextLine();
                            manager.updateNameByOldName(oldName, newName);
                            break;  // break case (2->1)

                        case (2)://----------------------2.SET COUNTRY_CODE BY CITY NAME----------------------
                            System.out.println("You have selected the mode: 2.SET COUNTRY_CODE BY CITY NAME " +
                                    "\nPlease, enter the name of the city you want to change");
                            String cityName = scanner.nextLine();
                            System.out.println("Please, enter the new country code");
                            String newCountryCode = scanner.nextLine();
                            manager.updateCountryCodeByCityName(cityName, newCountryCode);
                            break; // break case (2->2)

                        case (3)://------------------3.SET DISTRICT BY CITY NAME----------------------
                            System.out.println("You have selected the mode: 3.SET DISTRICT BY CITY NAME " +
                                    "\nPlease, enter the name of the city you want to change");
                            String city_name = scanner.nextLine();
                            System.out.println("Please, enter the new district");
                            String newDistrict = scanner.nextLine();
                            manager.updateDistrictByCityName(city_name, newDistrict);
                            break; // break case (2->3)

                        case (4)://------------------4.SET POPULATION BY CITY NAME------------------
                            System.out.println("You have selected the mode: 3.SET DISTRICT BY CITY NAME " +
                                    "\nPlease, enter the name of the city you want to change");
                            String city__name = scanner.nextLine();
                            System.out.println("Please, enter the new population of the city " + city__name);
                            int newPopulation = Integer.parseInt(scanner.nextLine());
                            manager.updatePopulationByCityName(city__name, newPopulation);
                            break; // break case (2->4)

                        default:
                            System.out.println("Unknown operationIndex. Please try again");
                    }
                    break; // break case (2)

                case (3):

                    /**
                     ************************ READ *********************************
                     */
                    int readIndex = Integer.parseInt(scanner.nextLine());

                    switch (readIndex) {
                        case (1)://------------1.READ FULL TABLE------------------
                            manager.readFullTable();
                            break; // break case (3->1)

                        case (2)://------------2.READ CITIES AND POPULATION (ORDERED BY POPULATION)------------------
                            System.out.println("You have selected the mode: 2.READ CITIES BY POPULATION RANGE (ORDERED BY POPULATION): " +
                                    "\nPlease indicate the low limit of the population ");


                            int lowerLimit = Integer.parseInt(scanner.nextLine());
                            System.out.println("Please indicate the upper limit of the population ");
                            int upperLimit = Integer.parseInt(scanner.nextLine());
                            manager.readByPopulationRange(lowerLimit, upperLimit);
                            break; // break case (3->2)

                        case (3)://------------3.READ CITIES BY COUNTRY CODE------------------
                            System.out.println("You have selected the mode: 3.READ CITIES BY COUNTRY CODE: " +
                                    "\nPlease indicate the country code ");
                            String countryCode = scanner.nextLine();
                            manager.readByCountryCode(countryCode);
                            break; // break case (3->3)

                        default:
                            System.out.println("Unknown operationIndex. Please, try again.");
                    }

                    break; // break case (3)

                case (4):
                    /**
                     ************************ DELETE *********************************
                     */

                    int deleteIndex = Integer.parseInt(scanner.nextLine());

                    switch (deleteIndex) {
                        case (1)://------------1.DELETE BY NAME------------------
                            System.out.println("You have selected the mode: 1.DELETE BY NAME: " +
                                    "\nPlease indicate the city name " +
                                    "\n (enter 'exit' anywhere to exit));");
                            String deleteName = scanner.nextLine();
                            manager.deleteCityByName(deleteName);
                            break; // break case (4->1)

                        case (2)://------------2.DELETE CITIES BY RANGE OF POPULATION------------------
                            System.out.println("You have selected the mode: 2.DELETE CITIES BY RANGE OF POPULATION: " +
                                    "\nPlease indicate the low limit of the population ");

                            int lowerLimit = Integer.parseInt(scanner.nextLine());
                            System.out.println("Please indicate the upper limit of the population ");
                            int upperLimit = Integer.parseInt(scanner.nextLine());
                            manager.deleteByPopulationRange(lowerLimit, upperLimit);
                            break; // break case (4->2)

                        case (3)://-------------3. DELETE DATABASE BY NAME (BY PASSWORD)----------------
                            System.out.println("You have selected the database drop mode. " +
                                    "\nPlease enter a password, thereby confirming your access to delete such objects: ");
                            String password = scanner.nextLine();
                            if (manager.isPasswordValid(password)) {
                                System.out.println("Please enter the name of the database you want to delete. \nEnter 'exit' to leave ");
                                String deleteDatabaseName = scanner.nextLine();
                                manager.deleteDatabase(deleteDatabaseName);
                            } else System.out.println("Access denied!");
                            break;
                    }
                    break; // break case (4)
                case (5):
                    /**
                     ************************ EXIT *********************************
                     */
                    break;
                default:
                    System.out.println("Unknown operationIndex. Please, try again");
            }
        }
        scanner.close();
    }
}
