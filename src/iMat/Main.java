package iMat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Controller.fxml")));
        primaryStage.setTitle("iMat");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.getIcons().add(new Image("img/normal_iMat_logo.png"));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);



        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                DB.getInstance().shutDown();
            }
        }));

    }
}
