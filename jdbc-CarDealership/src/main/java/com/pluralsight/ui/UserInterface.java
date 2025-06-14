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
        while (true) {
//            printDealershipInfo(this.dealership); // change this to DealershipDAO
            styledHeader("Welcome to the Dealership!");

            String[] menuOptions = new String[]{
                    "Search by Make/Model",
                    "Search by Year",
                    "Search by Color",
                    "Search by Mileage",
                    "Search by Vehicle Type",
                    "Search All Vehicles",
                    "Add Vehicle to Lot",
                    "Remove Vehicle from Lot",
                    "Sell a Vehicle",
                    "Lease a Vehicle",
                    "Exit Program"};

            int userChoice = console.promptForOption(menuOptions);
            switch (userChoice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
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
                case 12:
                    System.out.println("Thank you for coming to the dealership!");
                    return;
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
        double min;
        double max;

        while (true) {
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

    private void processGetByMakeModelRequest() {
        // get make/model from the user
        // add formatted header so that users are aware what they are doing
        // in this instance: Searching for vehicle by Make/Model

        String[] options = new String[]{"Search by Make", "Search by Model", "Search Make and Model"};

        int userChoice = console.promptForOption(options);

        switch (userChoice) {
            case 1 -> {
                String searchMake = console.promptForString("Please enter the Make of the vehicle to Search: ", true);
                if (!searchMake.isEmpty()) {
                    List<Vehicle> carMakeResults = vehicleDAO.getByMake(searchMake);
                    printVehicleInventory(carMakeResults);
                }
            }
            case 2 -> {
                String searchModel = console.promptForString("Please enter the Model of the vehicle to Search: ", true);
                if (!searchModel.isEmpty()) {
                    List<Vehicle> carModelResults = vehicleDAO.getByModel(searchModel);
                    printVehicleInventory(carModelResults);
                }
            }
            case 3 -> {
                String searchMake = console.promptForString("Please enter the Make of the vehicle to Search ", true);
                String searchModel = console.promptForString("Please enter the Model of the vehicle to Search ", true);
                
                if(searchMake.isEmpty() && searchModel.isEmpty()){
                    System.out.println("The search criteria cannot be empty");
                }
                
                List<Vehicle> searchResults = vehicleDAO.getByMakeModel(searchMake, searchModel);
                printVehicleInventory(searchResults);
                
                
            }
            default -> System.out.println("Invalid entry. Please Try Again.");
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