package fr.amu.iut.exercice18;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.converter.NumberStringConverter;

public class PanneauPrincipal {

    // Les contrôles FXML
    @FXML
    private Slider sliderAx;

    @FXML
    private Slider sliderAy;

    @FXML
    private TextField txtBx;

    @FXML
    private TextField txtBy;

    @FXML
    private TextField txtPerimeter;

    @FXML
    private Pane rectanglePane;

    // Les lignes qui forment le rectangle
    private Line horizontalLine1; // Ligne horizontale partant de A
    private Line verticalLine1;   // Ligne verticale partant de A
    private Line horizontalLine2; // Ligne horizontale partant de B
    private Line verticalLine2;   // Ligne verticale partant de B

    // Le modèle du rectangle
    private RectangleCustom rectangle;

    // Valeur maximale pour les coordonnées
    private final int valeurMaxCoordonnees = 20;

    // Ratio pour le dessin
    private final double ratioDessin = 10.0;

    /**
     * Initialise le contrôleur après le chargement du FXML
     */
    @FXML
    public void initialize() {
        // Crée le modèle du rectangle
        rectangle = new RectangleCustom();

        // Crée et ajoute les lignes au panneau
        createLines();
        addLines();

        // Lie les propriétés du rectangle aux contrôles de l'interface
        bindSommetsRectangle();
        bindPerimeterTextField();

        // Lie les lignes aux coordonnées du rectangle
        bindHorizontal1();
        bindVertical1();
        bindHorizontal2();
        bindVertical2();
    }

    /**
     * Crée les objets Line pour dessiner le rectangle
     */
    private void createLines() {
        horizontalLine1 = new Line();
        verticalLine1 = new Line();
        horizontalLine2 = new Line();
        verticalLine2 = new Line();
    }

    /**
     * Ajoute les lignes au panneau de dessin
     */
    private void addLines() {
        rectanglePane.getChildren().addAll(
                horizontalLine1,
                verticalLine1,
                horizontalLine2,
                verticalLine2
        );
    }

    /**
     * Lie les propriétés des sommets du rectangle aux contrôles de l'interface
     */
    private void bindSommetsRectangle() {
        // Lie les coordonnées du point A aux sliders
        rectangle.xAProperty().bind(sliderAx.valueProperty().asObject());
        rectangle.yAProperty().bind(sliderAy.valueProperty().asObject());

        // Lie bidirectionnellement les coordonnées du point B aux champs texte
        // Utiliser un StringConverter pour convertir entre String et Integer
        txtBx.textProperty().bindBidirectional(rectangle.xBProperty(), new NumberStringConverter());
        txtBy.textProperty().bindBidirectional(rectangle.yBProperty(), new NumberStringConverter());
    }

    /**
     * Lie le champ de texte du périmètre à la propriété correspondante dans le rectangle
     */
    private void bindPerimeterTextField() {
        txtPerimeter.textProperty().bind(
                rectangle.perimetreProperty().asString()
        );
    }

    /**
     * Lie la ligne horizontale partant du point A aux coordonnées du rectangle
     */
    private void bindHorizontal1() {
        // Start point corresponds to A
        horizontalLine1.startXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        horizontalLine1.startYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));

        // End point has same y as A but x is from B
        horizontalLine1.endXProperty().bind(rectangle.xBProperty().multiply(ratioDessin));
        horizontalLine1.endYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));
    }

    /**
     * Lie la ligne verticale partant du point A aux coordonnées du rectangle
     */
    private void bindVertical1() {
        // Start point corresponds to A
        verticalLine1.startXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        verticalLine1.startYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));

        // End point has same x as A but y is from B
        verticalLine1.endXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        verticalLine1.endYProperty().bind(rectangle.yBProperty().multiply(ratioDessin));
    }

    /**
     * Lie la ligne horizontale partant du point B aux coordonnées du rectangle
     */
    private void bindHorizontal2() {
        // Start point has x of A and y of B
        horizontalLine2.startXProperty().bind(rectangle.xAProperty().multiply(ratioDessin));
        horizontalLine2.startYProperty().bind(rectangle.yBProperty().multiply(ratioDessin));

        // End point corresponds to B
        horizontalLine2.endXProperty().bind(rectangle.xBProperty().multiply(ratioDessin));
        horizontalLine2.endYProperty().bind(rectangle.yBProperty().multiply(ratioDessin));
    }

    /**
     * Lie la ligne verticale partant du point B aux coordonnées du rectangle
     */
    private void bindVertical2() {
        // Start point has x of B and y of A
        verticalLine2.startXProperty().bind(rectangle.xBProperty().multiply(ratioDessin));
        verticalLine2.startYProperty().bind(rectangle.yAProperty().multiply(ratioDessin));

        // End point corresponds to B
        verticalLine2.endXProperty().bind(rectangle.xBProperty().multiply(ratioDessin));
        verticalLine2.endYProperty().bind(rectangle.yBProperty().multiply(ratioDessin));
    }

    /**
     * Incrémente la valeur de la coordonnée x du point B
     */
    @FXML
    public void incrementerBx() {
        int currentValue = rectangle.xBProperty().get();
        if (currentValue < valeurMaxCoordonnees) {
            rectangle.xBProperty().set(currentValue + 1);
        }
    }

    /**
     * Décrémente la valeur de la coordonnée x du point B
     */
    @FXML
    public void decrementerBx() {
        int currentValue = rectangle.xBProperty().get();
        if (currentValue > 0) {
            rectangle.xBProperty().set(currentValue - 1);
        }
    }

    /**
     * Décrémente la valeur de la coordonnée y du point B
     */
    @FXML
    public void setByMinusAction() {
        int currentValue = rectangle.yBProperty().get();
        if (currentValue > 0) {
            rectangle.yBProperty().set(currentValue - 1);
        }
    }

    /**
     * Incrémente la valeur de la coordonnée y du point B
     */
    @FXML
    public void setByPlusAction() {
        int currentValue = rectangle.yBProperty().get();
        if (currentValue < valeurMaxCoordonnees) {
            rectangle.yBProperty().set(currentValue + 1);
        }
    }
}