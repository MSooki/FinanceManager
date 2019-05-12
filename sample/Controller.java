package main.java.sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Controller {

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private DatePicker dateField;

    //menu Itemek
    @FXML
    private MenuItem food;
    @FXML
    private MenuItem clothes;
    @FXML
    private MenuItem others;
    @FXML
    private MenuItem travel;
    @FXML
    private MenuItem beautyHealth;
    @FXML
    private MenuItem bills;
    @FXML
    private MenuItem entertainment;
    @FXML
    private MenuButton categoryBtn;
    @FXML
    void changeTitleF() {
        categoryBtn.setText(food.getText());
    }
    @FXML
    void changeTitleC() {
        categoryBtn.setText(clothes.getText());
    }
    @FXML
    void changeTitleO() {
        categoryBtn.setText(others.getText());
    }
    @FXML
    void changeTitleT() {
        categoryBtn.setText(travel.getText());
    }
    @FXML
    void changeTitleB() {
        categoryBtn.setText(beautyHealth.getText());
    }
    @FXML
    void changeTitleBi() {
        categoryBtn.setText(bills.getText());
    }
    @FXML
    void changeTitleE() {
        categoryBtn.setText(entertainment.getText());
    }

    @FXML
    private AnchorPane pane;

    @FXML
    private ScrollPane sPane;

    @FXML
    private MenuItem quit;

    @FXML
    private VBox main;

    @FXML
    void back() throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("/Users/sookimihaly/" +
                "IdeaProjects/FinanceManager/src/main/resources/login.fxml").toURI().toURL());
        VBox box = loader.load();
        main.getChildren().setAll(box);
    }

    /*
    *       DB létrehozása
    *
    *       ebbe az adatbázisba mentem ki a költési adatokat
    *
    */


    Chart chart = Chart.getInstance();
    PieChart thePieChart = chart.getChart();

    @FXML
    void insertToDb() {
        System.out.println("InsertToDb called");

        //ide mentem ami jön adat
        String category = categoryBtn.getText();
        int price = Integer.parseInt(priceField.getText());
        String name = nameField.getText();
        String date = dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Insert app = new Insert();
        app.createNewTable();

        // insert three new rows
        app.insert(category, price,name, date);

        // mindent alaphelyzetbe állítok
        categoryBtn.setText("category");
        priceField.clear();
        nameField.clear();
        drawChart();
    }

    public void drawChart() {
        /*
         *      Chartot rajzol
         *      Frissíti is annak tartalmát mindig amikor az add gombra nyom a felhasználó
         *      A frissítés során gyakorlatilag letörlni a main nevű VBox ról a pieChartot,
         *      majd lekéri a friss adatokat és végül újra rajzolja azt.
         */

        //chartot töröl
        pane.getChildren().remove(thePieChart);
        //lekéri az új adatokat
        thePieChart = chart.getChart();
        //visszaírja a chartot
        thePieChart.resize(200,200);
        pane.getChildren().add(thePieChart);
    }
    public void deleteChart() {
        /*
        *       Chartot törli
        *       Törli az adott AnchorPane-ről a chart-ot
        *
         */
        pane.getChildren().remove(thePieChart);
    }
    public void createScroll() {
        pane.getChildren().add(sPane);
    }
    public void deleteScroll() {
        pane.getChildren().remove(sPane);
    }

    /*
    *       Gombok stílusa
    *
    *       Amikor kurzor felette van a gombnak, beállítja a színét
    *
    *       Sajnos itt valósul meg, mert a css fájlban nem tudtam funcionalitáshoz kötni
    */

    @FXML
    private Button btn1;
    @FXML
    private Button btn2;

    String btnBackColorCursor = "-fx-background-color: #EDF2FA";
    String btnBackColor = "-fx-background-color: #82E0AA";
    String btnBorderClikk = "-fx-border-color: #060606; -fx-border-width: 5px";
    String btnBorder = "-fx-border-color: #82E0AA";

    public void readData() {
        ExtractAll extractAll = new ExtractAll();
        extractAll.getText().wrappingWidthProperty().bind(pane.widthProperty());
        sPane.setFitToWidth(true);
        sPane.setContent(extractAll.getText());
    }

    @FXML
    void click1() {
        deleteChart();
        readData();
        sPane.setOpacity(1.0);
    }
    @FXML
    void click2() {
        drawChart();
        sPane.setOpacity(0.0);
    }

    @FXML
    void color1() {
        btn1.setStyle(btnBackColorCursor);
        btn2.setStyle(btnBackColor);
    }

    @FXML
    void color2() {
        btn1.setStyle(btnBackColor);
        btn2.setStyle(btnBackColorCursor);
    }
}
