package com.company;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class rwBlob_rem {
    public static void main(String[] args) throws Exception {
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
            System.out.println("-------------------------------------------------------");

            //Displaying database contents
            while (rs.next()) {
                System.out.printf("%2d\t\t\t%-20s\t\t%-2d\t\t%-10s\n",
                        rs.getInt("ID"), (rs.getString("First_Name") + " " + rs.getString("Last_Name")),
                        rs.getInt("Age"),rs.getString("Position"));
            }

            //Writing a blob to database
            String sql = "update man_utd set pic = ? where ID = 7";
            PreparedStatement stmt = conn.prepareStatement(sql);

            File f = new File("7.jpeg");
            FileInputStream input = new FileInputStream(f);
            stmt.setBinaryStream(1,input);
            stmt.executeUpdate();

            //Confirmation text
            System.out.println("Picture (blob) File updated.");
            //Printing absolute path of file
            System.out.println("Absolute path = "+f.getAbsolutePath());

            //Reading a blob from database
            sql = "select pic from man_utd where ID = 7";
            rs = st.executeQuery(sql);
            f = new File("read7.jpeg");
            FileOutputStream output = new FileOutputStream(f);

            //Reading blob and storing it in output file
            if (rs.next())
            {
                InputStream in = rs.getBinaryStream("pic");
                byte[] buffer = new byte[1024];
                while(in.read(buffer)>0)
                    output.write(buffer);
            }
            output.close();
            //Confirmation text
            System.out.println("Binary File read.");
            System.out.println("\nEnter a character to exit....");
            Scanner in = new Scanner(System.in);
            char c = in.next().charAt(0);

        } catch (SQLException e) {
            System.out.println("Error establishing connection.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } finally {
            conn.close();
            st.close();
            rs.close();
            System.out.println("\nConnection ended successfully.");
        }
    }
}