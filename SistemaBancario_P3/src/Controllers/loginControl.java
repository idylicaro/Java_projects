package Controllers;


import DAO.FuncionarioDao;
import Model.Cargo;
import Model.Funcionario;
import SistemaBancario.Main;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class loginControl implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="labelClose"
    private Label labelClose; // Value injected by FXMLLoader

    @FXML // fx:id="fieldUsuario"
    private JFXTextField fieldUsuario; // Value injected by FXMLLoader

    @FXML // fx:id="fieldSenha"
    private JFXPasswordField fieldSenha; // Value injected by FXMLLoader

    @FXML
    void CloseApplication(MouseEvent event) {
        System.exit(0);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert labelClose != null : "fx:id=\"labelClose\" was not injected: check your FXML file 'Login.fxml'.";
        assert fieldUsuario != null : "fx:id=\"fieldUsuario\" was not injected: check your FXML file 'Login.fxml'.";
        assert fieldSenha != null : "fx:id=\"fieldSenha\" was not injected: check your FXML file 'Login.fxml'.";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void logarButton(ActionEvent event) {

        FuncionarioDao fDao = new FuncionarioDao();
        Funcionario funcionario = new Funcionario();
        funcionario.setUsuario(fieldUsuario.getText());
        funcionario.setSenha(fieldSenha.getText());
        funcionario.setCargo(new Cargo());

        if(fDao.getFuncionarioLogin(funcionario)){
            Main.changeScreen("sidebar",funcionario);
            System.out.println("Login Efetuado");
        }else{
            Main.changeScreen("mensageBox","Usuario ou senha errada!");
        }

    }
}
