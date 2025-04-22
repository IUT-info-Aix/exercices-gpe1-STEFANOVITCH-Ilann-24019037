package fr.amu.iut.exercice9;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
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
        transition1.setAutoReverse(false); // Désactiver l'auto-reverse pour chaque transition
        transition1.setCycleCount(1);

        transition2.setByX(-300);
        transition2.setAutoReverse(false);
        transition2.setCycleCount(1);

        transition3.setByY(300);
        transition3.setAutoReverse(false);
        transition3.setCycleCount(1);

        transition4.setByX(300);
        transition4.setAutoReverse(false);
        transition4.setCycleCount(1);

        transition5.setByY(-300);
        transition5.setAutoReverse(false);
        transition5.setCycleCount(1);

        // Créer une séquence pour l'animation dans le sens normal
        SequentialTransition forwardSequence = new SequentialTransition(transition1, transition2, transition3, transition4, transition5);

        // Créer une séquence pour l'animation en sens inverse
        SequentialTransition reverseSequence = new SequentialTransition(transition5, transition4, transition3, transition2, transition1);

        // Combiner les deux séquences dans une séquence globale
        SequentialTransition fullSequence = new SequentialTransition(forwardSequence, reverseSequence);

        customButton.setOnMousePressed(mouseEvent -> fullSequence.play());

        primaryStage.setTitle("Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
