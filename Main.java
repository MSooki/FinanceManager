package main.java;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import main.java.sample.ControllerLogin;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //  fxml hozzáadása
        FXMLLoader fxmlLoader = new FXMLLoader(new File("/Users/sookimihaly/" +
                "IdeaProjects/FinanceManager/src/main/resources/login.fxml").toURI().toURL());
        Parent fxml = fxmlLoader.load();

        //  scene hozzáadás
        Scene scene = new Scene(new Group(fxml),800,1500);

        //  css fájl hozzáadása
        String css = new File("/Users/sookimihaly/" +
                        "IdeaProjects/FinanceManager/src/main/" +
                "resources/main.css").toURI().toURL().toExternalForm();
        scene.getStylesheets().add(css);

        // stage hozzáadása
        primaryStage.setWidth(775);
        primaryStage.setHeight(500);
        primaryStage.setTitle("Finance Manager Application");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
