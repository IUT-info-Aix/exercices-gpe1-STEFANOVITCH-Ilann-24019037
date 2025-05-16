package fr.amu.iut.exercice18;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;

public class RectangleCustom extends Rectangle {
    // Propriétés pour les coordonnées des points A et B
    private final IntegerProperty xA;
    private final IntegerProperty yA;
    private final IntegerProperty xB;
    private final IntegerProperty yB;

    // Propriété pour le périmètre
    private final IntegerProperty perimetre;

    public RectangleCustom() {
        // Initialisation des propriétés
        xA = new SimpleIntegerProperty(0);
        yA = new SimpleIntegerProperty(0);
        xB = new SimpleIntegerProperty(5);
        yB = new SimpleIntegerProperty(2);
        perimetre = new SimpleIntegerProperty(0);

        // Créer les bindings pour calculer automatiquement le périmètre
        createBinding();
    }

    /**
     * Crée les bindings pour calculer le périmètre en fonction des coordonnées
     */
    private void createBinding() {
        // Binding pour calculer la largeur du rectangle (différence absolue entre xA et xB)
        var largeur = Bindings.createIntegerBinding(
                () -> Math.abs(xA.get() - xB.get()),
                xA, xB
        );

        // Binding pour calculer la hauteur du rectangle (différence absolue entre yA et yB)
        var hauteur = Bindings.createIntegerBinding(
                () -> Math.abs(yA.get() - yB.get()),
                yA, yB
        );

        // Binding pour calculer le périmètre (2 * (largeur + hauteur))
        perimetre.bind(Bindings.createIntegerBinding(
                () -> 2 * (largeur.get() + hauteur.get()),
                largeur, hauteur
        ));
    }

    // Getters pour les propriétés (pas de setters car on manipule les propriétés directement)
    public IntegerProperty xAProperty() {
        return xA;
    }

    public IntegerProperty yAProperty() {
        return yA;
    }

    public IntegerProperty xBProperty() {
        return xB;
    }

    public IntegerProperty yBProperty() {
        return yB;
    }

    public IntegerProperty perimetreProperty() {
        return perimetre;
    }
}