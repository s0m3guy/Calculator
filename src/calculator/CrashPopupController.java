/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ilja
 */
public class CrashPopupController implements Initializable {
    @FXML
    private Button btnOK;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnOK.defaultButtonProperty().bind(btnOK.focusedProperty());
    }    

    @FXML
    private void handleOKAction(ActionEvent event) {
        Platform.exit();
        System.exit(5);
    }
    
}
