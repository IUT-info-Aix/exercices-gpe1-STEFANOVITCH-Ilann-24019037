package fr.amu.iut.exercice10;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;


public class Conteneurs extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fenetre.fxml"));
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }
}









