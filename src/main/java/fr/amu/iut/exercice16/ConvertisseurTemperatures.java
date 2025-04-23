package fr.amu.iut.exercice16;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class ConvertisseurTemperatures extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer les propriétés pour les températures
        DoubleProperty celsius = new SimpleDoubleProperty(0);
        DoubleProperty fahrenheit = new SimpleDoubleProperty(32);

        // Créer les Sliders
        Slider celsiusSlider = new Slider(0, 100, 0);
        Slider fahrenheitSlider = new Slider(32, 212, 32);

        celsiusSlider.setOrientation(Orientation.VERTICAL);
        fahrenheitSlider.setOrientation(Orientation.VERTICAL);

        // Créer les TextFields
        TextField celsiusTextField = new TextField("0.00");
        TextField fahrenheitTextField = new TextField("32.00");

        // Binding bidirectionnel entre les propriétés et les Sliders
        celsius.bindBidirectional(celsiusSlider.valueProperty());
        fahrenheit.bindBidirectional(fahrenheitSlider.valueProperty());

        // Binding bidirectionnel entre les propriétés et les TextFields
        Bindings.bindBidirectional(celsiusTextField.textProperty(), celsius, new NumberStringConverter("0.00"));
        Bindings.bindBidirectional(fahrenheitTextField.textProperty(), fahrenheit, new NumberStringConverter("0.00"));

        // Conversion entre Celsius et Fahrenheit
        celsius.addListener((obs, oldVal, newVal) -> {
            double celsiusValue = newVal.doubleValue();
            double fahrenheitValue = celsiusValue * 9 / 5 + 32;
            fahrenheit.set(fahrenheitValue);
        });

        fahrenheit.addListener((obs, oldVal, newVal) -> {
            double fahrenheitValue = newVal.doubleValue();
            double celsiusValue = (fahrenheitValue - 32) * 5 / 9;
            celsius.set(celsiusValue);
        });

        // Disposition des éléments
        HBox sliders = new HBox(10, celsiusSlider, fahrenheitSlider);
        HBox textFields = new HBox(10, celsiusTextField, fahrenheitTextField);

        HBox root = new HBox(10, sliders, textFields);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 300, 400);

        primaryStage.setTitle("Conversion de Température");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
