package Controllers;

import SistemaBancario.Main;
import Util.Utility;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class mensageBox_Controller implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnOk"
    private JFXButton btnOk; // Value injected by FXMLLoader

    @FXML
    private Label lbText;

    @FXML
    void btnOkAction(ActionEvent event) {
        Utility.closeWindow(btnOk);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangeScreenListeners(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("mensageBox")){
                    lbText.autosize();
                    lbText.setText( (String) userData );
                }
            }
        });
    }
}