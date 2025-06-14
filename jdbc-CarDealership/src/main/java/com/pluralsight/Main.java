package com.pluralsight;


import com.pluralsight.data.VehicleDAO;
import com.pluralsight.ui.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {

        // set up a basic data source to retrieve the database
        if(args.length != 3){
            System.out.println("You must run this program with three arguments:" +
                    "<username> <password> <url>");
            System.exit(-1);
        }

        BasicDataSource basicDataSource = getBasicDataSourceFromArgs(args);
        VehicleDAO vehicleDAO = new VehicleDAO(basicDataSource);
        
        
        UserInterface ui = new UserInterface(vehicleDAO);
        
        
    }
    
    private static BasicDataSource getBasicDataSourceFromArgs(String[] args){
        String username = args[0];
        String password = args[1];
        String url = args[2];
        BasicDataSource bds = new BasicDataSource();
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setUrl(url);
        return bds;
    }
}