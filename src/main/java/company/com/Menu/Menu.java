package company.com.Menu;

import company.com.Controllers.*;
import company.com.Objects.Passengers;

import java.util.Scanner;

public class Menu {
    public static void menuStart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! Who are you?");
        System.out.println("1. Administrator \t 2. Passenger");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        System.out.println("");

        switch (option){
            case 1:
                System.out.print("Do you have an account (Y/N)? ");
                String answer1 = scanner.next();
                switch (answer1) {
                    case "Y":
                    case "y":
                        Menu.menuAdminLogIn();
                        break;
                    case "N":
                    case "n":
                        System.out.print("Wish to sign up (Y/N)? ");
                        String choice2 = scanner.next();
                        switch (choice2){
                            case "Y":
                            case "y":
                                AdminController.signUp();
                                break;
                            case "N":
                            case "n":
                                System.out.println("Good bye");
                                break;
                            default:
                                System.out.println("Invalid input, start over");
                        }
                        break;
                    default:
                        System.out.println("Invalid input, start over");
                }
                break;
            case 2:
                System.out.print("Do you have an account (Y/N)? ");
                String answer2 = scanner.next();
                switch (answer2){
                    case "Y":
                    case "y":
                        System.out.print("Wish to log in (Y/N)? ");
                        String choice1 = scanner.next();
                        switch (choice1){
                            case "Y":
                            case "y":
                                menuPassengerLogin();
                                break;
                            case "N":
                            case "n":
                                System.out.println("Good bye");
                                break;
                            default:
                                System.out.println("Invalid input, start over");
                        }
                        break;
                    case "N":
                    case "n":
                        System.out.print("Wish to sign up (Y/N)? ");
                        String choice2 = scanner.next();
                        switch (choice2){
                            case "Y":
                            case "y":
                                PassengerController.signUp();
                                break;
                            case "N":
                            case "n":
                                System.out.println("Good bye");
                                break;
                            default:
                                System.out.println("Invalid input, start over");
                        }
                        break;
                    default:
                        System.out.println("Invalid input, start over");
                }
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }


    public static void menuAdminLogIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wish to log in (Y/N)? ");
        String choice3 = scanner.next();

        switch (choice3){
            case "Y":
            case "y":
                AdminController.logIn();
                break;
            case "N":
            case "n":
                System.out.println("Good bye");
                break;
            default:
                System.out.println("Invalid input, start over");
                break;
        }
    }


    public static void menuPassengerLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wish to log in (Y/N)? ");
        String choice1 = scanner.next();

        switch (choice1){
            case "Y":
            case "y":
                PassengerController.logIn();
                break;
            case "N":
            case "n":
                System.out.println("Good bye");
                break;
            default:
                System.out.println("Invalid input, start over");
                break;
        }
    }


    public static void menuAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do? ");
        System.out.println("1. Work with lists \t 2. Log out");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("\nChoose a list: ");
                System.out.println("1. Busses list \t 2. Drivers list \t 3. Routes list \t 4. Log out");
                System.out.print("Select an option: ");
                int input = scanner.nextInt();
                switch (input) {
                    case 1:
                        menuBus();
                        break;
                    case 2:
                        menuDriver();
                        break;
                    case 3:
                        menuRoute();
                        break;
                    case 4:
                        System.out.println("Good bye");
                        break;
                    default:
                        System.out.println("Invalid input, start over");
                }
                break;
            case 2:
                System.out.println("Good bye");
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }

    public static void menuPassenger() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do? ");
        System.out.println("1. Buy a ticket \t 2. Log out \t");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                PassengerController.buyTickets();
                break;
            case 2:
                System.out.println("Good bye");
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }

    public static void menuBus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do? ");
        System.out.println("1. See bus list \t 2. Add new bus \t 3. Go to Main Menu \t 4. Log out");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                BusController.seeBusList();
                break;
            case 2:
                BusController.addNewBus();
                break;
            case 3:
                Menu.menuAdmin();
                break;
            case 4:
                System.out.println("Good bye");
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }

    public static void menuRoute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do? ");
        System.out.println("1. See routes list \t 2. Add new route \t 3. Go to Main Menu \t 4. Log out");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                RouteController.seeRouteList();
                break;
            case 2:
                RouteController.addNewRoute();
                break;
            case 3:
                Menu.menuAdmin();
                break;
            case 4:
                System.out.println("Good bye");
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }

    public static void menuDriver() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to do? ");
        System.out.printf("%-30s %-30s\n", "1. See drivers list", "2. Add new driver");
        System.out.printf("%-30s %-30s\n", "3. Assign bus to driver", "4. Assign route to driver");
        System.out.printf("%-30s %-30s\n", "5. Change Assigned Bus", "6. Remove Assigned Bus");
        System.out.printf("%-30s %-30s\n", "7. Remove Assigned Route", "8. Go To Main Menu");
        System.out.printf("%-30s\n", "9. Log out");

        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        System.out.println("");

        switch (option) {
            case 1:
                DriverController.seeDriverList();
                break;
            case 2:
                DriverController.addNewDriver();
                break;
            case 3:
                DriverController.assignBusToDriver();
                break;
            case 4:
                DriverController.assignRouteToDriver();
                break;
            case 5:
                DriverController.changeAssignedBus();
                break;
            case 6:
                DriverController.removeAssignedBus();
                break;
            case 7:
                DriverController.removeAssignedRoute();
                break;
            case 8:
                Menu.menuAdmin();
                break;
            case 9:
                System.out.println("Good bye");
                break;
            default:
                System.out.println("Invalid input, start over");
        }
    }
}