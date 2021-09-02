package company.com.Controllers;

import company.com.DbHelper.DbConnection;
import company.com.Menu.Menu;
import company.com.Objects.Busses;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class BusController {
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static void addNewBus(){

        System.out.print("Enter the bus VIN number(5 digits): ");
        String number = scanner.nextLine();

 //       System.out.print("Enter the bus status: ");
 //       scanner.nextLine();
 //       String status = scanner.nextLine();

        try {
            if (number.length() == 5) {

//                System.out.print("Enter the bus status: ");
//                String status = scanner.nextLine();

                ps = DbConnection.getConnection().prepareStatement("INSERT INTO busses(VIN_number) VALUES('" + number + "')");
                ps.execute();
                System.out.println("Successfully added new bus \n");
                Menu.menuBus();
            } else if (number.length() != 5) {
                throw new ArithmeticException();
            } else {
                throw new SQLException();
            }

        }catch (SQLException e) {
    //        e.printStackTrace();
            System.out.print("The bus with number " + number + " already registered, try again (Y/N)? ");
            String option = scanner.nextLine();
            switch (option){
                case "Y":
                case "y":
                    addNewBus();
                    break;
                case "N":
                case "n":
                    //                   System.out.println("Good bye");
                    Menu.menuBus();
                    break;
                default:
                    System.out.println("Invalid input, start over");
            }
        }catch (ArithmeticException e2) {
            System.out.print("Number should contain 5 digits, try again (Y/N)? ");
            String option2 = scanner.nextLine();
            switch (option2){
                case "Y":
                case "y":
                    addNewBus();
                    break;
                case "N":
                case "n":
                    //                   System.out.println("Good bye");
                    Menu.menuBus();
                    break;
                default:
                    System.out.println("Invalid input, start over");
            }
        }
    }

    public static Busses seeBusList(){
        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM busses");
            rs = ps.executeQuery();

            int busId, vin_number;
            String status;

            Busses bus = new Busses();

            System.out.println("The busses are: ");
            System.out.printf("%-5s %-10s %-10s\n", "id", "VIN_number", "status");


            while (rs.next()){
                busId = rs.getInt("ID");
                vin_number = rs.getInt("VIN_number");
                status = rs.getString("status");
                bus.setId(busId);
                bus.setVIN_number(vin_number);
                bus.setStatus(status);
                System.out.printf("%-5s %-10s %-10s\n", busId, vin_number, status);
            }
            System.out.println();
            Menu.menuBus();

            return bus;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Failed to get the bus list");
            return null;
        }
    }

    public static void availableBusses () {
        System.out.println("Available busses are: ");
        System.out.printf("%-10s\n", "VIN_number");

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT VIN_number, status FROM busses WHERE status is NULL");
            rs = ps.executeQuery();

            int number;

            Busses bus = new Busses();

            while (rs.next()) {
                number = rs.getInt("VIN_number");
                bus.setVIN_number(number);
                System.out.printf("%-10s\n", number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
