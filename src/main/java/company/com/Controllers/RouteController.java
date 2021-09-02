package company.com.Controllers;

import company.com.DbHelper.DbConnection;
import company.com.Menu.Menu;
import company.com.Objects.Busses;
import company.com.Objects.Routes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RouteController {
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean addNewRoute(){

        System.out.print("Enter the route number: ");
        int number = scanner.nextInt();

        System.out.print("Enter the route name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO routes(route_number, route_name) VALUES('" + number + "', '" + name + "')");
            ps.execute();
            System.out.println("Successfully added new route \n");
            Menu.menuAdmin();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.print("Failed to add new route, try again (Y/N)? ");
            String option = scanner.next();
            switch (option){
                case "Y":
                    addNewRoute();
                    break;
                case "N":
                    System.out.println("Good bye");
                    break;
                default:
                    System.out.println("Invalid input, start over");
            }
            return false;
        }
    }

    public static Routes seeRouteList(){
        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM routes");
            rs = ps.executeQuery();

            int routeId, route_number;
            String route_name;

            Routes route = new Routes();

            System.out.println("The routes are: ");
            System.out.printf("%-5s %-10s %-10s\n", "id", "route_number", "route_name");

            while (rs.next()){
                routeId = rs.getInt("ID");
                route_number = rs.getInt("route_number");
                route_name = rs.getString("route_name");
                route.setId(routeId);
                route.setRoute_number(route_number);
                route.setRoute_name(route_name);
                System.out.printf("%-5s %-10s %-10s\n", routeId, route_number, route_name);
            }
            System.out.println();
            Menu.menuAdmin();
            return route;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Failed to get the routes list");
            return null;
        }
    }
}
