package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conn_remote_JDBC {
    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6457864", "sql6457864",
                    "Le6bIrdfLM");
            System.out.println("Successful Connection established.");

            st = conn.createStatement();
            rs = st.executeQuery("select * from man_utd");

            System.out.println("\n------------MANCHESTER UNITED SQUAD---------");
            System.out.println("\nNo.\t\t\tName\t\t\t\t\t\tAge\t\tPosition");
            while (rs.next()) {
                System.out.printf("%2d\t\t\t%-20s\t\t%-2d\t\t%-10s\n",
                        rs.getInt("ID"), (rs.getString("First_Name") + " " + rs.getString("Last_Name")),
                        rs.getInt("Age"),rs.getString("Position"));
            }
        } catch (SQLException e) {
            System.out.print("Error establishing connection.");
        }
        finally {
            conn.close();
            st.close();
            rs.close();
            System.out.println("\nConnection ended successfully.");
        }
    }
}