/*
 * To change this license header, choose License Headers in Project
 Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author ilja
 */
public class FXMLDocumentController implements Initializable {

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

    Timer timer = new Timer();

    long pastMilliseconds;
    long currentMilliseconds;

    DropShadow borderGlow = new DropShadow();
    Lighting lighting = new Lighting();

    private State state = State.EQUALED;

    NumberFormat nf = new DecimalFormat("##.###", new DecimalFormatSymbols(Locale.US));

    @FXML
    private TextField displayField;

    @FXML
    private Button btnOne;

    @FXML
    private Button btnTwo;

    @FXML
    private Button btnThree;

    @FXML
    private Button btnFour;

    @FXML
    private Button btnFive;

    @FXML
    private Button btnSix;

    @FXML
    private Button btnSeven;

    @FXML
    private Button btnEight;

    @FXML
    private Button btnNine;

    @FXML
    private Button btnZero;

    @FXML
    private Button btnEqual;

    @FXML
    private Button btnDivide;

    @FXML
    private Button btnMultiply;

    @FXML
    private Button btnSubtract;

    @FXML
    private Button btnAdd;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        displayField.setText("0");
    }

    private void startEffect(String button) {

        borderGlow.setColor(Color.RED);
        borderGlow.setOffsetX(0f);
        borderGlow.setOffsetY(0f);

        switch (button) {
            case "1":
                btnOne.setEffect(lighting);
                break;
            case "2":
                btnTwo.setEffect(lighting);
                break;
            case "3":
                btnThree.setEffect(lighting);
                break;
            case "4":
                btnFour.setEffect(lighting);
                break;
            case "5":
                btnFive.setEffect(lighting);
                break;
            case "6":
                btnSix.setEffect(lighting);
                break;
            case "7":
                btnSeven.setEffect(lighting);
                break;
            case "8":
                btnEight.setEffect(lighting);
                break;
            case "9":
                btnNine.setEffect(lighting);
                break;
            case "0":
                btnZero.setEffect(lighting);
                break;
            case "=":
                btnEqual.setEffect(lighting);
                break;
            case "/":
                btnDivide.setEffect(lighting);
                break;
            case "*":
                btnMultiply.setEffect(lighting);
                break;
            case "-":
                btnSubtract.setEffect(lighting);
                break;
            case "+":
                btnAdd.setEffect(null);
                break;
        }
//        btnSix.setEffect(lighting);
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
//                btnSix.setEffect(null);

                switch (button) {
                    case "1":
                        btnOne.setEffect(null);
                        break;
                    case "2":
                        btnTwo.setEffect(null);
                        break;
                    case "3":
                        btnThree.setEffect(null);
                        break;
                    case "4":
                        btnFour.setEffect(null);
                        break;
                    case "5":
                        btnFive.setEffect(null);
                        break;
                    case "6":
                        btnSix.setEffect(null);
                        break;
                    case "7":
                        btnSeven.setEffect(null);
                        break;
                    case "8":
                        btnEight.setEffect(null);
                        break;
                    case "9":
                        btnNine.setEffect(null);
                        break;
                    case "0":
                        btnZero.setEffect(null);
                        break;
                    case "=":
                        btnEqual.setEffect(null);
                        break;
                    case "/":
                        btnDivide.setEffect(null);
                        break;
                    case "*":
                        btnMultiply.setEffect(null);
                        break;
                    case "-":
                        btnSubtract.setEffect(null);
                        break;
                    case "+":
                        btnAdd.setEffect(null);
                        break;
                    default:
                        break;
                }

                timer.purge();
            }
        }, 200);
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

        startEffect(digit);

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
        
        startEffect(operator);
        
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

    public void handleEqual() throws IOException {
        startEffect("=");

        i1 = r.nextInt(6 - 0) + 0;

        if (i1 == 1) {
            Parent parent = FXMLLoader.load(getClass().getResource("/calculator/CrashPopup.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Unerwarteter Fehler");
            stage.show();
        }
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

        startEffect("0");

        oldText = displayField.getText();
        if ("0".equals(oldText) || "0.0".equals(oldText)) {
            newText = oldText;
        } else {
            newText = oldText + "0";
        }
        displayField.setText(newText);
    }
}
