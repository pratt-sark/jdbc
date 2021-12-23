package com.company;

import java.sql.*;
import java.util.Scanner;

public class prepState_rem {
    public static void main(String[] args) throws Exception {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6457864", "sql6457864",
                    "Le6bIrdfLM");
            System.out.println("Successful Connection established.");
            st = conn.createStatement();

            //Demonstrating prepared statements
            String sql = "select * from man_utd where Age<? and Position=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            //Could've been taken as user input, but I chose not to,
            //because that's not the point of this program
            int age = 30; String pos="Forward";
            stmt.setInt(1,age); //setting integer age
            stmt.setString(2,pos); //setting String position
            rs = stmt.executeQuery(); //execute query and store in ResultSet variable

            System.out.println("\n------------MANCHESTER UNITED SQUAD---------");
            System.out.println("\nNo.\t\t\tName\t\t\t\t\t\tAge\t\tPosition");
            System.out.println("-------------------------------------------------------");

            //Displaying database contents
            while (rs.next()) {
                System.out.printf("%2d\t\t\t%-20s\t\t%-2d\t\t%-10s\n",
                        rs.getInt("ID"), (rs.getString("First_Name") + " " + rs.getString("Last_Name")),
                        rs.getInt("Age"),rs.getString("Position"));
            }
            System.out.println("\nEnter a character to exit....");
            Scanner in = new Scanner(System.in);
            char c = in.next().charAt(0);

        } catch (SQLException e) {
            System.out.println("Error establishing connection.");
        } finally {
            conn.close();
            st.close();
            rs.close();
            System.out.println("\nConnection ended successfully.");
        }
    }
}