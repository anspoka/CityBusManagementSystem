package company.com.Controllers;

import company.com.DbHelper.DbConnection;
import company.com.Menu.Menu;
import company.com.Objects.Drivers;
import company.com.Objects.Routes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DriverController {
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean addNewDriver(){

        System.out.print("Enter driver name: ");
        String name = scanner.nextLine();

        System.out.print("Enter driver surname: ");
        String surname = scanner.nextLine();


        String status = "not assigned";

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO drivers(name, surname, status) VALUES('" + name + "', '" + surname + "', '"+status+"')");
            ps.execute();
            System.out.println("Successfully added new driver \n");
            Menu.menuDriver();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.print("Failed to add new driver, try again (Y/N)? ");
            String option = scanner.next();
            switch (option){
                case "Y":
                    addNewDriver();
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

    public static Drivers seeDriverList(){
        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM drivers");
            rs = ps.executeQuery();

            int driverId, assigned_route;
            String name, surname, status, assigned_bus;

            Drivers driver = new Drivers();

            System.out.println("The drivers are: ");
            System.out.printf("%-5s %-10s %-10s %-15s %-15s %-15s\n", "id", "name", "surname", "status", "assigned_bus", "assigned_route");

            while (rs.next()){
                driverId = rs.getInt("driver_ID");
                name = rs.getString("name");
                surname = rs.getString("surname");
                status = rs.getString("status");
                assigned_bus = rs.getString("assigned_bus");
                assigned_route = rs.getInt("assigned_route");

                driver.setId(driverId);
                driver.setName(name);
                driver.setSurname(surname);
                driver.setStatus(status);
                driver.setAssigned_bus(assigned_bus);
                driver.setAssigned_route(assigned_route);

                System.out.printf("%-5s %-10s %-10s %-15s %-15s %-15s\n", driverId, name, surname, status, assigned_bus, assigned_route);
            }
            System.out.println();
            Menu.menuDriver();
            return driver;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Failed to get the drivers list");
            return null;
        }
    }


    public static void assignBusToDriver() {

        System.out.println("Available drivers are: ");
        System.out.printf("%-5s %-10s %-10s %-15s %-15s\n", "id", "name", "surname", "assigned bus", "assigned route");

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM drivers WHERE assigned_bus IS NULL");
            rs = ps.executeQuery();

            int id, assignedRoute;
            String name, surname, assignedBus;

            Drivers driver = new Drivers();

            while (rs.next()) {
                id = rs.getInt("driver_ID");
                name = rs.getString("name");
                surname = rs.getString("surname");
                assignedBus = rs.getString("assigned_bus");
                assignedRoute = rs.getInt("assigned_route");
                driver.setId(id);
                driver.setName(name);
                driver.setSurname(surname);
                driver.setAssigned_bus(assignedBus);
                driver.setAssigned_route(assignedRoute);
                System.out.printf("%-5s %-10s %-10s %-15s %-15s\n", id, name, surname, assignedBus, assignedRoute);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter driver's id for assigning a bus: ");
        int driver_id = scanner.nextInt();

        BusController.availableBusses();

        System.out.print("Enter bus's VIN_number for assigning to the driver: ");
        int vinNumber = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET assigned_bus= '" + vinNumber + "' WHERE driver_ID= " + driver_id+"");
            ps.execute();
            ps = DbConnection.getConnection().prepareStatement("UPDATE busses SET status= 'active' WHERE VIN_number= '" + vinNumber+"'");
            ps.execute();
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET status='active' WHERE driver_ID="+driver_id+" AND assigned_route IS NOT NULL");
            ps.execute();
            System.out.println("Successfully assigned bus to the driver");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to assign the bus to the driver");
        }

        System.out.println();
        Menu.menuDriver();
    }

    public static void assignRouteToDriver() {
        System.out.println("Available drivers are: ");
        System.out.printf("%-5s %-10s %-10s %-15s %-15s\n", "id", "name", "surname", "assigned bus", "assigned route");

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT driver_ID, name, surname, assigned_bus, assigned_route FROM drivers WHERE assigned_route IS NULL");
            rs = ps.executeQuery();

            int id, assignedRoute;
            String name, surname, assignedBus;

            Drivers driver = new Drivers();

            while (rs.next()) {
                id = rs.getInt("driver_ID");
                name = rs.getString("name");
                surname = rs.getString("surname");
                assignedBus = rs.getString("assigned_bus");
                assignedRoute = rs.getInt("assigned_route");
                driver.setId(id);
                driver.setName(name);
                driver.setSurname(surname);
                driver.setAssigned_bus(assignedBus);
                driver.setAssigned_route(assignedRoute);
                System.out.printf("%-5s %-10s %-10s %-15s %-15s\n", id, name, surname, assignedBus, assignedRoute);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter driver's id for assigning a route: ");
        int driver_id = scanner.nextInt();

        System.out.println("The routes are: ");
        System.out.printf("%-10s %-10s\n", "route_number", "route_name");

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM routes");
            rs = ps.executeQuery();

            int route_number;
            String route_name;

            Routes route = new Routes();

            while (rs.next()){
                route_number = rs.getInt("route_number");
                route_name = rs.getString("route_name");
                route.setRoute_number(route_number);
                route.setRoute_name(route_name);
                System.out.printf("%-10s %-10s\n", route_number, route_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter route number for assigning to the driver: ");
        int routeNumber = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET assigned_route= " + routeNumber + " WHERE driver_ID= " + driver_id);
            ps.execute();
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET status='active' WHERE driver_ID="+driver_id+" AND assigned_bus IS NOT NULL");
            ps.execute();
            System.out.println("Successfully assigned route to the driver");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to assign the route to the driver");
        }

        System.out.println("");
        Menu.menuDriver();
    }


    public static void seeDriverData () {

        Drivers driver = new Drivers();
        int driverId = 0;
        int assigned_route =0;
        String name="";
        String surname="";
        String status="";
        String assigned_bus="";

        System.out.print("Enter driver's id: ");
        int driver_id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM drivers WHERE driver_ID='" + driver_id + "'");
            rs = ps.executeQuery();

            System.out.printf("%-5s %-10s %-10s %-15s %-15s %-15s\n", "id", "name", "surname", "status", "assigned_bus", "assigned_route");

            while (rs.next()) {
                driverId = rs.getInt("driver_ID");
                name = rs.getString("name");
                surname = rs.getString("surname");
                status = rs.getString("status");
                assigned_bus = rs.getString("assigned_bus");
                assigned_route = rs.getInt("assigned_route");

                driver.setId(driverId);
                driver.setName(name);
                driver.setSurname(surname);
                driver.setStatus(status);
                driver.setAssigned_bus(assigned_bus);
                driver.setAssigned_route(assigned_route);
            }
                System.out.printf("%-5s %-10s %-10s %-15s %-15s %-15s\n", driverId, name, surname, status, assigned_bus, assigned_route);
                System.out.println();

        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("Failed to get the driver data");

        }
    }

    //OK
    public static void changeAssignedBus () {

        seeDriverData();

        BusController.availableBusses();

        System.out.println("Confirm data for changes: ");
        System.out.print("Driver ID: ");
        int driversID = scanner.nextInt();
        System.out.print("Bus number to change: ");
        int busNumber = scanner.nextInt();
        System.out.println("New bus number to assign: ");
        int busNumberNew = scanner.nextInt();

        String status = "active";
        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE busses SET status= NULL WHERE VIN_number= '" + busNumber + "'");
            ps.execute();
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET assigned_bus= '" + busNumberNew + "' WHERE driver_ID= '" + driversID + "'");
            ps.execute();
            ps = DbConnection.getConnection().prepareStatement("UPDATE busses SET status='" + status +"' WHERE VIN_number= '" + busNumberNew + "'");
            ps.execute();

            System.out.println("Bus " + busNumber + " removed. New bus " + busNumberNew + " assigned.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to change data.");
        }

        System.out.println("");
        Menu.menuDriver();
    }

//OK
    public static void removeAssignedBus () {

        seeDriverData();

        System.out.println("Confirm data for changes: ");
        System.out.print("Driver ID: ");
        int driverID = scanner.nextInt();
        System.out.print("Bus number to remove: ");
        int busNumber = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE busses SET status= NULL WHERE VIN_number= " + busNumber + "");
            ps.execute();
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET assigned_bus= NULL WHERE driver_ID = " + driverID + "");
            ps.execute();
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET status='not assigned' WHERE driver_ID = " + driverID + "");
            ps.execute();

            System.out.println("Bus " + busNumber + " unassigned.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to change data.");
        }
        System.out.println("");
        Menu.menuDriver();
    }


    public static void removeAssignedRoute () {

        seeDriverData();

        System.out.println("Confirm data for changes: ");
        System.out.print("Driver ID: ");
        int driverID = scanner.nextInt();
        System.out.print("Route number to remove: ");
        int routeNumber = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET assigned_route= NULL WHERE driver_ID = " + driverID + "");
            ps.execute();
            ps = DbConnection.getConnection().prepareStatement("UPDATE drivers SET status='not assigned' WHERE driver_ID = " + driverID + "");
            ps.execute();

            System.out.println("Route " + routeNumber + " unassigned.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to change data.");
        }
        System.out.println("");
        Menu.menuDriver();
    }

}