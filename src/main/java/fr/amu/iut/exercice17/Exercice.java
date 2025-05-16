package fr.amu.iut.exercice17;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Exercice {
    private static final Random random = new Random();

    private int operande1;
    private int operande2;
    private char operation;
    private IntegerProperty solution = new SimpleIntegerProperty();

    public Exercice() {
        genererExercice();
    }

    private void genererExercice() {
        // Choisir une opération au hasard
        int choixOperation = random.nextInt(4);

        switch (choixOperation) {
            case 0: // Addition
                operande1 = random.nextInt(50) + 1;
                operande2 = random.nextInt(50) + 1;
                operation = '+';
                solution.set(operande1 + operande2);
                break;

            case 1: // Soustraction
                operande1 = random.nextInt(50) + 10;
                operande2 = random.nextInt(operande1);
                operation = '-';
                solution.set(operande1 - operande2);
                break;

            case 2: // Multiplication
                operande1 = random.nextInt(12) + 1;
                operande2 = random.nextInt(10) + 1;
                operation = '*';
                solution.set(operande1 * operande2);
                break;

            case 3: // Division
                operande2 = random.nextInt(10) + 1;
                solution.set(random.nextInt(10) + 1);
                operande1 = operande2 * solution.get();
                operation = '/';
                break;
        }
    }

    public String getEnonce() {
        return operande1 + " " + operation + " " + operande2 + " =";
    }

    public IntegerProperty solutionProperty() {
        return solution;
    }

    public int getSolution() {
        return solution.get();
    }
}
