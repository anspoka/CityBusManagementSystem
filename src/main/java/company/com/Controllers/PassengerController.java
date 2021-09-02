package company.com.Controllers;

import company.com.DbHelper.DbConnection;
import company.com.Menu.Menu;
import company.com.Objects.Routes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PassengerController {
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean signUp (){
        System.out.print("Enter new passenger username: ");
        String username = scanner.next().trim();

        System.out.print("Enter new passenger password: ");
        String password = scanner.next().trim();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO passengers(username, password) VALUES('" + username + "', '" + password + "')");
            ps.execute();
            System.out.println("Successfully signed up new passenger");
            System.out.println("");
            Menu.menuPassengerLogin();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Unable to sign up, username exists, try again");
            signUp();
            return false;
        }
    }

    public static boolean logIn() {
        System.out.print("Enter passenger username: ");
        String username = scanner.next().trim();

        System.out.print("Enter passenger password: ");
        String password = scanner.next().trim();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM passengers WHERE username='" + username + "'");
            rs = ps.executeQuery();

            String passwordCheck = "";

            while (rs.next()) {
                passwordCheck = rs.getString("password");
            }

            if (passwordCheck.equals(password)){
                System.out.println("Passenger login successful \n");
                Menu.menuPassenger();
            } else {
                System.out.print("Unable to login, try again (Y/N)? ");
                String option = scanner.next();
                switch (option){
                    case "Y":
                        logIn();
                        break;
                    case "N":
                        System.out.println("Good bye");
                        break;
                    default:
                        System.out.println("Invalid input, start over");
                }
            }
            return true;

        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        }
    }

    public static void buyTickets(){
        System.out.print("Enter how many rides: ");
        int noOfRides = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM routes");
            rs = ps.executeQuery();

            String route_name;
            int route_number;

            Routes route = new Routes();

            System.out.println("Available routes are: ");

            while (rs.next()){
                route_name = rs.getString("route_name");
                route_number = rs.getInt("route_number");
                route.setRoute_name(route_name);
                route.setRoute_number(route_number);
                System.out.println(route_number + " - " + route_name);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        System.out.print("Enter the route: ");
        scanner.nextLine();
        String route = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.println("You have bought " + noOfRides + " rides on the route " + route);
        System.out.println("Tickets have been sent to email " + email + "\n");
        Menu.menuPassenger();
    }
}