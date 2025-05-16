package fr.amu.iut.exercice17;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AppMaths extends Application {

    private List<LigneExercice> lignesExercices = new ArrayList<>();
    private VBox exercicesContainer;

    @Override
    public void start(Stage primaryStage) {
        // Créer les options pour la ComboBox
        ObservableList<Integer> options = FXCollections.observableArrayList(6, 9, 12, 15);

        // Créer la ComboBox
        ComboBox<Integer> comboBox = new ComboBox<>(options);
        comboBox.setValue(0); // Valeur par défaut

        // Créer le label
        Label label = new Label("Combien d'exercices ? ");

        // Créer le conteneur horizontal pour le label et la ComboBox
        HBox hbox = new HBox(10, label, comboBox);
        hbox.setPadding(new Insets(15));

        // Créer la scène initiale
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercices de mathématiques");

        // Créer le conteneur des exercices (vide au départ)
        exercicesContainer = new VBox(5);
        exercicesContainer.setPadding(new Insets(15));

        // Ajouter un listener pour réagir aux changements de la ComboBox
        comboBox.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                updateExercises(newValue, primaryStage);
            }
        });

        primaryStage.show();
    }

    private void updateExercises(int nombreExercices, Stage stage) {
        // Nettoyer le conteneur
        exercicesContainer.getChildren().clear();

        // Conserver les exercices incorrects
        List<LigneExercice> exercicesIncorrects = new ArrayList<>();
        for (LigneExercice ligne : lignesExercices) {
            if (ligne.correctProperty().get() == false) {
                exercicesIncorrects.add(ligne);
            }
        }

        // Réinitialiser la liste des lignes d'exercices
        lignesExercices.clear();

        // Ajouter d'abord les exercices incorrects (jusqu'à concurrence du nombre demandé)
        int nbExercicesAjoutes = 0;
        for (int i = 0; i < Math.min(exercicesIncorrects.size(), nombreExercices); i++) {
            lignesExercices.add(exercicesIncorrects.get(i));
            nbExercicesAjoutes++;
        }

        // Compléter avec de nouveaux exercices
        for (int i = nbExercicesAjoutes; i < nombreExercices; i++) {
            lignesExercices.add(new LigneExercice(new Exercice()));
        }

        // Ajouter les lignes d'exercices au conteneur
        for (LigneExercice ligne : lignesExercices) {
            exercicesContainer.getChildren().add(ligne);
        }

        // Ajouter le bouton de validation
        Button btnValider = new Button("Voir le résultat");
        btnValider.setOnAction(e -> validateAnswers());
        exercicesContainer.getChildren().add(btnValider);

        // Mettre à jour la scène
        if (stage.getScene().getRoot() != exercicesContainer) {
            stage.setScene(new Scene(exercicesContainer));
        }

        // Ajuster la taille de la fenêtre
        stage.sizeToScene();
    }

    private void validateAnswers() {
        int nbCorrect = 0;

        // Vérifier chaque réponse
        for (LigneExercice ligne : lignesExercices) {
            if (ligne.correctProperty().get()) {
                nbCorrect++;
            }
        }

        // Afficher le résultat dans une boîte de dialogue
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Résultat");
        alert.setHeaderText(null);
        alert.setContentText("Nombre de réponses correctes : " + nbCorrect + " sur " + lignesExercices.size());

        // Attendre que l'utilisateur ferme la boîte de dialogue
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
