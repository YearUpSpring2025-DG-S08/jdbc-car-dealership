package com.pluralsight.data;

import com.pluralsight.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {
    private static final Logger logger = LogManager.getLogger(DealershipDAO.class);
    private final BasicDataSource dataSource;

    public DealershipDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    // search query methods
    public Vehicle getByVin(int userChosenVin){
        Vehicle queryVehicle = null;
        
        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE VIN = ? AND has_Contract = false;
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setInt(1, userChosenVin);

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
                queryVehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
            }

            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles with the vin: {} ✅", userChosenVin);
            } else{
                logger.warn("❌ No vehicles found with the vin: {} ❌", userChosenVin);
            }

        } catch (SQLException e) {
            logger.error("Could not query vehicles by vin");
        }


        return queryVehicle;
    }
    
    public List<Vehicle> getByPrice(double minPrice, double maxPrice){
        // we need a place to hold the results
        ArrayList<Vehicle> results = new ArrayList<>();
        
        // we need to ask for the data
        String query = """
                SELECT VIN, year, make, model, type, color, mileage, price
                FROM car_dealership.vehicles v
                WHERE price >= ? AND price <= ? AND has_Contract = false;
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
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
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
                WHERE make = ? AND has_Contract = false;
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
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
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
                WHERE model = ? AND has_Contract = false;
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
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
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
                WHERE make = ? AND model = ? AND has_Contract = false;
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
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
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
                WHERE year
                BETWEEN ? AND ? AND has_Contract = false;
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setDouble(1, minYear);
            s.setDouble(2, maxYear);

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
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
                results.add(vehicle);
            }

            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles within the year range: {} and {} ✅", (int) minYear, (int) maxYear);
            } else{
                logger.warn("❌ No vehicles found within the year range: {} and {} ❌", (int) minYear, (int) maxYear);
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
                WHERE color = ? AND has_Contract = false
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setString(1, userChosenColor);

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
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
                results.add(vehicle);
            }

            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles with the color: {} ✅", userChosenColor);
            } else{
                logger.warn("❌ No vehicles found with the color: {} ❌", userChosenColor);
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
                WHERE mileage
                BETWEEN ? AND ?
                AND has_Contract = false
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setDouble(1, minMileage);
            s.setDouble(2, maxMileage);

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
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
                results.add(vehicle);
            }

            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles within the mileage range: {} and {} ✅", (int) minMileage, (int) maxMileage);
            } else{
                logger.warn("❌ No vehicles found within the mileage range: {} and {} ❌", (int) minMileage, (int) maxMileage);
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
                WHERE type = ? AND has_Contract = false;
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query))
        {

            // protect from sql injection
            s.setString(1, userChosenVehicleType);

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
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
                results.add(vehicle);
            }


            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles with the type: {} ✅", userChosenVehicleType);
            } else{
                logger.warn("❌ No vehicles found with the type: {} ❌", userChosenVehicleType);
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
                FROM car_dealership.vehicles v
                WHERE v.has_Contract = false;
                """;

        // we need to make a connection to the database, send a prepared statement of the query, and execute it
        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query);
            ResultSet queryResults = s.executeQuery()
        )
        {

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
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, mileage, price, false);
                results.add(vehicle);
            }

            // add a logging message to communicate with user
            if(hasRows){
                logger.info("✅ Successfully retrieved vehicles ✅");
            } else{
                logger.warn("❌ No vehicles found ❌");
            }
            
        } catch (SQLException e) {
            logger.error("Could not query vehicles");
        }


        return results;
    }
    
    // insert data method
    public void addVehicle(Vehicle vehicle){
        // we need to add the given vehicle to the database using an INSERT statement
        String insertQuery = """
                INSERT into vehicles (VIN, year, make, model, type, color, mileage, price, has_Contract)
                values (?, ?, ? , ?, ?, ?, ?, ?, false);
                """;
        
        try(Connection c = dataSource.getConnection();
        PreparedStatement s = c.prepareStatement(insertQuery)
        )
        {
            
            s.setInt(1, vehicle.getVin());
            s.setInt(2, vehicle.getYear());
            s.setString(3, vehicle.getMake());
            s.setString(4, vehicle.getModel());
            s.setString(5, vehicle.getType());
            s.setString(6, vehicle.getColor());
            s.setInt(7, (int) vehicle.getMileage());
            s.setBigDecimal(8, BigDecimal.valueOf(vehicle.getPrice()));

            int rowsAffected = s.executeUpdate();
            
            // add a logging message to communicate with user
            if(rowsAffected > 0) {
                logger.info("✅ Successfully added a new vehicle to the dealership inventory ✅");
            }
            
        } catch (SQLException e) {
            logger.error("❌ Could not add new vehicle to dealership inventory ❌");
        }
    }

    
    // remove data method
    public void removeVehicle(int vin){
        // we need to take the given vin and match to a vehicle within the database to remove
        // refactor code to set a helper method to delete from inventory, and vehicles table
        try( Connection c = dataSource.getConnection())
        {
            String deleteFromInventoryQuery = """
                DELETE FROM car_dealership.inventory i
                WHERE vin = ?
                AND has_Contract = false;
                """;
            
            try(PreparedStatement s = c.prepareStatement(deleteFromInventoryQuery))
            {
                s.setInt(1, vin);
                
                s.executeUpdate();
            }
            
            // have to add condition to delete a vehicle from the contracts tables if it applies
            // most likely will need to create a helper method to check
            
        String deleteFromVehicleQuery = """
                DELETE FROM car_dealership.vehicles v
                WHERE vin = ?
                AND has_Contract = false;
                """;
        
            try (PreparedStatement s1 = c.prepareStatement(deleteFromVehicleQuery)
            )
            {
                
                s1.setInt(1, vin);
                
                int rowsAffected = s1.executeUpdate();
    
                // add a logging message to communicate with user
                if(rowsAffected > 0) {
                    logger.info("✅ Successfully removed a vehicle from dealership vehicles ✅");
                }
                
            } catch (SQLException e) {
                logger.error("❌ Could not remove vehicle from dealership vehicles ❌");
                System.out.println(e.getMessage());
            }
            
        } catch (SQLException ex){
    logger.error("❌ Could not remove vehicle from dealership inventory ❌");
        }
    }
    
    // update data method
    public void updateVehicle(Vehicle vehicle){
        
        String updateVehicleQuery = """
                update car_dealership.vehicles v\s
                set v.has_Contract = true
                where v.VIN = ?;
                """;
        
        try(Connection c = dataSource.getConnection();
        PreparedStatement s = c.prepareStatement(updateVehicleQuery)) {
            
            s.setInt(1, vehicle.getVin());
            
            s.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return;
    }
    
}

