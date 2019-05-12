package main.java.sample;

import java.sql.*;
import java.util.Date;

/**
 *
 * @author sqlitetutorial.net
 */
public class Insert {

    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable() {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            /*
            String sql = "CREATE TABLE IF NOT EXISTS TAB " +
                    "(category TEXT)";
            */

            String sql = "CREATE TABLE IF NOT EXISTS TABLA3 " +
                    "(ID INT PRIMARY KEY," +
                    " category    TEXT," +
                    " price       INT, " +
                    " name        TEXT, " +
                    " date        TEXT)";


            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }




    /**
     * Connect to the data.db database
     *
     * @return the Connection object
     */

    private Connection connect() throws ClassNotFoundException {
        // SQLite connection string
        System.out.println("Connenction called");
        String url = "jdbc:sqlite:data.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the table
     *
     * @param category
     * @param price
     * @param name
     * @param date
     */
    public void insert(String category, int price, String name, String date) {
        String sql = "INSERT INTO TABLA3(category, price, name, date) VALUES(?,?,?,?)";

        System.out.println("insert called");
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, category);
            pstmt.setInt(2, price);
            pstmt.setString(3, name);
            pstmt.setString(4, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
