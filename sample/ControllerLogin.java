package main.java.sample;

        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.RadioButton;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.VBox;

        import java.io.*;
        import java.util.Properties;

public class ControllerLogin {

    @FXML
    private RadioButton remember;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private VBox root;

    @FXML
    void login() throws IOException {

        FXMLLoader loader = new FXMLLoader(new File("/Users/sookimihaly/" +
                "IdeaProjects/FinanceManager/src/main/resources/mainPage.fxml").toURI().toURL());

        String name, psw;
        name = "admin";
        psw = "kod";

        // ha a kod és a felhasználo név egyezik, betölti a másik oldalt
        if(username.getText().equals(name) && password.getText().equals(psw)) {
            VBox box = loader.load();
            root.getChildren().setAll(box);

        } else {
            System.out.println("rossz név vagy jelszó");
        }
        if(remember.isSelected()) {
            saveUser(username.getText(), password.getText());
            System.out.println("kód és név mentve");
        }
    }

    private Properties prop = new Properties();

    private void saveUser(String name, String code) throws IOException {
        //Setting the value to  our properties file.
        prop.setProperty("NEV", name);
        prop.setProperty("KOD", code);

        prop.store(new FileOutputStream("/Users/sookimihaly/" +
                "IdeaProjects/FinanceManager/src/main/resources/userData.properties"), null);

    }

    public void loadUser() throws IOException {

        InputStream in = new FileInputStream("/Users/sookimihaly/" +
                "IdeaProjects/FinanceManager/src/main/resources/userData.properties");
        prop.load(in);

        //Getting the value from  our properties file.
        String valueName = prop.getProperty("NEV").trim();
        String valueCode = prop.getProperty("KOD").trim();
        //Setting the textFields
        username.setText(valueName);
        password.setText(valueCode);
    }
}

