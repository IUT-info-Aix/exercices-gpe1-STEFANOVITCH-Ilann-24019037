package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Animation extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        CustomButton customButton = new CustomButton();
        root.setCenter(customButton);
        Scene scene = new Scene(root, 400, 400);

        Duration duration = Duration.millis(1500);
        TranslateTransition transition1 = new TranslateTransition(duration, customButton);
        TranslateTransition transition2 = new TranslateTransition(duration, customButton);
        TranslateTransition transition3 = new TranslateTransition(duration, customButton);
        TranslateTransition transition4 = new TranslateTransition(duration, customButton);
        TranslateTransition transition5 = new TranslateTransition(duration, customButton);

        transition1.setByX(150);
        transition1.setByY(-150);
        transition1.setAutoReverse(true);
        transition1.setCycleCount(2);

        transition2.setByX(-300);
        transition2.setAutoReverse(true);
        transition2.setCycleCount(2);

        transition3.setByY(300);
        transition3.setAutoReverse(true);
        transition3.setCycleCount(2);

        transition4.setByX(300);
        transition4.setAutoReverse(true);
        transition4.setCycleCount(2);

        transition5.setByY(-300);
        transition5.setAutoReverse(true);
        transition5.setCycleCount(2);

        SequentialTransition forwardSequence = new SequentialTransition(transition1, transition2, transition3, transition4, transition5);

        customButton.setOnMousePressed(mouseEvent -> {
            forwardSequence.play();
            });


        primaryStage.setTitle("Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
