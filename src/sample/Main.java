package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{


        FXMLLoader mainControllerLoader = new FXMLLoader();
        mainControllerLoader.setLocation(getClass().getResource("/sample/sample.fxml"));
        mainControllerLoader.load();
        Parent root = mainControllerLoader.getRoot();

        primaryStage.setScene(new Scene(root));
        int nic;
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
