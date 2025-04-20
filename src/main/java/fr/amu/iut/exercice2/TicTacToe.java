package fr.amu.iut.exercice2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Random;

public class TicTacToe extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        Random random = new Random();

        ImageView image0 = new ImageView("/exercice2/Croix.png");
        ImageView image1 = new ImageView("/exercice2/Vide.png");
        ImageView image2 = new ImageView("/exercice2/Rond.png");


        for (int i = 0; i < 3; i++) {
            for (int y=0 ; y<3;y++) {
                Label label = new Label();
                int nombre = random.nextInt(3);
                switch (nombre){
                    case 0:
                        label.setGraphic(image0);
                        break;
                    case 1:
                        label.setGraphic(image1);
                        break;
                    case 2:
                        label.setGraphic(image2);
                        break;
                }
                // Créer un BorderPane avec une bordure
                BorderPane borderPane = new BorderPane(label);
                borderPane.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

                gridPane.add(borderPane, i , y);
            }
        }
        Scene scene = new Scene(gridPane, 300, 300);


        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}