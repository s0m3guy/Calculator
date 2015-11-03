/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author ilja
 */
public class Calculator3 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();
        FXMLDocumentController controller = loader.getController();

        Scene scene_main = new Scene(root);

        stage.setScene(scene_main);
        stage.show();

        scene_main.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                switch (keyEvent.getCode()) {
                    case ENTER:
                {
                    try {
                        controller.handleEqual();
                    } catch (IOException ex) {
                        Logger.getLogger(Calculator3.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        System.out.println("Enter");
                        break;
                    case ADD:
                        controller.operatorPressed("+");
                        System.out.println("Plus");
                        break;
                    case SUBTRACT:
                        System.out.println("Minus");
                        break;
                    case DIVIDE:
                        System.out.println("Division");
                        break;
                    case MULTIPLY:
                        System.out.println("Multiply");
                        break;
                    case DIGIT1:
                        controller.handleDigit("1");
                        System.out.println("1");
                        break;
                    case NUMPAD1:
                        controller.handleDigit("1");
                        System.out.println("1");
                        break;
                    case DIGIT2:
                        controller.handleDigit("2");
                        System.out.println("2");
                        break;
                    case NUMPAD2:
                        controller.handleDigit("2");
                        System.out.println("2");
                        break;
                    case DIGIT3:
                        controller.handleDigit("3");
                        System.out.println("3");
                        break;
                    case NUMPAD3:
                        controller.handleDigit("3");
                        System.out.println("3");
                        break;
                    case DIGIT4:
                        controller.handleDigit("4");
                        System.out.println("4");
                        break;
                    case NUMPAD4:
                        controller.handleDigit("4");
                        System.out.println("4");
                        break;
                    case DIGIT5:
                        controller.handleDigit("5");
                        System.out.println("5");
                        break;
                    case NUMPAD5:
                        controller.handleDigit("5");
                        System.out.println("5");
                        break;
                    case DIGIT6:
                        controller.handleDigit("6");
                        System.out.println("6");
                        break;
                    case NUMPAD6:
                        controller.handleDigit("6");
                        System.out.println("6");
                        break;
                    case DIGIT7:
                        controller.handleDigit("7");
                        System.out.println("7");
                        break;
                    case NUMPAD7:
                        controller.handleDigit("7");
                        System.out.println("7");
                        break;
                    case DIGIT8:
                        controller.handleDigit("8");
                        System.out.println("8");
                        break;
                    case NUMPAD8:
                        controller.handleDigit("8");
                        System.out.println("8");
                        break;
                    case DIGIT9:
                        controller.handleDigit("9");
                        System.out.println("9");
                        break;
                    case NUMPAD9:
                        controller.handleDigit("9");
                        System.out.println("9");
                        break;
                    case DIGIT0:
                        controller.handleZero();
                        System.out.println("0");
                        break;
                    case NUMPAD0:
                        controller.handleZero();
                        System.out.println("0");
                        break;

                }
            }
        }
        );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
