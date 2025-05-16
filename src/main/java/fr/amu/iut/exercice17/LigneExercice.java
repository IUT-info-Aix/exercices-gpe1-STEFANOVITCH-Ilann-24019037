package fr.amu.iut.exercice17;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class LigneExercice extends HBox {

    private Exercice exercice;
    private TextField tfReponse;
    private Label lblEnonce;
    private BooleanProperty correct = new SimpleBooleanProperty(false);

    public LigneExercice(Exercice exercice) {
        super(10); // Espacement horizontal de 10 pixels
        this.exercice = exercice;

        // Créer le label pour l'énoncé
        lblEnonce = new Label(exercice.getEnonce());
        lblEnonce.setStyle("-fx-background-color: lightgreen; -fx-padding: 5px;");
        lblEnonce.setPrefWidth(100);

        // Créer le champ de texte pour la réponse
        tfReponse = new TextField("0");
        tfReponse.setPrefWidth(50);

        // Ajouter les composants à la ligne
        getChildren().addAll(lblEnonce, tfReponse);

        // Configurer la propriété correct pour se mettre à jour automatiquement
        tfReponse.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int reponse = Integer.parseInt(newValue);
                correct.set(reponse == exercice.getSolution());
            } catch (NumberFormatException e) {
                correct.set(false);
            }
        });

        // Configurer le style en fonction de la validité de la réponse
        tfReponse.styleProperty().bind(
                Bindings.when(correct)
                        .then("-fx-background-color: lightgreen;")
                        .otherwise("-fx-background-color: white;")
        );
    }

    public BooleanProperty correctProperty() {
        return correct;
    }

    public boolean isCorrect() {
        return correct.get();
    }

    public Exercice getExercice() {
        return exercice;
    }
}
