package company.com.Controllers;

import company.com.DbHelper.DbConnection;
import company.com.Menu.Menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminController {
    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static boolean signUp (){
        System.out.print("Enter new administrator username: ");
        String username = scanner.next().trim();

        System.out.print("Enter new administrator password: ");
        String password = scanner.next().trim();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO administrators(username, password) VALUES('" + username + "', '" + password + "')");
            ps.execute();
            System.out.println("Successfully signed up new administrator");
            System.out.println("");
            Menu.menuAdminLogIn();
            return true;
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Unable to sign up, username exists, try again");
            signUp();
            return false;
        }
    }

    public static boolean logIn() {
        System.out.print("Enter administrator username: ");
        String username = scanner.next().trim();

        System.out.print("Enter administrator password: ");
        String password = scanner.next().trim();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM administrators WHERE username='" + username + "'");
            rs = ps.executeQuery();

            String passwordCheck = "";

            while (rs.next()) {
                passwordCheck = rs.getString("password");
            }

            if (passwordCheck.equals(password)){
                System.out.println("Administrator login successful \n");
                Menu.menuAdmin();
            } else {
                System.out.print("Unable to login, try again (Y/N)? ");
                String option = scanner.next();
                switch (option){
                    case "Y":
                    case "y":
                        logIn();
                        break;
                    case "N":
                    case "n":
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

}