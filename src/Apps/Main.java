package Apps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Calculator");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/CalculatorOverview.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();

            primaryStage.setScene(new Scene(rootLayout));
            primaryStage.show();
        }
        catch (IOException e){}
    }

    public static void main(String[] args) {
        launch(args);
    }

}
