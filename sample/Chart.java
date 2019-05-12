package main.java.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

class Chart {
    // static variable single_instance of type Singleton
    private static Chart single_instance = null;

    // variable of type String
    public String s;

    // private constructor restricted to this class itself
    private Chart()
    {
        s = "Hello I am a string part of Singleton class";
    }

    // static method to create instance of Singleton class
    public static Chart getInstance()
    {
        if (single_instance == null)
            single_instance = new Chart();

        return single_instance;
    }

    public PieChart getChart() {
        Extract app = new Extract();
        app.selectAll();
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Beauty & health", app.getBeautyPrice()),
                        new PieChart.Data("Travel", app.getTravelPrice()),
                        new PieChart.Data("Food", app.getFoodPrice()),
                        new PieChart.Data("Clothes", app.getClothesPrice()),
                        new PieChart.Data("Utility costs", app.getBillPrice()),
                        new PieChart.Data("Entartainment", app.getEntertainmentPrice()));
        final PieChart thePieChart = new PieChart(pieChartData);
        thePieChart.setTitle("Your spending");
        return thePieChart;
    }
}
