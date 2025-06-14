package com.pluralsight.ui;

import com.pluralsight.data.VehicleDAO;
import com.pluralsight.models.Vehicle;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static com.pluralsight.ui.StyledUI.styledHeader;

public class UserInterface {
    private final Console console = new Console();
    private final Scanner scanner = new Scanner(System.in);
    private final VehicleDAO vehicleDAO;
    
    // constructor method
    public UserInterface(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
        displayMenu();
    }

    // user interface display menu method
    private void displayMenu() {
        // this helper method will display the menu for the User to make a selection
        // for which process they would like to choose
        // will include accepting user input within this method
        int input = 0;
        while(input != -1) {
//            printDealershipInfo(this.dealership); // change this to DealershipDAO
            styledHeader("Welcome to the Dealership!");
            String welcomeMenuPrompt ="""
                    [1] Search by Price
                    [2] Search by Make/Model
                    [3] Search by Year
                    [4] Search by Color
                    [5] Search by Mileage
                    [6] Search by Vehicle Type
                    [7] Search All Vehicles
                    [8] Add Vehicle to lot
                    [9] Remove Vehicle from lot
                    [10] Sell a Vehicle
                    [11] Lease a Vehicle
                    """;

            input = console.promptForInt(welcomeMenuPrompt);

            switch (input) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
//                    processGetByMakeModelRequest();
                    break;
                case 3:
//                    processGetByYearRequest();
                    break;
                case 4:
//                    processGetByColorRequest();
                    break;
                case 5:
//                    processGetByMileageRequest();
                    break;
                case 6:
//                    processGetByVehicleTypeRequest();
                    break;
                case 7:
//                    processGetAllVehiclesRequest();
                    break;
                case 8:
//                    processAddVehicleRequest();
                    break;
                case 9:
//                    processRemoveVehicleRequest();
                    break;
                case 10:
//                    processSalesContract();
                    break;
                case 11:
//                    processLeaseContract();
                    break;
                default:
                    System.out.println("Please make a selection from the menu");
            }
        }
        
    }

    // processing methods for user requests
    private void processGetByPriceRequest() {
        // get min/max from the user
        // add formatted header so that users are aware what they are doing
        // in this instance: Searching for vehicle by price
        System.out.println(StyledUI.styledBoxTitle("Search Vehicles By Price"));
        double min = 0;
        double max = 0;

        while(true) {
            try {
                System.out.print("Please enter a minimum price: ");
                min = scanner.nextDouble();
                System.out.print("Please enter a maximum price: ");
                max = scanner.nextDouble();
                scanner.nextLine();

                if (min > max) {
                    System.out.println("Your minimum price cannot be greater than maximum price");
                    continue;
                }


            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numerical value for price.");
                scanner.nextLine();
                return;
            }
            
            List<Vehicle> results = vehicleDAO.getByPrice(min, max);
            printVehicleInventory(results);
            break;
        }

    }

    // helper method
    public void printVehicleInventory(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles in inventory \n");
            return;
        }

        // Print styled header
        System.out.println(StyledUI.FormattedTextHeader());

        // Print each vehicle using the Vehicle's toFormattedRow() method
        for (Vehicle v : vehicles) {
            System.out.println(v.toFormattedRow());
        }

        System.out.println();
    }
    
    
    
}