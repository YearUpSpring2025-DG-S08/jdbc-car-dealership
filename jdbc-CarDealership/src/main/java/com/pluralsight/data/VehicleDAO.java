package com.pluralsight.data;

import com.pluralsight.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    private static final Logger logger = LogManager.getLogger(VehicleDAO.class);
    private final BasicDataSource dataSource;

    public VehicleDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public List<Vehicle> getByPrice(double minPrice, double maxPrice){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();
        
        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE price >= ? AND price <= ?
                """;
        
        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query)) 
        {
            
            // protect from sql injection
            s.setDouble(1, minPrice);
            s.setDouble(2, maxPrice);

            ResultSet queryResults = s.executeQuery();
            
            
            // boolean to check whether query results has any rows
            boolean hasRows = false;
            // loop through results and parse the information to be saved
            while(queryResults.next()){
                hasRows = true;
                
                int vin = queryResults.getInt(1);
                int year = queryResults.getInt(2);
                String make = queryResults.getString(3);
                String model = queryResults.getString(4);
                String type = queryResults.getString(5);
                String color = queryResults.getString(6);
                double mileage = queryResults.getDouble(7);
                double price = queryResults.getDouble(8);

                // create a new instance of the Vehicle model and add to the array to be returned
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                results.add(vehicle);
            }

            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles within the price range: ${} and ${} ✅", minPrice, maxPrice);
            } else{
                logger.warn("❌ No vehicles found within the price range: ${} and ${} ❌", minPrice, maxPrice);
            }
            
        } catch (SQLException e) {
            logger.error("Could not query vehicles by price");
        }


        return results;
    }
    
    public List<Vehicle> getByMake(String userChosenMake){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();

        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE make = ?
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setString(1, userChosenMake);

            ResultSet queryResults = s.executeQuery();

            // boolean to check whether query results has any rows
            boolean hasRows = false;
            // loop through results and parse the information to be saved
            while(queryResults.next()){
                hasRows = true;
                int vin = queryResults.getInt(1);
                int year = queryResults.getInt(2);
                String make = queryResults.getString(3);
                String model = queryResults.getString(4);
                String type = queryResults.getString(5);
                String color = queryResults.getString(6);
                double mileage = queryResults.getDouble(7);
                double price = queryResults.getDouble(8);

                // create a new instance of the Vehicle model and add to the array to be returned
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                results.add(vehicle);
            }

            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles with the make: {} ✅", userChosenMake);
            } else{
                logger.warn("❌ No vehicles found with make: {} ❌", userChosenMake);
            }

        } catch (SQLException e) {
            logger.error("Could not query vehicles by make");
        }


        return results;
    }

    public List<Vehicle> getByModel(String userChosenModel){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();

        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE model = ?
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setString(1, userChosenModel);

            ResultSet queryResults = s.executeQuery();

            // boolean to check whether query results has any rows
            boolean hasRows = false;
            // loop through results and parse the information to be saved
            while(queryResults.next()){
                hasRows = true;
                int vin = queryResults.getInt(1);
                int year = queryResults.getInt(2);
                String make = queryResults.getString(3);
                String model = queryResults.getString(4);
                String type = queryResults.getString(5);
                String color = queryResults.getString(6);
                double mileage = queryResults.getDouble(7);
                double price = queryResults.getDouble(8);

                // create a new instance of the Vehicle model and add to the array to be returned
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                results.add(vehicle);
            }

            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles with the model: {} ✅", userChosenModel);
            } else{
                logger.warn("❌ No vehicles found with the model: {} ❌", userChosenModel);
            }

        } catch (SQLException e) {
            logger.error("Could not query vehicles by model");
        }


        return results;
    }
    
    public List<Vehicle> getByMakeModel(String userChosenMake, String userChoseModel){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();

        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE make = ? AND model = ?
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setString(1, userChosenMake);
            s.setString(2, userChoseModel);

            ResultSet queryResults = s.executeQuery();
            

            // boolean to check whether query results has any rows
            boolean hasRows = false;
            // loop through results and parse the information to be saved
            while(queryResults.next()){
                hasRows = true;
                int vin = queryResults.getInt(1);
                int year = queryResults.getInt(2);
                String make = queryResults.getString(3);
                String model = queryResults.getString(4);
                String type = queryResults.getString(5);
                String color = queryResults.getString(6);
                double mileage = queryResults.getDouble(7);
                double price = queryResults.getDouble(8);

                // create a new instance of the Vehicle model and add to the array to be returned
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                results.add(vehicle);
            }

            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles with the make and model: {} {}✅", userChosenMake, userChoseModel);
            } else{
                logger.warn("❌ No vehicles found with make and model: {} {}❌", userChosenMake, userChoseModel);
            }

        } catch (SQLException e) {
            logger.error("Could not query vehicles by make and model");
        }


        return results;
    }
    
    public List<Vehicle> getByYear(double minYear, double maxYear){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();

        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE year >= ? AND year <= ?
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setDouble(1, minYear);
            s.setDouble(2, maxYear);

            ResultSet queryResults = s.executeQuery();

            // add a logging message to communicate with user
            if(queryResults.next()){
                logger.info("✅ Successfully retrieved vehicles within the year range: {} and {} ✅", minYear, maxYear);
            } else{
                logger.warn("❌ No vehicles found within the year range: {} and {} ❌", minYear, maxYear);
            }

            // loop through results and parse the information to be saved
            while(queryResults.next()){
                int vin = queryResults.getInt(1);
                int year = queryResults.getInt(2);
                String make = queryResults.getString(3);
                String model = queryResults.getString(4);
                String type = queryResults.getString(5);
                String color = queryResults.getString(6);
                double mileage = queryResults.getDouble(7);
                double price = queryResults.getDouble(8);

                // create a new instance of the Vehicle model and add to the array to be returned
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                results.add(vehicle);
            }


        } catch (SQLException e) {
            logger.error("Could not query vehicles by year");
        }


        return results;
    }
    
    public List<Vehicle> getByColor(String userChosenColor){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();

        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE color = ?
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setString(1, userChosenColor);

            ResultSet queryResults = s.executeQuery();

            // add a logging message to communicate with user
            if(queryResults.next()){
                logger.info("✅ Successfully retrieved vehicles with the color: {} ✅", userChosenColor);
            } else{
                logger.warn("❌ No vehicles found with the color: {} ❌", userChosenColor);
            }

            // loop through results and parse the information to be saved
            while(queryResults.next()){
                int vin = queryResults.getInt(1);
                int year = queryResults.getInt(2);
                String make = queryResults.getString(3);
                String model = queryResults.getString(4);
                String type = queryResults.getString(5);
                String color = queryResults.getString(6);
                double mileage = queryResults.getDouble(7);
                double price = queryResults.getDouble(8);

                // create a new instance of the Vehicle model and add to the array to be returned
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                results.add(vehicle);
            }


        } catch (SQLException e) {
            logger.error("Could not query vehicles by color");
        }


        return results;
    }

    public List<Vehicle> getByMileage(double minMileage, double maxMileage){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();

        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE mileage <= ? AND mileage >= ?;
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setDouble(1, minMileage);
            s.setDouble(2, maxMileage);

            ResultSet queryResults = s.executeQuery();

            // add a logging message to communicate with user
            if(queryResults.next()){
                logger.info("✅ Successfully retrieved vehicles within the range: {} and {} ✅", minMileage, maxMileage);
            } else{
                logger.warn("❌ No vehicles found within the range: {} and {} ❌", minMileage, maxMileage);
            }

            // loop through results and parse the information to be saved
            while(queryResults.next()){
                int vin = queryResults.getInt(1);
                int year = queryResults.getInt(2);
                String make = queryResults.getString(3);
                String model = queryResults.getString(4);
                String type = queryResults.getString(5);
                String color = queryResults.getString(6);
                double mileage = queryResults.getDouble(7);
                double price = queryResults.getDouble(8);

                // create a new instance of the Vehicle model and add to the array to be returned
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                results.add(vehicle);
            }


        } catch (SQLException e) {
            logger.error("Could not query vehicles by mileage");
        }


        return results;
    }

    public List<Vehicle> getByVehicleType(String userChosenVehicleType){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();

        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE type = ?;
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setString(1, userChosenVehicleType);

            ResultSet queryResults = s.executeQuery();

            // add a logging message to communicate with user
            if(queryResults.next()){
                logger.info("✅ Successfully retrieved vehicles with the type: {} ✅", userChosenVehicleType);
            } else{
                logger.warn("❌ No vehicles found with the type: {} ❌", userChosenVehicleType);
            }

            // loop through results and parse the information to be saved
            while(queryResults.next()){
                int vin = queryResults.getInt(1);
                int year = queryResults.getInt(2);
                String make = queryResults.getString(3);
                String model = queryResults.getString(4);
                String type = queryResults.getString(5);
                String color = queryResults.getString(6);
                double mileage = queryResults.getDouble(7);
                double price = queryResults.getDouble(8);

                // create a new instance of the Vehicle model and add to the array to be returned
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                results.add(vehicle);
            }


        } catch (SQLException e) {
            logger.error("Could not query vehicles by type");
        }


        return results;
    }
    
    public List<Vehicle> getAllVehicles(){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();

        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v;
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query);
            ResultSet queryResults = s.executeQuery()
        )
        {

            // add a logging message to communicate with user
            if(queryResults.next()){
                logger.info("✅ Successfully retrieved vehicles ✅");
            } else{
                logger.warn("❌ No vehicles found ❌");
            }

            // loop through results and parse the information to be saved
            while(queryResults.next()){
                int vin = queryResults.getInt(1);
                int year = queryResults.getInt(2);
                String make = queryResults.getString(3);
                String model = queryResults.getString(4);
                String type = queryResults.getString(5);
                String color = queryResults.getString(6);
                double mileage = queryResults.getDouble(7);
                double price = queryResults.getDouble(8);

                // create a new instance of the Vehicle model and add to the array to be returned
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price);
                results.add(vehicle);
            }


        } catch (SQLException e) {
            logger.error("Could not query vehicles");
        }


        return results;
    }
    

}
