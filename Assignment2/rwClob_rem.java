package com.company;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class rwClob_rem {
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

            //Writing a clob to database
            String sql = "update man_utd set info_text = ? where ID = 7";
            PreparedStatement stmt = conn.prepareStatement(sql);

            File f = new File("7.txt");
            FileReader input = new FileReader(f);
            stmt.setCharacterStream(1,input);
            stmt.executeUpdate();

            //Confirmation text
            System.out.println("Information (clob) File updated.");
            //Printing absolute path of file
            System.out.println("Absolute path = "+f.getAbsolutePath());

            //Reading a clob from database
            sql = "select info_text from man_utd where ID = 7";
            rs = st.executeQuery(sql);
            f = new File("read7.txt");
            FileWriter output = new FileWriter(f);

            //Reading blob and storing it in output file
            if (rs.next())
            {
                Reader in = rs.getCharacterStream("info_text");
                int ch1;
                while((ch1=in.read())!=-1)
                    output.write(ch1);
            }
            output.close(); //essential, otherwise the file will show nothing
            //Confirmation text
            System.out.println("Character File read.");
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