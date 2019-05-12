package main.java.sample;

import java.sql.*;

public class Extract {
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

    private double travelPrice = 0;
    private double clothesPrice = 0;
    private double beautyPrice = 0;
    private double billPrice = 0;
    private double entertainmentPrice = 0;
    private double foodPrice = 0;

    public double getTravelPrice() {
        return travelPrice;
    }

    public double getClothesPrice() {
        return clothesPrice;
    }

    public double getBeautyPrice() {
        return beautyPrice;
    }

    public double getBillPrice() {
        return billPrice;
    }

    public double getEntertainmentPrice() {
        return entertainmentPrice;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    /**
     * select category and price row in the TABLA3 table
     */
    public void selectAll(){
        String sql = "SELECT id, category, price FROM TABLA3";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(
                        rs.getString("category") +
                        rs.getDouble("price"));


                if(rs.getString("category").equals("Travel")) {
                    travelPrice = travelPrice + rs.getDouble("price");
                }
                if(rs.getString("category").equals("Clothes")){
                    clothesPrice = clothesPrice + rs.getDouble("price");
                }
                if(rs.getString("category").equals("Entertainment")){
                    entertainmentPrice = entertainmentPrice + rs.getDouble("price");
                }
                if(rs.getString("category").equals("Food")){
                    foodPrice = foodPrice + rs.getDouble("price");
                }
                if(rs.getString("category").equals("Bills")){
                    billPrice = billPrice + rs.getDouble("price");
                }
                if(rs.getString("category").equals("Beauty & health")){
                    beautyPrice = beautyPrice + rs.getDouble("price");
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
