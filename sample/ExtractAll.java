package main.java.sample;

import javafx.scene.text.Text;

import java.sql.*;

public class ExtractAll {
    /**
     * Connect to the data.db database
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
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
     * select category and price row in the TABLA3 table
     */
    public Text getText(){
        String sql = "SELECT id, category, price, name, date FROM TABLA3";

        String string = "";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                string = string +
                        rs.getString("category") + "\t" +
                        rs.getDouble("price") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("date") + "\n";
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Text(string);
    }
}
