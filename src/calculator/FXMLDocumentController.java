/*
 * To change this license header, choose License Headers in Project
 Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author ilja
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private void handleTestAction(ActionEvent event) throws Exception {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent parent = FXMLLoader.load(getClass().getResource("/calculator/CrashPopup.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle("Teeeeest");
        stage.show();
    }

    private enum State {

        EQUALED, FIRST, SECOND, OPERATOR
    }

    public static String operator = "+";
    private double oldValue = 0;
    private double newValue = 0;
    private double result = 0;
    private String oldText, newText;
    private String digit;
    private boolean equaled = false;
    Random r = new Random();
    int i1;

    private State state = State.EQUALED;

    NumberFormat nf = new DecimalFormat("##.###", new DecimalFormatSymbols(Locale.US));

    @FXML
    private TextField displayField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayField.setText("0");
    }

    @FXML
    private void handleDotAction(ActionEvent event) {

        double result;

        oldText = displayField.getText();

        if (!oldText.isEmpty() && !oldText.contains(".")) {
            newText = oldText + ".";
        } else {
            newText = oldText;
        }

        displayField.setText(newText);
        result = Double.parseDouble(newText);

        if (state == State.EQUALED || state == State.FIRST) {
            oldValue = result;
            state = State.FIRST;
        } else {
            newValue = result;
            state = State.SECOND;
        }
    }

    @FXML
    private void handleDigitAction(ActionEvent event) {
        digit = ((Button) event.getSource()).getText();
        handleDigit(digit);
    }

    public void handleDigit(String digit) {
        if (state == State.FIRST || state == State.SECOND) {
            oldText = displayField.getText();
        } else {
            oldText = "";
        }

        if ("0".equals(oldText) /*|| "0.0".equals(oldText)*/) {
            displayField.setText(digit);
        } else {
            displayField.setText(oldText + digit);
        }

        if (state == State.EQUALED || state == State.FIRST) {
            oldValue = Double.parseDouble(displayField.getText());
            state = State.FIRST;
        } else {
            newValue = Double.parseDouble(displayField.getText());
            state = State.SECOND;
        }
    }

    @FXML
    private void handleOperator(ActionEvent event) {

        String buttonText = ((Button) event.getSource()).getText();
        operatorPressed(buttonText);
    }

    public void operatorPressed(String buttonText) {
        if (state == State.EQUALED) {
            operator = buttonText;
        }

        if (state == State.SECOND) {
            switch (operator) {
                case "+":
                    oldValue += newValue;
                    break;
                case "-":
                    oldValue -= newValue;
                    break;
                case "*":
                    oldValue *= newValue;
                    break;
                case "/":
                    oldValue /= newValue;
                    break;
                default:
                    break;
            }
            result = oldValue;
            newValue = 0;
            displayField.setText(String.valueOf(nf.format(oldValue)));
        }

        operator = buttonText;
        state = State.OPERATOR;
    }

    @FXML
    private void handleEqualAction(ActionEvent event) throws Exception {
        handleEqual();
    }

    public void handleEqual() {
        i1 = r.nextInt(6 - 0) + 0;

//        if (i1 == 1) {
//            ((Node) (event.getSource())).getScene().getWindow().hide();
//            Parent parent = FXMLLoader.load(getClass().getResource("/calculator/CrashPopup.fxml"));
//            Stage stage = new Stage();
//            Scene scene = new Scene(parent);
//            stage.setScene(scene);
//            stage.setTitle("Unerwarteter Fehler");
//            stage.show();
//        }
        switch (operator) {
            case "+":
                oldValue += newValue;
                break;
            case "-":
                oldValue -= newValue;
                break;
            case "*":
                oldValue *= newValue;
                break;
            case "/":
                oldValue /= newValue;
                break;
            default:
                break;
        }
        result = oldValue;
        displayField.setText(String.valueOf(nf.format(oldValue)));
        state = State.EQUALED;
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        displayField.setText("0");
        oldValue = 0;
        newValue = 0;
        operator = "+";
        state = State.EQUALED;
    }

    @FXML
    private void handleClearEntryAction(ActionEvent event) {
        displayField.setText("0");
        newValue = 0;
        switch (state) {
            case EQUALED:
                displayField.setText(String.valueOf(nf.format(result)));
                break;
            case FIRST:
                oldValue = 0;
                state = State.EQUALED;
                break;
            case SECOND:
            case OPERATOR:
                newValue = 0;
                state = State.OPERATOR;
                break;
            default:
                break;
        }
    }

    private void handleZeroAction(ActionEvent event) {
        handleZero();
    }

    public void handleZero() {
        oldText = displayField.getText();
        if ("0".equals(oldText) || "0.0".equals(oldText)) {
            newText = oldText;
        } else {
            newText = oldText + "0";
        }
        displayField.setText(newText);
    }
}
