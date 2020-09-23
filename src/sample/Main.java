package sample; // make sure this matches the package name, ex: src -> sample

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml")); // make sure the file names match
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400)); // set the window size
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
