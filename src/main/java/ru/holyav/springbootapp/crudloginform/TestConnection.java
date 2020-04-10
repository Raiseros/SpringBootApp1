package ru.holyav.springbootapp.crudloginform;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestConnection {


    public static void main(String[] args) {



        String jdbcUrl = "jdbc:mysql://localhost:3306/studentdb?useSSL=false&amp&serverTimezone=UTC";
        String user = "root";
        String pass = "ypzpp45xs";
        try{
            System.out.println("Connecting to database: " + jdbcUrl);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);


            System.out.println("Connecting successful!!!");

        }
        catch (Exception exc){
            exc.printStackTrace();
        }
    }
}
